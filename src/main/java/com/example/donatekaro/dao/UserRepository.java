package com.example.donatekaro.dao;

import com.example.donatekaro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //return userInfo by email
    User findUserByEmail(String email);

    //return userEmail and password
    User findUserByEmailAndPassword(String email, String password);

    User getUserById(long id);

}
