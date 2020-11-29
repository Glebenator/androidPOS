package com.example.posapplication;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TableDao {

    @Insert
    void registerTable(TableEntity tableEntity);

}
