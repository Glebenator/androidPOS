package com.example.posapplication;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<MenuItemEntity> menuItemEntities;
    private int numItems = 0;
    private boolean isSent = false;

    public boolean addMenuItem(MenuItemEntity m){
        try{
            menuItemEntities.add(m);
            numItems +=1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error adding");
            return false;
        }
        return true;
    }
    public MenuItemEntity getMenuItem(int index){
        return menuItemEntities.get(index);
    }
    boolean removeMenuItem(int index){
        try{
            menuItemEntities.remove(index);
            numItems -=1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error removing");
            return false;
        }
        return  true;
    }
    public int getNumItems(){
        return numItems;
    }

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

    public void setSent(boolean b){
        this.isSent = b;
    }
    public boolean getSent(){
        return isSent;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

}
