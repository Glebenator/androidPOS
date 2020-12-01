package com.example.posapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Delete
    int delete(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE last_name=(:userId) AND password=(:password)")
    UserEntity login(String userId, String password);

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAllUsers();
}
