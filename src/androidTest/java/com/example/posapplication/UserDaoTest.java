package com.example.posapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserDaoTest {

    private UserDatabase database;
    private UserDao dao;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                UserDatabase.class
        ).allowMainThreadQueries().build();
        dao = database.userDao();
    }

    @After
    public void teardown() {
        database.close();
    }

    @Test
    public void insertUser() {
        UserEntity user = new UserEntity();
        user.setFirst_name("Bob");
        user.setLast_name("White");
        user.setUserId("BWhite");
        user.setPassword("12345");
        user.setRole("Admin");
        new Thread( new Runnable() {
            @Override
            public void run() {
                dao.registerUser(user);
//                UserEntity checkUser = dao.login("BWhite", "12345");
                UserEntity checkUser = dao.login("BWhite", "12345");

                // Don't know if this works
//                assert checkUser == user;
            }
        }).start();
    }
}
