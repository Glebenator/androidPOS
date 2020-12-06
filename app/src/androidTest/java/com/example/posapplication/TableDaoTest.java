package com.example.posapplication;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TableDaoTest {

    private TableDatabase database;
    private TableDao dao;

    @Before
    public void setup() {
        database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                TableDatabase.class
        ).allowMainThreadQueries().build();
        dao = database.tableDao();
    }

    @After
    public void teardown() {
        database.close();
    }

    // Insert and search
    @Test
    public void insertMenuItem() {
        Table table = new Table("108");

        Table checkTable = new Table();
        Thread addThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.registerTable(table);
                Table tableEntity = dao.Search(table.getNumber());

                assert(tableEntity.getNumber().equals(table.getNumber()));
            }
        });
        addThread.start();
        try {
            addThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Delete
    @Test
    public void deleteUser() {
        Table table = new Table("108");

        Thread deleteThread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.deleteByTable(table.getNumber());
                Table tableEntity = dao.Search(table.getNumber());
                assert(tableEntity == null);
            }
        });
        deleteThread.start();
        try {
            deleteThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // getIsReady
    @Test
    public void getReadyTest() {
        Table table = new Table("108");
        table.setReady(true);

        Thread thread = new Thread( new Runnable() {
            @Override
            public void run() {
                dao.registerTable(table);
                List<Table> tableList = dao.getIsReady(true);

                assert(tableList.get(0).isReady() == table.isReady());
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
