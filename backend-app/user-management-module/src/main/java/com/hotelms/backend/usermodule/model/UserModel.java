package com.hotelms.backend.usermodule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;
import com.hotelms.backend.usermodule.util.DateTimeUtil;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "user_details_view")
public class UserModel {

    @Id
    @Column(name = "userid")
    private String userId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobileNo;
    @Column(name = "role")
    private String role;
    @Column(name = "password")
    private String password;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;

    // Default constructor for Hibernate
    public UserModel() {}

    // Constructor only for user registration
    public UserModel(String firstName, String lastName, String address, String email, String mobileNo, String role, String password) {
        this.userId = UUID.randomUUID().toString().replace("-", "");;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.mobileNo = mobileNo;
        this.role = role;
        this.password = password;
        this.createdAt = DateTimeUtil.currentDateTime();
        this.updatedAt = DateTimeUtil.currentDateTime();
    }
}
