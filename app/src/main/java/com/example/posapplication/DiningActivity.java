package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiningActivity extends AppCompatActivity {
    private Button table1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining);
    }

    public void openMenu(View view){
        Table tableobj = new Table();
        // Search if tableobj exists
        TableDatabase tableDatabase = TableDatabase.getTableDatabase(getApplicationContext());
        TableDao tableDao = tableDatabase.tableDao();
        Thread searchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Table checkTable = tableDao.Search(String.valueOf(view.getTooltipText()));
                if (checkTable != null) {
                    tableobj.setMenuArray(checkTable.getMenuArray());
                }
            }
        });

        searchThread.start();
        try{
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tableobj.setNumber(String.valueOf(view.getTooltipText()));
        tableobj.printMenuItems();
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("table", tableobj);
        startActivity(intent);
    }
}