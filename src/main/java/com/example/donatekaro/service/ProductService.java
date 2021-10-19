package com.example.donatekaro.service;

import com.example.donatekaro.dao.ProductRepository;
import com.example.donatekaro.dao.UserRepository;
import com.example.donatekaro.dto.ProductRequest;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.User;
import com.example.donatekaro.views.ProductViews;
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



    public  List<ProductViews> getAllProduct(){

        List<Product> products= productRepository.findAll();

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
            return productViewList;
        }).collect(Collectors.toList());

        return updatedProducts;
    }


    public Product save(ProductRequest product){
        Product product1 = new Product();
        product1.setDescription(product.getDescription());
        product1.setIsFree(product.getIsFree());
        product1.setProductPrice(product.getProductPrice());

        User user = userService.getUserById(product.getUserId());
        product1.setUserId(user);

        return productRepository.save(product1);
    }




    public  List<ProductViews> getAllProductByUser(long id){
        User user = userService.getUserById(id);
        List<Product> productList= productRepository.getAllByUserId(user);

        List<ProductViews> updatedProductList=productList.stream().map(product -> {
            ProductViews productViews=new ProductViews();
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
            return productViews;
        }).collect(Collectors.toList());

        return updatedProductList;
    }

}
