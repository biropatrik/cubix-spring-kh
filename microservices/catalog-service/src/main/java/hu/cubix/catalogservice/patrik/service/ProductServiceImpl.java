package hu.cubix.catalogservice.patrik.service;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import hu.cubix.catalogservice.patrik.exception.ProductAlreadyAddedException;
import hu.cubix.catalogservice.patrik.exception.ProductNotFoundException;
import hu.cubix.catalogservice.patrik.model.HistoryData;
import hu.cubix.catalogservice.patrik.model.Product;
import hu.cubix.catalogservice.patrik.model.QProduct;
import hu.cubix.catalogservice.patrik.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager em;

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAllWithCategory();
    }

    @Override
    @Transactional
    public Product create(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new ProductAlreadyAddedException();
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new ProductNotFoundException();
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Iterable<Product> search(Predicate predicate, Pageable pageable, Integer fromPrice, Integer toPrice) {
        ArrayList<Predicate> predicates = new ArrayList<>();
        predicates.add(predicate);

        QProduct product = QProduct.product;
        if (fromPrice != null) {
            predicates.add(product.price.gt(fromPrice));
        }
        if (toPrice != null) {
            predicates.add(product.price.lt(toPrice));
        }

        return  productRepository.findAll(ExpressionUtils.allOf(predicates), pageable);
    }

    @Transactional
    @Cacheable("productHistoryResults")
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<HistoryData<Product>> getHistoryById(int id) {
        List resultList = AuditReaderFactory.get(em)
                .createQuery()
                .forRevisionsOfEntity(Product.class, false, true)
                .add(AuditEntity.property("id").eq(id))
                .getResultList().stream().map(o -> {
                    Object[] objArray = (Object[]) o;

                    DefaultRevisionEntity defaultRevisionEntity = (DefaultRevisionEntity) objArray[1];
                    RevisionType revType = (RevisionType) objArray[2];

                    Product product = (Product) objArray[0];
                    HistoryData<Product> historyData =
                            new HistoryData<>(
                                    product, revType,
                                    defaultRevisionEntity.getId(), defaultRevisionEntity.getRevisionDate());
                    return historyData;
                }).toList();

        return resultList;
    }
}
