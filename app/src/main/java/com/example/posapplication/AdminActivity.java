package com.example.posapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    Button btn_UserDatabase, btn_MenuItemDatabase, btn_CookScreen, btn_ServerScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btn_UserDatabase = findViewById(R.id.btn_database_screen);
        btn_UserDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersActivity = new Intent(AdminActivity.this, UsersActivity.class);
                startActivity(usersActivity);
            }
        });

        btn_MenuItemDatabase = findViewById(R.id.btn_menu_item_database);
        btn_MenuItemDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersActivity = new Intent(AdminActivity.this, MenuItemActivity.class);
                startActivity(usersActivity);
            }
        });

        btn_CookScreen = findViewById(R.id.btn_cook_screen);
        btn_CookScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cookActivity = new Intent(AdminActivity.this, AdminCookScreen.class);
                startActivity(cookActivity);
            }
        });
        btn_ServerScreen = findViewById(R.id.btn_server_screen);
        btn_ServerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serverActivity = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(serverActivity);
            }
        });
    }
}