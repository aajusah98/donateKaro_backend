package com.example.donatekaro.views;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class ProductViews {


    private Long productID;

    private String description;

    private  String isFree;

    private Long productPrice;

    private String firstName;

    private  String lastName;

    private  String email;

    private String mobile;

    private  String address;

    private String typeName;

    private Boolean isDelete;

    private Date createdAt;

    private Date updatedAt;

    private String productImage;

    private String subCategoryName;

    private String categoryName;


}
