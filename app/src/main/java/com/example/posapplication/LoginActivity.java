package com.example.posapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText userId, password;
    Button loginBtn;
    Intent startingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId = findViewById(R.id.loginID);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.btn_login);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input
                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill all Fields!", Toast.LENGTH_SHORT).show();
                } else {

                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();

                    MenuItemDatabase menuItemDatabase = MenuItemDatabase.getMenuItemsDatabase(getApplicationContext());
                    MenuItemDao menuItemDao = menuItemDatabase.menuItemDao();

                    // Initialize user and menu item database on first run
                    Thread initializeDbThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            MenuItemEntity menuItem = menuItemDao.Search(null);
                        }
                    });

                    initializeDbThread.start();
                    try {
                        initializeDbThread.join();
                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userIdText, passwordText);
                            if (userEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                if (userEntity.getRole().equals("Admin")) {
                                    startingIntent = new Intent(getApplicationContext(), AdminActivity.class);
                                } else if (userEntity.getRole().equals("Server")) {
                                    startingIntent = new Intent(getApplicationContext(), MainActivity.class);
                                } else if (userEntity.getRole().equals("Cook")){
                                    startingIntent = new Intent(getApplicationContext(), CookScreen.class);
                                }
                                startActivity(startingIntent);
                            }
                        }
                    }).start();
                }
            }
        });
    }
}