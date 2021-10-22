package com.example.donatekaro.dto;

import com.example.donatekaro.model.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class ProductRequest {

    private Long productID;

    private String description;

    private  String isFree;

    private Long productPrice;

    private Long userId;

    private Long subCategoryId;
}
