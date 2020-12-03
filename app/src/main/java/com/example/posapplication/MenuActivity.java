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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    BreakfastFragment breakfastFragment = new BreakfastFragment();
    BurgersFragment burgersFragment = new BurgersFragment();
    PastasFragment pastasFragment = new PastasFragment();
    DesertsFragment desertsFragment = new DesertsFragment();
    DrinksFragment drinksFragment = new DrinksFragment();
    public CharSequence tableNum;
    LinearLayout LL;
    Table tableobj;
    TableDatabase tableDatabase;
    TableDao tableDao;
    MenuItemEntity MIE = new MenuItemEntity();
    MenuItemDatabase menuItemDatabase;
    MenuItemDao menuItemDao;
    boolean isLoaded = false;

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

        // Updates table object
        tableDatabase = TableDatabase.getTableDatabase(getApplicationContext());
        tableDao = tableDatabase.tableDao();
        addTable();

        // Updates menuItems
        menuItemDatabase = MenuItemDatabase.getMenuItemsDatabase(getApplicationContext());
        menuItemDao = menuItemDatabase.menuItemDao();
        updateList();
    }

    public void itemSelected(View v){
        Thread itemThread = new Thread(new Runnable() {
            @Override
            public void run() {
                MenuItemEntity menuItem = menuItemDao.Search(String.valueOf(v.getTooltipText()));
                MIE.setItemName(menuItem.getItemName());
                MIE.setPrice(menuItem.getPrice());
            }
        });
        itemThread.start();
        try {
            itemThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (MIE.getItemName().equals("")) {
            System.out.println("ero0r");
        } else {
            tableobj.addMenuItem(MIE);



            updateList();
        }
    }

    public void addTable() {
        // Add table entity
        Thread addTable = new Thread(new Runnable() {
            @Override
            public void run() {
                tableDao.registerTable(tableobj);
            }
        });
        addTable.start();
        try {
            addTable.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Deletes previous table instance and adds new table instance
    public void updateTable(MenuItemEntity newMenuItem) {
        Thread tableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Table table = tableDao.Search(tableobj.getNumber());

                System.out.println(table.getNumber());
                System.out.println(table.getNumItems());
            }
        });
        tableThread.start();
        try {
            tableThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateList() {
        LL = (LinearLayout) findViewById(R.id.linearLayout);
        ArrayList<MenuItemEntity> menuItemEntities = tableobj.getMenuArray();
        if(!isLoaded) {
            for (int i = 0; i < menuItemEntities.size(); i++) {
                TextView tv1 = new TextView(this);
                tv1.setText(menuItemEntities.get(i).getItemName());
                tv1.setTextSize(40);
                LL.addView(tv1);
            }
        }
        else {
            TextView tv1 = new TextView(this);
            tv1.setText(menuItemEntities.get(menuItemEntities.size()-1).getItemName());
            tv1.setTextSize(40);
            LL.addView(tv1);
        }
        isLoaded = true;
    }
}