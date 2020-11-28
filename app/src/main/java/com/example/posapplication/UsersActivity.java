package com.example.posapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private UserListAdapter userListAdapter;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        userListAdapter = new UserListAdapter(this);
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
}
