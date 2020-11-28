package com.example.posapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserDao userDao;
    private UserDatabase userDB;
    private LiveData<List<UserEntity>> mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);

        userDB = UserDatabase.getUserDatabase(application);
        userDao = userDB.userDao();
        mAllUsers = userDao.getAllUsers();
    }

    // Wrapper Functions
    LiveData<List<UserEntity>> getAllUsers() {
        return mAllUsers;
    }
}