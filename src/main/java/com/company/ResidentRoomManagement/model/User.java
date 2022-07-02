package com.company.ResidentRoomManagement.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity{

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private String phoneNo;

    private String email;

    private String passportNo;

    private String gender;

    private String roles = "";

    private String permissions = "";

    private int active;

    private int userGroupId;

    public User(String username, String password, String roles, String permissions){
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
        this.active = 1;
    }

    protected User(){}

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
