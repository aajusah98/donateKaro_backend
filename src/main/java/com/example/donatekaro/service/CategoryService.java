package com.example.donatekaro.service;

import com.example.donatekaro.dao.CategoryRepository;
import com.example.donatekaro.dto.ProductRequest;
import com.example.donatekaro.model.Category;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.User;
import com.example.donatekaro.views.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){

        return categoryRepository.save(category);

    }

    public Object updateCategoryById(long categoryId, Category category) {


        Category category1 = categoryRepository.getById(categoryId);

        category1.setCategoryCode(category.getCategoryCode());
        category1.setCategoryName(category.getCategoryName());

         categoryRepository.save(category1);
        return new ResponseObject(12, "Category Updated");
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Object deleteCategory(long categoryId) {

        Category category = categoryRepository.getCategoriesByCategoryId(categoryId);

        category.setIsDeleted(true);

        categoryRepository.save(category);


        return new ResponseObject(12, "Category Deleted");

    }

    public Category getCategoriesByCategoryId(long id) {
        return categoryRepository.getCategoriesByCategoryId(id);
    }

}
