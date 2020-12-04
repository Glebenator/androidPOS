package com.example.posapplication;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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

    @ColumnInfo(name = "menu_items")
    private ArrayList<MenuItemEntity> menuItemEntities;

    private boolean isSent = false;

    public Table(){
        menuItemEntities = new ArrayList<MenuItemEntity>();
    }

    public void addMenuItem(MenuItemEntity m){
        MenuItemEntity menuItem = new MenuItemEntity(m.getItemName(), m.getPrice());
        this.menuItemEntities.add(menuItem);
    }

    public ArrayList<MenuItemEntity> getMenuItemEntities() {
        return menuItemEntities;
    }

    public void setMenuItemEntities(ArrayList<MenuItemEntity> menuItemEntities) {
        this.menuItemEntities = menuItemEntities;
    }

    public MenuItemEntity getMenuItem(int index){
        return menuItemEntities.get(index);
    }

    boolean removeMenuItem(int index){
        try{
            menuItemEntities.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error removing");
            return false;
        }
        return  true;
    }

    public int getNumItems(){
        return menuItemEntities.size();
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

    public void setCheckPrice(double checkPrice) {
        this.checkPrice = checkPrice;
    }

    public String getNumber() {
        return number;
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

    public void printMenuItems() {
        System.out.println(menuItemEntities);
    }

    public ArrayList<MenuItemEntity> getMenuArray(){
        return menuItemEntities;
    }

    public void setMenuArray(ArrayList<MenuItemEntity> menuArray) {
        Collections.copy(this.menuItemEntities, menuArray);
    }
}
