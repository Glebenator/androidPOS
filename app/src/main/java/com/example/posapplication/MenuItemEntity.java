package com.example.posapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = MenuItemEntity.TABLE_NAME)
public class MenuItemEntity {
    public static final String TABLE_NAME = "menuItems";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private double price;

    public MenuItemEntity(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public MenuItemEntity() {
        this.name = "";
        this.price = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

