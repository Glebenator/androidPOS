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

public class MenuItemActivity extends AppCompatActivity implements MenuItemListAdapter.OnDeleteClickListener {

    private MenuItemListAdapter menuItemListAdapter;
    private MenuItemViewModel menuItemViewModel;
    Button btn_Add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_items);

        btn_Add = findViewById(R.id.btn_add_new_item);

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addActivity = new Intent(MenuItemActivity.this, MenuItemAddActivity.class);
                startActivity(addActivity);
            }
        });

        // Display menu item database
        RecyclerView recyclerView = findViewById(R.id.recyclerMenuItemView);

        menuItemListAdapter = new MenuItemListAdapter(this, this);
        recyclerView.setAdapter(menuItemListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menuItemViewModel = ViewModelProviders.of(this).get(MenuItemViewModel.class);

        menuItemViewModel.getAllMenuItems().observe(this, new Observer<List<MenuItemEntity>>() {
            @Override
            public void onChanged(@Nullable List<MenuItemEntity> menuItemEntities) {
                menuItemListAdapter.setMenuItems(menuItemEntities);
            }
        });
    }

    @Override
    public void OnDeleteClickListener(MenuItemEntity menuItemEntity) {
        // Code for Delete operation
        menuItemViewModel.delete(menuItemEntity);
    }
}