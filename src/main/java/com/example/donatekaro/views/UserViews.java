package com.example.donatekaro.views;


import com.example.donatekaro.model.User;
import com.example.donatekaro.model.UserType;
import lombok.Data;

import javax.persistence.*;

@Data
public class UserViews {


	private Long id;

	private String firstName;

	private  String lastName;

	private  String email;

	private String mobile;

	private  String address;

	private String typeName;

}

