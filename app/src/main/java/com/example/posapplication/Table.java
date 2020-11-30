package com.example.posapplication;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = Table.TABLE_NAME)
public class Table implements Serializable {
    public static final String TABLE_NAME = "Tables";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "table_number")
    private String number;

    @ColumnInfo(name = "price")
    private double checkPrice;

//    @ColumnInfo(name = "menu_items")
//    private List<MenuItem> menuitems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCheckPrice() {
        return checkPrice;
    }

    public String getNumber() {
        return number;
    }

    public void setCheckPrice(double checkPrice) {
        this.checkPrice = checkPrice;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
