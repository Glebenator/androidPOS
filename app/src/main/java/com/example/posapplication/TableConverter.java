package com.example.posapplication;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableConverter {
    @TypeConverter
    public String fromMenuItemList(ArrayList<MenuItemEntity> menuItems) {
        if (menuItems == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MenuItemEntity>>() {}.getType();
        String json = gson.toJson(menuItems, type);

        return json;
    }

    @TypeConverter
    public ArrayList<MenuItemEntity> toMenuItemList(String menuItemString) {
        if (menuItemString == null) {
            return null;
        }

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<MenuItemEntity>>() {}.getType();
        ArrayList<MenuItemEntity> menuItemList = gson.fromJson(menuItemString, type);
        return menuItemList;
    }
}
