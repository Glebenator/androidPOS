package com.example.posapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE userId=(:userId) AND password=(:password)")
    UserEntity login(String userId, String password);

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAllUsers();
}
