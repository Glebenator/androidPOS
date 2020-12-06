package com.example.posapplication;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MenuItemDaoTest {

    private MenuItemDatabase database;
    private MenuItemDao dao;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                MenuItemDatabase.class
        ).allowMainThreadQueries().build();
        dao = database.menuItemDao();
    }

    @After
    public void teardown() {
        database.close();
    }

    // Insert and search
    @Test
    public void insertMenuItem() {
        MenuItemEntity menuItem = new MenuItemEntity("Sandwich", 8.99);

        Thread addThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.insert(menuItem);
                MenuItemEntity menuItemEntity = dao.Search("Sandwich");

                assert(menuItemEntity.getItemName().equals(menuItem.getItemName()));
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

        MenuItemEntity menuItem = new MenuItemEntity("Sandwich", 8.99);

        Thread deleteThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.delete(menuItem);
                MenuItemEntity menuItemEntity = dao.Search("Sandwich");
                assert(menuItemEntity == null);
            }
        });
        deleteThread.start();
        try {
            deleteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // InsertAll
    @Test
    public void insertAllMenuItem() {

        MenuItemEntity[] menuItemList = new MenuItemEntity[]{
                new MenuItemEntity("Nat's Breakfast", 12.99),
                new MenuItemEntity("Sunny Start", 10.56),
                new MenuItemEntity("Benedict", 8.36)
        };

        Thread addThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.insertAll(menuItemList);
                MenuItemEntity menuItem1 = dao.Search("Nat's Breakfast");
                MenuItemEntity menuItem2 = dao.Search("Sunny Start");
                MenuItemEntity menuItem3 = dao.Search("Benedict");

                assert(menuItemList[0].getItemName().equals(menuItem1.getItemName()));
                assert(menuItemList[1].getItemName().equals(menuItem2.getItemName()));
                assert(menuItemList[2].getItemName().equals(menuItem3.getItemName()));
            }
        });
        addThread.start();
        try {
            addThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
