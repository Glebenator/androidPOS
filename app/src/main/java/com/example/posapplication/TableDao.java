package com.example.posapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TableDao {

    @Insert
    void registerTable(Table tableEntity);

    @Query("DELETE FROM tables WHERE table_number=(:table_number)")
    abstract void deleteByTable(String table_number);

    @Query("SELECT * FROM tables WHERE table_number=(:table_number)")
    Table Search(String table_number);

    @Query("SELECT * FROM tables")
    List<Table> getAll();

    @Query("SELECT * FROM tables")
    LiveData<List<Table>> getAllTables();

}
