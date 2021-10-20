package com.example.donatekaro.service;

import com.example.donatekaro.dao.ProductRepository;
import com.example.donatekaro.dao.UserRepository;
import com.example.donatekaro.dto.ProductRequest;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.User;
import com.example.donatekaro.views.ProductViews;
import com.example.donatekaro.views.ResponseObject;
import com.example.donatekaro.views.UserViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

//    public List<Product> getAllProduct() {
//        return productRepository.findAll();
//    }


    public List<ProductViews> getAllProduct() {

        List<Product> products = productRepository.findAll();

        List<ProductViews> updatedProducts = products.stream().map(product -> {
            ProductViews productViewList = new ProductViews();
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
            return productViewList;
        }).collect(Collectors.toList());

        return updatedProducts;
    }


    public Object save(ProductRequest product) {
        Product product1 = new Product();
        product1.setDescription(product.getDescription());
        product1.setIsFree(product.getIsFree());
        product1.setProductPrice(product.getProductPrice());

        User user = userService.getUserById(product.getUserId());
        product1.setUserId(user);

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
