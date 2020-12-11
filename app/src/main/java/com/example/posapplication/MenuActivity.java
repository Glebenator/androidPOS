package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.net.URI;
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
    double totalPrice = 0;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(getIntent().getExtras() != null){
            tableobj = (Table) getIntent().getSerializableExtra("table");
            tableNum = tableobj.getNumber();
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

        tableDatabase = TableDatabase.getTableDatabase(getApplicationContext());
        tableDao = tableDatabase.tableDao();
        Button SendButton = findViewById(R.id.sendButton);
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add table object to table database
                addTable();

                // Check that item is is added properly
                Thread searchThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Table checkTable = tableDao.Search(String.valueOf(tableobj.getNumber()));
                        if (checkTable != null) {
                            checkTable.printMenuItems();
                        }
                    }
                });

                searchThread.start();
                try{
                    searchThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

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

    // Add table entity and delete previous table value
    public void addTable() {
        Thread addTable = new Thread(new Runnable() {
            @Override
            public void run() {
                tableDao.deleteByTable(tableobj.getNumber());
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
        TextView priceText = (TextView) findViewById(R.id.editTextNumber);
        if(!isLoaded) {
            for (int i = 0; i < menuItemEntities.size(); i++) {
                TextView tv1 = new TextView(this);
                tv1.setText(menuItemEntities.get(i).getItemName());
                tv1.setTextSize(40);
                LL.addView(tv1);

                totalPrice += menuItemEntities.get(i).getPrice();
                String result = String.format("%.2f", totalPrice);
                priceText.setText( " " + result);
            }
            index = menuItemEntities.size();
        }
        else {
            TextView tv1 = new TextView(this);
            tv1.setTooltipText(String.valueOf(index));
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(MenuActivity.this, v);
                    popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.delButton) {
                                LL.removeView(v);
                                totalPrice -= menuItemEntities.get((Integer.parseInt(String.valueOf(v.getTooltipText())))).getPrice();
                                String result = String.format("%.2f", totalPrice);
                                priceText.setText(result);
                               menuItemEntities.remove(v.getTooltipText());
                               tableobj.removeMenuItem((Integer.parseInt(String.valueOf(v.getTooltipText()))));
                                index -= 1;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });
            tv1.setText(menuItemEntities.get(menuItemEntities.size()-1).getItemName());
            tv1.setTextSize(40);
            LL.addView(tv1);
            totalPrice += menuItemEntities.get(menuItemEntities.size()-1).getPrice();
            String result = String.format("%.2f", totalPrice);
            priceText.setText(result);
            index += 1;

        }
        isLoaded = true;
    }


}