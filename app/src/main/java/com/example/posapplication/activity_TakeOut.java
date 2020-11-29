package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_TakeOut extends AppCompatActivity {
    public int callerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__take_out);
    }

    public void openMenu(View view){
        System.out.println(view.getId());
        callerID = view.getId();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}

