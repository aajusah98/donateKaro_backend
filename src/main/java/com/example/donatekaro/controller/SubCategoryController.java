package com.example.donatekaro.controller;

import com.example.donatekaro.dto.SubCategoryRequest;
import com.example.donatekaro.model.Category;
import com.example.donatekaro.model.SubCategory;
import com.example.donatekaro.service.CategoryService;
import com.example.donatekaro.service.SubCategoryService;
import com.example.donatekaro.views.ProductViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubCategoryController {

    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("/addSubCategory")
    public SubCategory addSubCategory(@RequestBody SubCategoryRequest subCategoryRequest){

        return subCategoryService.addSubCategory(subCategoryRequest);
    }

    @PutMapping("/updateSubCategory/{subCategoryId}")
    public SubCategory updateSubProduct(@PathVariable long subCategoryId, @RequestBody SubCategoryRequest subCategoryRequest) {
        return subCategoryService.updateSubCategoryById(subCategoryId,subCategoryRequest);
    }

    @GetMapping("getSubCategory")
    public List<SubCategory> getAllSubCategory() {
        return subCategoryService.getAllSubCategory();
    }


    @GetMapping("/category/subCategory/{categoryId}")
    public List<SubCategory> getAllSubCategoryByCatgeoryId(@PathVariable long categoryId) {
        return subCategoryService.getAllSubCategoryByCatgeoryId(categoryId);
    }


    @DeleteMapping("/delete/subCategory/{subCategoryId}")
    public Object deleteCategory(@PathVariable long subCategoryId) {
        return subCategoryService.deleteSubCategory(subCategoryId);
    }


}
