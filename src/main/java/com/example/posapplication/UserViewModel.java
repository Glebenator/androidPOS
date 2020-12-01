package com.example.posapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserDao userDao;
    private UserDatabase userDB;
    private LiveData<List<UserEntity>> mAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);

        userDB = UserDatabase.getUserDatabase(application);
        userDao = userDB.userDao();
        mAllUsers = userDao.getAllUsers();
    }

    public void delete(UserEntity userEntity) {
        new DeleteAsyncTask(userDao).execute(userEntity);
    }

    // Wrapper Functions
    LiveData<List<UserEntity>> getAllUsers() {
        return mAllUsers;
    }

    private class DeleteAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        UserDao mUserDao;

        public DeleteAsyncTask(UserDao userDao) {
            this.mUserDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            mUserDao.delete(userEntities[0]);
            return null;
        }
    }
}