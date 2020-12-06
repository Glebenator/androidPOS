package com.example.posapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.posapplication.Data.MenuItemData;

@Database(entities = MenuItemEntity.class, version = 1, exportSchema = false)
public abstract class MenuItemDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "dbmenuitems.db";
    private static MenuItemDatabase sInstance;

    public abstract MenuItemDao menuItemDao();

    public static MenuItemDatabase getMenuItemsDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        MenuItemDatabase.class, MenuItemDatabase.DATABASE_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                AppExecutors.getInstance().diskIO()
                                        .execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                getMenuItemsDatabase(context).menuItemDao()
                                                        .insertAll(MenuItemData
                                                                .populateMenuItemDatabase());
                                            }
                                        });
                            }
                        }).build();
            }
        }
        return sInstance;
    }




//    // Create singleton instance of database
//    private static volatile MenuItemDatabase menuItemDatabase;
//
//    static MenuItemDatabase getMenuItemsDatabase(final Context context) {
//        if (menuItemDatabase == null) {
//            synchronized (MenuItemDatabase.class) {
//                if (menuItemDatabase == null) {
//                    menuItemDatabase = Room.databaseBuilder(context.getApplicationContext(),
//                            MenuItemDatabase.class, "menu_item_database")
//                            .build();
//                }
//            }
//        }
//        return menuItemDatabase;
//    }
}
