package com.example.donatekaro.dao;

import com.example.donatekaro.model.Category;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.SubCategory;
import com.example.donatekaro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {


    List<SubCategory> getAllByCategoryId(Category category);

    SubCategory getSubCategoryBySubCategoryId(long id);

}
