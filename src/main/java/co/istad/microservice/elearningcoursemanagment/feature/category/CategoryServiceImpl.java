package co.istad.microservice.elearningcoursemanagment.feature.category;


import co.istad.microservice.elearningcoursemanagment.domain.Category;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryRequest;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryResponse;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryUpdate;
import co.istad.microservice.elearningcoursemanagment.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;



    @Override
    public List<CategoryResponse> getAllCategories() {

        // isStatus == false show
        List<Category> categories = categoryRepository.findAll()
                .stream()
                .filter(category -> !category.isDeleted() ).toList();
        return categories.stream()
                .map(categoryMapper::categoryToCategoryResponse)
                .toList();

    }



    @Override
    public CategoryResponse findCategoryById(String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId)
                );
        return categoryMapper.categoryToCategoryResponse(category);

    }



    @Override
    public void createCategory(CategoryRequest categoryRequest) {

        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        category.setUuid(UUID.randomUUID().toString());
        categoryRepository.save(category);

    }



    @Override
    public CategoryResponse updateCategory(String categoryId, CategoryUpdate categoryUpdate) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId)
                );
        category.setName(categoryUpdate.name());
        return categoryMapper.categoryToCategoryResponse(categoryRepository.save(category));

    }



    @Override
    public void deleteCategory(String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId)
                );
        categoryRepository.delete(category);

    }



    @Override
    public void enableCategory(String id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + id)
                );
        category.setDeleted(false);
        categoryRepository.save(category);
    }



    @Override
    public void disableCategory(String categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + categoryId)
                );
        category.setDeleted(true);
        categoryRepository.save(category);

    }



}
