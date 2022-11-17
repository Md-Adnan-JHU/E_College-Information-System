package com.adnan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class User extends  BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
//    private String username;
//    private String password;
//
//    public User(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

    @Column(nullable = false, unique = true)
    private String email;// email
    @Column(nullable = false, unique = true)
    private String username;// email
    @Column(nullable = false, unique = true)
    private String phoneNo;// email

    private String userPassword;

    private String role;

    private String gender;

    private Date dateOfBirth;

    private String otp;

}
