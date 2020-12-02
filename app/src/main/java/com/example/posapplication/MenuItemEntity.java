package com.example.posapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = MenuItemEntity.TABLE_NAME)
public class MenuItemEntity {
    public static final String TABLE_NAME = "menuItems";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "itemName")
    private String itemName;

    @ColumnInfo(name = "price")
    private double price;

    public MenuItemEntity(String name, double price) {
        this.itemName = name;
        this.price = price;
    }

    public MenuItemEntity() {
        this.itemName = "";
        this.price = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItemEntity{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }
}

