package com.example.posapplication;

import android.app.Application;
import android.os.AsyncTask;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MenuItemViewModel extends AndroidViewModel {

    private MenuItemDao menuItemDao;
    private MenuItemDatabase menuItemDB;
    private LiveData<List<MenuItemEntity>> mAllMenuItems;

    public MenuItemViewModel(@NonNull Application application) {
        super(application);

        menuItemDB = MenuItemDatabase.getMenuItemsDatabase(application);
        menuItemDao = menuItemDB.menuItemDao();
        mAllMenuItems = menuItemDao.getAllMenuItems();
    }

    public void delete(MenuItemEntity menuItemEntity) {
        new MenuItemViewModel.DeleteAsyncTask(menuItemDao).execute(menuItemEntity);
    }

    // Wrapper Functions
    LiveData<List<MenuItemEntity>> getAllMenuItems() {
        return mAllMenuItems;
    }

    private class DeleteAsyncTask extends AsyncTask<MenuItemEntity, Void, Void> {
        MenuItemDao mMenuItemDao;

        public DeleteAsyncTask(MenuItemDao menuItemDao) {
            this.mMenuItemDao = menuItemDao;
        }

        @Override
        protected Void doInBackground(MenuItemEntity... menuItems) {
            mMenuItemDao.delete(menuItems[0]);
            return null;
        }
    }
}