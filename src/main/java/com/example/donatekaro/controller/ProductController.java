package com.example.donatekaro.controller;

import com.example.donatekaro.dto.ProductRequest;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.service.ProductService;
import com.example.donatekaro.views.ProductViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/addProduct")
    public Product saveProduct(@RequestBody ProductRequest product){
        return productService.save(product);
    }


    @GetMapping("getProducts")
    public List<ProductViews> getAllProduct(){
        return productService.getAllProduct();
    }



    @GetMapping("/users/products/{userId}")
    public  List<ProductViews> getAllProductByuser(@PathVariable long userId){
        return productService.getAllProductByUser(userId);
    }



}
