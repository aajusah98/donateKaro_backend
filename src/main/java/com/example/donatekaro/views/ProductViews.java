package com.example.donatekaro.views;

import lombok.Data;

import javax.persistence.Column;

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

}
