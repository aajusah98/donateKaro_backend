package com.example.donatekaro.service;

import com.example.donatekaro.dao.UserRepository;
import com.example.donatekaro.dao.UserTypeRepository;
import com.example.donatekaro.dto.UserRequest;
import com.example.donatekaro.model.Product;
import com.example.donatekaro.model.User;
import com.example.donatekaro.model.UserType;
import com.example.donatekaro.util.UserValidator;
import com.example.donatekaro.views.ResponseObject;
import com.example.donatekaro.views.UserViews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    UserTypeRepository userTypeRepository;



    public List<UserViews> getAllUsers() {
        List<User> users = userRepo.findAll();

        List<UserViews> updatedUsers = users.stream().map(user -> {
            UserViews userViews = new UserViews();
            userViews.setId(user.getId());
            userViews.setFirstName(user.getFirstName());
            userViews.setLastName(user.getLastName());
            userViews.setEmail(user.getEmail());
            userViews.setMobile(user.getMobile());
            userViews.setAddress(user.getAddress());
            userViews.setTypeName(user.getUserType().getTypeName());
            userViews.setIsDelete(user.getIsDeleted());
            userViews.setUpdatedAt(user.getUpdatedAt());
            userViews.setCreatedAt(user.getCreatedAt());
            return userViews;
        }).collect(Collectors.toList());

        return updatedUsers;
    }


    public Object registration(UserRequest userRequest) {
        /*
         * validate the email
         */
        if (!UserValidator.validateEmail(userRequest.getEmail())) {
            return new ResponseObject(5, "This is invaild email!");
        }

        /*
         *validate the first name
         */
        if (!UserValidator.validateFirstname(userRequest.getFirstName())) {
            return new ResponseObject(6, "First Name Is Not Valid!");
        }
        /*
         * validate last name
         */
        if (!UserValidator.validateLastname(userRequest.getLastName())) {

            return new ResponseObject(7, "Last Name Is Not Valid!");
        }

        /*
         * validate password
         */

        if (!UserValidator.validatePassword(userRequest.getPassword())) {
            return new ResponseObject(8, "Invallid password!");
        }

        if (!UserValidator.validateMobile(userRequest.getMobile())) {
            return new ResponseObject(10, "Enter Valid Mobile Number!");
        }

        UserType userType = userTypeRepository.findUserTypeById(userRequest.getUserTypeId());
        User user = userRepo.findUserByEmail(userRequest.getEmail());

        if (user != null) {
            return new ResponseObject(2, "This user is exists!");
        }

        user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setMobile(userRequest.getMobile());
        user.setAddress(userRequest.getAddress());
        user.setPassword(userRequest.getPassword());
        user.setUserType(userType);
        userRepo.save(user);

        UserViews userViews = new UserViews();

        userViews.setId(user.getId());
        userViews.setFirstName(userRequest.getFirstName());
        userViews.setLastName(userRequest.getLastName());
        userViews.setEmail(userRequest.getEmail());
        userViews.setMobile(userRequest.getMobile());
        userViews.setAddress(userRequest.getAddress());
        userViews.setTypeName(userType.getTypeName());
        userViews.setCreatedAt(user.getCreatedAt());
        userViews.setUpdatedAt(user.getUpdatedAt());

        return userViews;
    }


    public Object updateUserById(long userId, UserRequest userRequest) {

        User userById = userRepo.getUserById(userId);

        /*
         *validate the first name
         */
        if (!UserValidator.validateFirstname(userRequest.getFirstName())) {
            return new ResponseObject(6, "First Name Is Not Valid!");
        }
        /*
         * validate last name
         */
        if (!UserValidator.validateLastname(userRequest.getLastName())) {

            return new ResponseObject(7, "Last Name Is Not Valid!");
        }


        if (!UserValidator.validateEmail(userRequest.getEmail())) {
            return new ResponseObject(10, "Email Already Exist!");
        }

        if (!UserValidator.validateMobile(userRequest.getMobile())) {
            return new ResponseObject(11, "Enter Valid Mobile");
        }


        userById.setFirstName(userRequest.getFirstName());
        userById.setLastName(userRequest.getLastName());
        if (userRequest.getEmail() != userById.getEmail()) {
            userById.setEmail(userRequest.getEmail());
        }
        userById.setMobile(userRequest.getMobile());
        userById.setAddress(userRequest.getAddress());

        userRepo.save(userById);
        return new ResponseObject(12, "Profile Updated");
    }


    //User login
    public Object login(UserRequest  userRequest) {

        User user = userRepo.findUserByEmailAndPassword(userRequest.getEmail(), userRequest.getPassword());

        //check if the if the user exists or not
        if (user != null) {
            UserViews userViews = new UserViews();
            userViews.setId(user.getId());
            userViews.setFirstName(user.getFirstName());
            userViews.setLastName(user.getLastName());
            userViews.setEmail(user.getEmail());
            userViews.setMobile(user.getMobile());
            userViews.setAddress(user.getAddress());
            userViews.setTypeName(user.getUserType().getTypeName());
            userViews.setCreatedAt(user.getCreatedAt());
            userViews.setUpdatedAt(user.getUpdatedAt());
            return userViews;
        } else {
            return new ResponseObject(0, "Inavlid email or password!");

        }
    }

    public User getUserById(long id) {
        return userRepo.getUserById(id);
    }

    public UserViews getUserProfile( long userId ) {
        User user = userRepo.getUserById(userId);
            UserViews userViews = new UserViews();
            userViews.setId(user.getId());
            userViews.setFirstName(user.getFirstName());
            userViews.setLastName(user.getLastName());
            userViews.setEmail(user.getEmail());
            userViews.setMobile(user.getMobile());
            userViews.setAddress(user.getAddress());
            userViews.setTypeName(user.getUserType().getTypeName());
            userViews.setIsDelete(user.getIsDeleted());
            userViews.setCreatedAt(user.getCreatedAt());
            userViews.setUpdatedAt(user.getUpdatedAt());
            return userViews;
    }

    public Object deleteUser(long userId) {

        User existUser = userRepo.getUserById(userId);

        existUser.setIsDeleted(true);

        userRepo.save(existUser);

        return new ResponseObject(2, "User Deleted");

    }


}
