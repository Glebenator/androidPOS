package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button diningActivityBtn = (Button) findViewById(R.id.DiningSec);
        diningActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DiningActivity.class);

                startActivity(startIntent);
            }
        });
        Button activityTakeOutBtn = (Button) findViewById(R.id.TravelSec);
        activityTakeOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), activity_TakeOut.class);
                startActivity(startIntent);
            }
        });

        Button CookScreenButton = (Button) findViewById(R.id.CookSec);
        CookScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), OrderReadyActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
