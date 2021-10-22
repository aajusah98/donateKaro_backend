package com.example.donatekaro.service;

import com.example.donatekaro.dao.ProductRepository;
import com.example.donatekaro.dao.UserRepository;
import com.example.donatekaro.dto.ProductRequest;
import com.example.donatekaro.model.Category;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.SubCategory;
import com.example.donatekaro.model.User;
import com.example.donatekaro.views.ProductViews;
import com.example.donatekaro.views.ResponseObject;
import com.example.donatekaro.views.UserViews;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private CategoryService categoryService;



    public List<ProductViews> getAllProduct() {

        List<Product> products = productRepository.findAll();

        List<ProductViews> updatedProducts = products.stream().map(product -> {
            ProductViews productViewList = new ProductViews();
            productViewList.setSubCategoryName(product.getSubCategoryId().getSubCategoryName());
            productViewList.setCategoryName(product.getSubCategoryId().getCategoryId().getCategoryName());
            productViewList.setProductID(product.getProductID());
            productViewList.setDescription(product.getDescription());
            productViewList.setIsFree(product.getIsFree());
            productViewList.setProductPrice(product.getProductPrice());
            productViewList.setFirstName(product.getUserId().getFirstName());
            productViewList.setLastName(product.getUserId().getLastName());
            productViewList.setEmail(product.getUserId().getEmail());
            productViewList.setAddress(product.getUserId().getAddress());
            productViewList.setMobile(product.getUserId().getMobile());
            productViewList.setTypeName(product.getUserId().getUserType().getTypeName());
            productViewList.setIsDelete(product.getIsDeleted());
            productViewList.setUpdatedAt(product.getUpdatedAt());
            productViewList.setCreatedAt(product.getCreatedAt());
            productViewList.setProductImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/product/").path(product.getProductImage()).toUriString());
            return productViewList;
        }).collect(Collectors.toList());

        return updatedProducts;
    }


    public Object save(ProductRequest product, MultipartFile productFile) throws IOException {

        UUID uuid = UUID.randomUUID();
        File saveFile = new ClassPathResource("static/product").getFile();
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + uuid + productFile.getOriginalFilename());
        Files.copy(productFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        Product product1 = new Product();
        product1.setDescription(product.getDescription());
        product1.setIsFree(product.getIsFree());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductImage((uuid + productFile.getOriginalFilename()));

        User user = userService.getUserById(product.getUserId());
        product1.setUserId(user);

        SubCategory subCategory=  subCategoryService.getSubCategoryById(product.getSubCategoryId());

        product1.setSubCategoryId(subCategory);

        productRepository.save(product1);

        return new ResponseObject(2, "Product Added");
    }


    public Object updateProductById(long productId, ProductRequest productRequest) {


        Product existProduct = productRepository.getProductByProductID(productId);

        existProduct.setDescription(productRequest.getDescription());

        existProduct.setIsFree(productRequest.getIsFree());

        existProduct.setProductPrice(productRequest.getProductPrice());

        productRepository.save(existProduct);


        return new ResponseObject(12, "Product Updated");

    }


    public List<ProductViews> getAllProductByUser(long id) {
        User user = userService.getUserById(id);
        List<Product> productList = productRepository.getAllByUserId(user);

        List<ProductViews> updatedProductList = productList.stream().map(product -> {
            ProductViews productViews = new ProductViews();
            productViews.setSubCategoryName(product.getSubCategoryId().getSubCategoryName());
            productViews.setCategoryName(product.getSubCategoryId().getCategoryId().getCategoryName());
            productViews.setProductID(product.getProductID());
            productViews.setDescription(product.getDescription());
            productViews.setIsFree(product.getIsFree());
            productViews.setProductPrice(product.getProductPrice());
            productViews.setFirstName(product.getUserId().getFirstName());
            productViews.setLastName(product.getUserId().getLastName());
            productViews.setEmail(product.getUserId().getEmail());
            productViews.setAddress(product.getUserId().getAddress());
            productViews.setMobile(product.getUserId().getMobile());
            productViews.setTypeName(product.getUserId().getUserType().getTypeName());
            productViews.setIsDelete(product.getIsDeleted());
            productViews.setUpdatedAt(product.getUpdatedAt());
            productViews.setCreatedAt(product.getCreatedAt());
            productViews.setProductImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/product/").path(product.getProductImage()).toUriString());
            return productViews;
        }).collect(Collectors.toList());

        return updatedProductList;
    }

    public List<ProductViews> getAllProductBySubCategoryId(long subCategoryId) {
        SubCategory subCategory = subCategoryService.getSubCategoryById(subCategoryId);
        List<Product> productList = productRepository.getAllBySubCategoryId(subCategory);

        List<com.example.donatekaro.views.ProductViews> updatedProductList = productList.stream().map(product -> {
            com.example.donatekaro.views.ProductViews productViews = new com.example.donatekaro.views.ProductViews();
            productViews.setSubCategoryName(product.getSubCategoryId().getSubCategoryName());
            productViews.setCategoryName(product.getSubCategoryId().getCategoryId().getCategoryName());
            productViews.setProductID(product.getProductID());
            productViews.setDescription(product.getDescription());
            productViews.setIsFree(product.getIsFree());
            productViews.setProductPrice(product.getProductPrice());
            productViews.setFirstName(product.getUserId().getFirstName());
            productViews.setLastName(product.getUserId().getLastName());
            productViews.setEmail(product.getUserId().getEmail());
            productViews.setAddress(product.getUserId().getAddress());
            productViews.setMobile(product.getUserId().getMobile());
            productViews.setTypeName(product.getUserId().getUserType().getTypeName());
            productViews.setIsDelete(product.getIsDeleted());
            productViews.setUpdatedAt(product.getUpdatedAt());
            productViews.setCreatedAt(product.getCreatedAt());
            productViews.setProductImage(ServletUriComponentsBuilder.fromCurrentContextPath().path("/product/").path(product.getProductImage()).toUriString());
            return productViews;
        }).collect(Collectors.toList());

        return updatedProductList;
    }




    public Object deleteProduct(long productId) {

        Product existProduct = productRepository.getProductByProductID(productId);

        existProduct.setIsDeleted(true);

        productRepository.save(existProduct);


        return new ResponseObject(12, "Product Deleted");

    }



}
