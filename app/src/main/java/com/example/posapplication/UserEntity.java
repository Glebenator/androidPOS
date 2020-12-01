package com.example.posapplication;


import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *  input is not valid if...
 *  ... the username/password/first name/last name is empty
 *  ... the username is already taken
 */
@Entity(tableName = UserEntity.TABLE_NAME)
public class UserEntity {

    public static final String TABLE_NAME = "Users";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "Role")
    private String Role;

    @ColumnInfo(name = "userId")
    private String userId;

    public UserEntity(String first_name, String last_name, String password, String Role, String userId) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.Role = Role;
        this.userId = userId;
    }

    public UserEntity() {
        this.first_name = "";
        this.last_name = "";
        this.password = "";
        this.Role = "";
        this.userId = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
