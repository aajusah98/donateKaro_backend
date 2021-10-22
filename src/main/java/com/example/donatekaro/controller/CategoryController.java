package com.example.donatekaro.controller;

import com.example.donatekaro.dto.ProductRequest;
import com.example.donatekaro.model.Category;
import com.example.donatekaro.service.CategoryService;
import com.example.donatekaro.views.ProductViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category){

        return categoryService.addCategory(category);
    }

    @PutMapping("/updateCategory/{categoryId}")
    public Object updateProduct(@PathVariable long categoryId, @RequestBody Category category) {
        return categoryService.updateCategoryById(categoryId,category);
    }

    @GetMapping("getCategory")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @DeleteMapping("/delete/category/{categoryId}")
    public Object deleteCategory(@PathVariable long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }


}
