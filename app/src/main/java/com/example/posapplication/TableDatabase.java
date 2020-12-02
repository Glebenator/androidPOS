//package com.example.posapplication;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//@Database(entities = Table.class, version = 1, exportSchema = false)
//public abstract class TableDatabase extends RoomDatabase {
//
//    public abstract TableDao tableDao();
//
//    // Create singleton instance of database
//    private static volatile TableDatabase tableDatabase;
//
//    static TableDatabase getTableDatabase(final Context context) {
//        if (tableDatabase == null) {
//            synchronized (UserDatabase.class) {
//                if (tableDatabase == null) {
//                    tableDatabase = Room.databaseBuilder(context.getApplicationContext(),
//                            TableDatabase.class, "table_database")
//                            .build();
//                }
//            }
//        }
//        return tableDatabase;
//    }
//}
