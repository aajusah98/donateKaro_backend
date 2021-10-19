package com.example.donatekaro.dto;

import com.example.donatekaro.model.UserType;
import lombok.Data;

@Data
public class UserRequest {


    private long id;


    private String firstName;

    private  String lastName;


    private  String email;


    private String mobile;

    private  String address;


    private String password;


    private Integer userTypeId;

}
