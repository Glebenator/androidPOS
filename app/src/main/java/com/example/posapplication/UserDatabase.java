package com.example.posapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.posapplication.Data.UsersData;

@Database(entities = UserEntity.class, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "dbusers.db";
    private static UserDatabase sInstance;

    public abstract UserDao userDao();

    public static UserDatabase getUserDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        UserDatabase.class, UserDatabase.DATABASE_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                AppExecutors.getInstance().diskIO()
                                        .execute(new Runnable() {
                                            @Override
                                            public void run() {
                                                getUserDatabase(context).userDao()
                                                        .insertAll(UsersData
                                                        .populateUserDatabase());
                                            }
                                        });
                            }
                        }).build();
            }
        }
        return sInstance;
    }
}
