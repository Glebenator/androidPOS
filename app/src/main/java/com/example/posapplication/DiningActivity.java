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
        tableobj.setNumber(String.valueOf(view.getTooltipText()));
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("table", tableobj);
        startActivity(intent);
    }
}