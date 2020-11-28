package com.example.posapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersActivity extends AppCompatActivity implements UserListAdapter.OnDeleteClickListener {

    private UserListAdapter userListAdapter;
    private UserViewModel userViewModel;
    Button btn_Register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        btn_Register = findViewById(R.id.btn_reg_new_user);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(UsersActivity.this, RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

        // Display user database
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        userListAdapter = new UserListAdapter(this, this);
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getAllUsers().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> users) {
                userListAdapter.setUsers(users);
            }
        });
    }

    @Override
    public void OnDeleteClickListener(UserEntity myUser) {
        // Code for Delete operation
        userViewModel.delete(myUser);
    }
}
