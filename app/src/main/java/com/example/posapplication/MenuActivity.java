package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    LinearLayout LL;
    Table tableobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(getIntent().getExtras() != null){
            tableobj = (Table) getIntent().getSerializableExtra("table");
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
        LL = (LinearLayout) findViewById(R.id.linearLayout);

    }
    public void itemSelected(View v){
        //System.out.println(String.valueOf(v.getTooltipText()));
        //Search in database for v.getTooltipText()
        MenuItemDatabase menuItemDatabase = MenuItemDatabase.getMenuItemsDatabase(getApplicationContext());
        MenuItemDao menuItemDao = menuItemDatabase.menuItemDao();
        //final MenuItemEntity[] menuItem = new MenuItemEntity[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                MenuItemEntity testEnt = menuItemDao.searchItem("Benedict");
                System.out.println(testEnt.getName());
                //tableobj.addMenuItem(menuItemDao.searchItem(String.valueOf(v.getTooltipText())));

            }
        }).start();

        updateList();

    }
    public void updateList(){
        //create a for loop to iterate through the vector of items and exctract the name and price for each.

        for (int i = 0; i < tableobj.getNumItems(); i++){
            TextView tv = new TextView(this);
            tv.setTextSize(40);
            tv.setText(tableobj.getMenuItem(i).getName());

            LL.addView(tv);
            LL.invalidate();
        }


    }
}