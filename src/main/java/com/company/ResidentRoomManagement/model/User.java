package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "tbl_user")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
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

    public User(){}

    @JsonIgnore
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @JsonIgnore
    public List<String> getPermissionList(){
        if(this.permissions.length() > 0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
