package com.example.posapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuItemAddActivity extends AppCompatActivity{

        EditText itemName, itemPrice;
        Button btnAdd;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_menu_item);
            itemName = findViewById(R.id.item_name);
            itemPrice = findViewById(R.id.item_price);
            btnAdd = findViewById(R.id.add_menu_item);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create new user entity
                    MenuItemEntity menuItemEntity = new MenuItemEntity();
                    menuItemEntity.setItemName(itemName.getText().toString());
                    menuItemEntity.setPrice(Double.parseDouble(itemPrice.getText().toString()));

                    if (validateInput(menuItemEntity)) {
                        // Do insert operation
                        MenuItemDatabase menuItemDatabase = MenuItemDatabase.getMenuItemsDatabase(getApplicationContext());
                        MenuItemDao menuItemDao = menuItemDatabase.menuItemDao();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // Register User
                                menuItemDao.insert(menuItemEntity);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Menu Item Added!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).start();
                    } else {
                        Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private Boolean validateInput(MenuItemEntity menuItemEntity) {
            if (menuItemEntity.getItemName().isEmpty() ||
                    !(menuItemEntity.getPrice() != 0))
            {
                return false;
            }
            return true;
        }

    }
