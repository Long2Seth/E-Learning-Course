package co.istad.microservice.elearningcoursemanagment.feature.category;


import co.istad.microservice.elearningcoursemanagment.domain.Category;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryRequest;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryResponse;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
    }


    @GetMapping("/{id}")
    public CategoryResponse getCategoryById ( @PathVariable String id){
        return categoryService.findCategoryById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCategory(@PathVariable String id, @RequestBody CategoryUpdate categoryUpdate){
        categoryService.updateCategory(id, categoryUpdate);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
    }


    @PutMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableCategory(@PathVariable String id){
        categoryService.enableCategory(id);
    }


    @PutMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableCategory(@PathVariable String id){
        categoryService.disableCategory(id);
    }



}
