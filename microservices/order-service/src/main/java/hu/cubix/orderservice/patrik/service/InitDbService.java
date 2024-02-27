package hu.cubix.orderservice.patrik.service;

import hu.cubix.orderservice.patrik.model.*;
import hu.cubix.orderservice.patrik.repository.AddressRepository;
import hu.cubix.orderservice.patrik.repository.OrderModelRepository;
import hu.cubix.orderservice.patrik.repository.OrderedProductRepository;
import hu.cubix.orderservice.patrik.repository.ProductWithInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InitDbService {

    private final OrderModelRepository orderModelRepository;
    private final AddressRepository addressRepository;
    private final ProductWithInfoRepository productWithInfoRepository;
    private final OrderedProductRepository productRepository;

    @Transactional
    public void deleteDb() {
        productWithInfoRepository.deleteAllInBatch();
        orderModelRepository.deleteAllInBatch();
        productRepository.deleteAllInBatch();
        addressRepository.deleteAllInBatch();
    }

    @Transactional
    public void initDb() {
        OrderedProduct product = OrderedProduct.builder().price(200).name("oil").build();
        productRepository.save(product);

        ProductWithInfo productWithInfo = ProductWithInfo.builder().product(product).count(2).build();
        Address address = Address.builder().city("Eger").zip("3300").build();
        addressRepository.save(address);

        OrderModel order = OrderModel.builder()
                .address(address)
                .username("admin")
                .status(Status.PENDING)
                .productWithInfos(List.of(productWithInfo))
                .build();

        orderModelRepository.save(order);

        productWithInfo.setOrder(order);
        productWithInfoRepository.save(productWithInfo);
    }
}
