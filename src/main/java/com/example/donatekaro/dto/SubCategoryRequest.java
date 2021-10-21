package com.example.donatekaro.dto;

import lombok.Data;

@Data
public class SubCategoryRequest {

    private Long productID;

    private String subCategoryName;

    private Long categoryId;
}
