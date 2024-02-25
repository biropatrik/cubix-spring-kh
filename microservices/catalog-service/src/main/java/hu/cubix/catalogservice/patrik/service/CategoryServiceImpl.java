package hu.cubix.catalogservice.patrik.service;

import hu.cubix.catalogservice.patrik.exception.CategoryAlreadyAddedException;
import hu.cubix.catalogservice.patrik.model.Category;
import hu.cubix.catalogservice.patrik.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category create(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyAddedException();
        }
        return categoryRepository.save(category);
    }
}
