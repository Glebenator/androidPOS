package com.example.posapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName, lastName, userId, password;
    Spinner spinnerRole;
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        userId = findViewById(R.id.user_id);
        password = findViewById(R.id.password);
        spinnerRole = findViewById(R.id.spinnerRole);
        btnRegister = findViewById(R.id.register);
        populateSpinnerRole();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new user entity
                UserEntity userEntity = new UserEntity();
                userEntity.setFirst_name(firstName.getText().toString());
                userEntity.setLast_name(lastName.getText().toString());
                userEntity.setUserId(userId.getText().toString());
                userEntity.setRole(spinnerRole.getSelectedItem().toString());
                userEntity.setPassword(password.getText().toString());
                if (validateInput(userEntity)) {
                    // Do insert operation
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Register User
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
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

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getFirst_name().isEmpty() ||
            userEntity.getLast_name().isEmpty() ||
                userEntity.getUserId().isEmpty() ||
                userEntity.getRole().isEmpty() ||
                userEntity.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

    private void populateSpinnerRole() {
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.spinner_role));
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(roleAdapter);
    }
}
