package com.example.possystem;

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

        table1 = (Button) findViewById(R.id.table1Btn);
        table1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiningMenuActivity();
            }
        });
    }
    public void openDiningMenuActivity() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}