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

    // register and login
    @Test
    public void insertUser() {
        UserEntity user = new UserEntity();
        user.setFirst_name("Bob");
        user.setLast_name("White");
        user.setUserId("BWhite");
        user.setPassword("12345");
        user.setRole("Admin");

        Thread addThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.registerUser(user);
                UserEntity userEntity = dao.login(user.getLast_name(), user.getPassword());

                assert(userEntity.getFirst_name().equals(user.getFirst_name()));
                assert(userEntity.getLast_name().equals(user.getLast_name()));
                assert(userEntity.getUserId().equals(user.getUserId()));
                assert(userEntity.getPassword().equals(user.getPassword()));
            }
        });
        addThread.start();
        try {
            addThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Delete
    @Test
    public void deleteUser() {

        UserEntity user = new UserEntity();
        user.setFirst_name("Bob");
        user.setLast_name("White");
        user.setUserId("BWhite");
        user.setPassword("12345");
        user.setRole("Admin");

        Thread deleteThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.delete(user);
                UserEntity userEntity = dao.login(user.getLast_name(), user.getPassword());
                assert(userEntity == null);
            }
        });
        deleteThread.start();
        try {
            deleteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Test insertAll and getAllUsers
    // Fix this
    @Test
    public void insertAllUsers() {

        UserEntity[] userList = new UserEntity[] {
                new UserEntity("Admin", "Admin", "12345", "Admin", "Admin"),
                new UserEntity("Server", "Server", "12345", "Server", "Server")
        };

        Thread addAllThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.insertAll(userList);
                LiveData<List<UserEntity>> checkUsers = dao.getAllUsers();

                assert(checkUsers != null);
            }
        });
        addAllThread.start();
        try {
            addAllThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
