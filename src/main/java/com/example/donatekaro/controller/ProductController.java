package com.example.donatekaro.controller;

import com.example.donatekaro.dto.ProductRequest;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.SubCategory;
import com.example.donatekaro.service.ProductService;
import com.example.donatekaro.views.ProductViews;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    @Autowired
    ProductService productService;



    @PostMapping("/addProduct")
    public Object saveProduct(@RequestParam("productFile") MultipartFile productFile ,@RequestParam("product") String product) throws IOException {
        ProductRequest productRequest = new ObjectMapper().readValue(product, ProductRequest.class);
        return productService.save(productRequest,productFile);
    }


    @GetMapping("getProducts")
    public List<ProductViews> getAllProduct() {
        return productService.getAllProduct();
    }


    @GetMapping("/users/products/{userId}")
    public List<ProductViews> getAllProductByuser(@PathVariable long userId) {
        return productService.getAllProductByUser(userId);
    }

    @GetMapping("/subCategory/products/{subCatId}")
    public List<ProductViews> getAllProductBySubCategoryId(@PathVariable long subCatId) {
        return productService.getAllProductBySubCategoryId(subCatId);
    }





    @PutMapping("/updateProduct/{productId}")

    public Object updateProduct(@PathVariable long productId, @RequestBody ProductRequest productRequest) {
        return productService.updateProductById(productId,productRequest);
    }

    @DeleteMapping("/delete/product/{productId}")

    public Object deleteProduct(@PathVariable long productId) {
        return productService.deleteProduct(productId);
    }


}
