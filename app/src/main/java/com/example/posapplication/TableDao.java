package com.example.posapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TableDao {

    @Insert
    void registerTable(Table tableEntity);

    @Query("SELECT * FROM tables WHERE table_number=(:table_number)")
    Table Search(String table_number);
}
