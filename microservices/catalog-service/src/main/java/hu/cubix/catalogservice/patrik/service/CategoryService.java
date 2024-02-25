package hu.cubix.catalogservice.patrik.service;

import hu.cubix.catalogservice.patrik.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public Category create(Category category);
}
