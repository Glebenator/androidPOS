package com.example.posapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = MenuItemEntity.class, version = 1, exportSchema = false)
public abstract class MenuItemDatabase extends RoomDatabase {

//    public abstract MenuItemDao menuItemDao();
//
//    private static String DATABASE_NAME = "menuItemDatabase";
//    // Create singleton instance of database
//    private static MenuItemDatabase menuItemDatabase;
//
//    public synchronized static MenuItemDatabase getMenuItemDatabase(Context context) {
//        if(menuItemDatabase == null) {
//            menuItemDatabase = Room.databaseBuilder(context.getApplicationContext(),
//                    MenuItemDatabase.class, DATABASE_NAME)
//                    .allowMainThreadQueries()
//                    .fallbackToDestructiveMigration()
//                    .build();
//        }
//        return menuItemDatabase;
//    }
    public abstract MenuItemDao menuItemDao();

    // Create singleton instance of database
    private static volatile MenuItemDatabase menuItemDatabase;

    static MenuItemDatabase getMenuItemsDatabase(final Context context) {
        if (menuItemDatabase == null) {
            synchronized (MenuItemDatabase.class) {
                if (menuItemDatabase == null) {
                    menuItemDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            MenuItemDatabase.class, "menu_item_database")
                            .build();
                }
            }
        }
        return menuItemDatabase;
    }
}
