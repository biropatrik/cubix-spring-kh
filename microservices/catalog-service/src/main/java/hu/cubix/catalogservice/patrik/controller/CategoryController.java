package hu.cubix.catalogservice.patrik.controller;

import hu.cubix.catalogservice.patrik.api.CategoryControllerApi;
import hu.cubix.catalogservice.patrik.api.model.CategoryDto;
import hu.cubix.catalogservice.patrik.mapper.CategoryMapper;
import hu.cubix.catalogservice.patrik.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerApi {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Override
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        return ResponseEntity.ok(
                categoryMapper.categoriesToDtos(
                        categoryService.findAll()));
    }

    @Override
    public ResponseEntity<CategoryDto> addCategory(CategoryDto categoryDto) {
        return ResponseEntity.ok(
                categoryMapper.categoryDto(
                        categoryService.create(
                            categoryMapper.dtoToCategory(categoryDto))));
    }
}
