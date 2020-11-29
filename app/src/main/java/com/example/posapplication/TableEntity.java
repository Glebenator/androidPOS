package com.example.posapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = TableEntity.TABLE_NAME)
public class TableEntity {
    public static final String TABLE_NAME = "Tables";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "table_number")
    private int table_number;

//    @ColumnInfo(name = "menu_items")
//    private List<MenuItem> menu_items;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTable_number() {
        return table_number;
    }

    public void setTable_number(int table_number) {
        this.table_number = table_number;
    }

//    public List<MenuItem> getMenu_items() {
//        return menu_items;
//    }
//
//    public void setMenu_items(List<MenuItem> menu_items) {
//        this.menu_items = menu_items;
//    }

}
