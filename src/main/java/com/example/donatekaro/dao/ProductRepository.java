package com.example.donatekaro.dao;

import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.SubCategory;
import com.example.donatekaro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long > {

    List<Product> getAllByUserId(User user);

    List<Product> getAllBySubCategoryId(SubCategory subCategory);

    Product getProductByProductID(long id);

}
