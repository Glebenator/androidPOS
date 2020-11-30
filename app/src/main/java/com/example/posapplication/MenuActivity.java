package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    //FragmentManager fragmentManager = getSupportFragmentManager();
    //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    BreakfastFragment breakfastFragment = new BreakfastFragment();
    BurgersFragment burgersFragment = new BurgersFragment();
    PastasFragment pastasFragment = new PastasFragment();
    DesertsFragment desertsFragment = new DesertsFragment();
    DrinksFragment drinksFragment = new DrinksFragment();
    public CharSequence tableNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(getIntent().getExtras() != null){
            Table tableobj = (Table) getIntent().getSerializableExtra("table");
            tableNum = tableobj.getNumber();
            System.out.println(tableNum);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_menu);
        TextView textView = (TextView) findViewById(R.id.TableText);
        textView.setText(String.valueOf(tableNum));


        Button BreakfastButton = (Button) findViewById(R.id.buttonBreakfast);
        BreakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flfragment, breakfastFragment);
                fragmentTransaction.commit();
            }
        });
        Button BurgerButton = (Button) findViewById(R.id.buttonBurgers);
        BurgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flfragment, burgersFragment);
                fragmentTransaction.commit();
            }
        });
        Button PastaButton = (Button) findViewById(R.id.buttonPastas);
        PastaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flfragment, pastasFragment);
                fragmentTransaction.commit();
            }
        });
        Button DesertsButton = (Button) findViewById(R.id.buttonDeserts);
        DesertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flfragment, desertsFragment);
                fragmentTransaction.commit();
            }
        });
        Button DrinksButton = (Button) findViewById(R.id.buttonDrinks);
        DrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.flfragment, drinksFragment);
                fragmentTransaction.commit();
            }
        });


    }
}