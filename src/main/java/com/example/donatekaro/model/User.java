package com.example.donatekaro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "first_name",nullable = false,length = 20)
    private String firstName;

    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "email",nullable = false,unique = true,length = 45)
    private  String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "address")
    private  String address;

    @Column(name = "password", nullable = false,length = 64)
    private String password;

    @JoinColumn(name = "user_type", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserType userType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> productList;


}
