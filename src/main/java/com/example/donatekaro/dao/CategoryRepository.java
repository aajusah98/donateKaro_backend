package com.example.donatekaro.dao;

import com.example.donatekaro.model.Category;
import com.example.donatekaro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category getCategoriesByCategoryId(long id);
}
