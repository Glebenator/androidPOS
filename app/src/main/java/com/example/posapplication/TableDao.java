package com.example.posapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TableDao {

    @Insert
    void registerTable(Table tableEntity);

    @Query("DELETE FROM tables WHERE table_number=(:table_number)")
    abstract void deleteByTable(String table_number);

    @Query("SELECT * FROM tables WHERE table_number=(:table_number)")
    Table Search(String table_number);

    @Query("Select * FROM tables WHERE is_ready=(:is_ready)")
    List<Table> getIsReady(boolean is_ready);

    @Query("SELECT * FROM tables")
    List<Table> getAll();

    @Update
    void updateTable(Table table);

}
