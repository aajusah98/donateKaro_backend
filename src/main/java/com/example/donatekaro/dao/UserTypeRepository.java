package com.example.donatekaro.dao;

import com.example.donatekaro.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {

    //	UserType findUserByEmail(String typeName);
    UserType findUserTypeById(int id);
}
