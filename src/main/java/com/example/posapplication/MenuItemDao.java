package com.example.posapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MenuItemDao {
    @Insert
    void insert(MenuItemEntity menuItemEntity);

    @Delete
    int delete(MenuItemEntity menuItemEntity);

    @Query("SELECT * FROM menuItems WHERE name=(:name)")
    MenuItemEntity searchItem(String name);

    @Query("SELECT * FROM menuItems")
    LiveData<List<MenuItemEntity>> getAllMenuItems();

    // Update Query
}
