package com.example.posapplication;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = UserEntity.class, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    // Create singleton instance of database
    private static volatile UserDatabase userDatabase;

    static UserDatabase getUserDatabase(final Context context) {
        if (userDatabase == null) {
            synchronized (UserDatabase.class) {
                if (userDatabase == null) {
                    userDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return userDatabase;
    }
}
