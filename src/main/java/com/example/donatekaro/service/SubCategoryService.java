package com.example.donatekaro.service;

import com.example.donatekaro.dao.CategoryRepository;
import com.example.donatekaro.dao.SubCategoryRepository;
import com.example.donatekaro.dto.SubCategoryRequest;
import com.example.donatekaro.model.Category;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.SubCategory;
import com.example.donatekaro.model.User;
import com.example.donatekaro.views.ProductViews;
import com.example.donatekaro.views.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CategoryService categoryService;


    public Object addSubCategory(SubCategoryRequest subCategoryRequest){
        SubCategory subCategory1 = new SubCategory();
        subCategory1.setSubCategoryName(subCategoryRequest.getSubCategoryName());

        Category category = categoryService.getCategoriesByCategoryId(subCategoryRequest.getCategoryId());
        subCategory1.setCategoryId(category);

        return subCategoryRepository.save(subCategory1);

    }

    public Object updateSubCategoryById(long subCategoryId, SubCategoryRequest subCategoryRequest) {


        SubCategory subCategory1 = subCategoryRepository.getSubCategoryBySubCategoryId(subCategoryId);

        subCategory1.setSubCategoryName(subCategoryRequest.getSubCategoryName());

        Category category = categoryService.getCategoriesByCategoryId(subCategoryRequest.getCategoryId());
        subCategory1.setCategoryId(category);


        return  subCategoryRepository.save(subCategory1);
    }

    public List<SubCategory> getAllSubCategory(){
        return subCategoryRepository.findAll();
    }


    public List<SubCategory> getAllSubCategoryByCatgeoryId(long categoryId) {
        Category category = categoryService.getCategoriesByCategoryId(categoryId);

        List<SubCategory> subCategoryList = subCategoryRepository.getAllByCategoryId(category);

        return subCategoryList;
    }

    public Object deleteSubCategory(long subCategoryId) {

        SubCategory subCategory = subCategoryRepository.getSubCategoryBySubCategoryId(subCategoryId);

        subCategory.setIsDeleted(true);

        subCategoryRepository.save(subCategory);


        return new ResponseObject(12, "Sub Category Deleted");

    }

}
