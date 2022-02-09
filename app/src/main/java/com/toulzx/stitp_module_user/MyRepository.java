package com.toulzx.stitp_module_user;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.toulzx.stitp_module_user.dao.DataDao;
import com.toulzx.stitp_module_user.dao.UserDao;
import com.toulzx.stitp_module_user.entity.Data;
import com.toulzx.stitp_module_user.entity.User;

import java.util.List;

public class MyRepository {
    private LiveData<List<User>> allUsersLive;
    private LiveData<List<Data>> allDataLive;

    private List<Data> selectedDataLive;

    private UserDao userDao;
    private DataDao dataDao;

    // Constructor

    public MyRepository(Context context) {
        MyDatabase myDatabase = MyDatabase.getDatabase(context.getApplicationContext());
        this.userDao = myDatabase.getUserDao();
        this.dataDao = myDatabase.getDataDao();
        this.allUsersLive = userDao.getAllUsersLive();
        this.allDataLive = dataDao.getAllDataLive();
        this.selectedDataLive = null;
    }

    // Getter

    public LiveData<List<User>> getAllUsersLive() {
        return allUsersLive;
    }

    public LiveData<List<Data>> getAllDataLive() {
        return allDataLive;
    }


    // interface

    public void insertUser(User...users) {
        new InsertUserAsyncTask(userDao).execute(users);
    }
    public void updateUser(User...users) {
        new UpdateUserAsyncTask(userDao).execute(users);
    }
    public void deleteUser(User...users) {
        new DeleteUserAsyncTask(userDao).execute(users);
    }
    public void deleteAllUser() {
        new DeleteAllUsersAsyncTask(userDao).execute();
    }

    public void insertData(Data...data) {
        new InsertDataAsyncTask(dataDao).execute(data);
    }
    public void updateData(Data...data) {
        new InsertDataAsyncTask(dataDao).execute(data);
    }
    public void deleteData(Data...data) {
        new InsertDataAsyncTask(dataDao).execute(data);
    }
    public void deleteAllData() {
        new DeleteAllDataAsyncTask(dataDao).execute();
    }

//    public void loadDataByUserName(User...users) {
//         new LoadDataByUserIdAsyncTask(dataDao).execute(users);
//    }

    public List<Data> loadDataByUserName(User...users) {
        for (User user : users) {
            selectedDataLive = dataDao.loadDataByUserName(user.getUsername());
        }
        return selectedDataLive;
    }



    // AsyncTask - User

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        /**
         *  将`插入`操作在后台运行
         */
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public UpdateUserAsyncTask(UserDao wordDao) {
            this.userDao = userDao;
        }

        /**
         *  将`更新`操作在后台运行
         */
        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        /**
         *  将`删除`操作在后台运行
         */
        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        public DeleteAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        /**
         *  将`删除全部`操作在后台运行
         */
        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }

    // AsyncTask - Data

    private static class InsertDataAsyncTask extends AsyncTask<Data, Void, Void> {
        private DataDao dataDao;

        public InsertDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        /**
         *  将`插入`操作在后台运行
         */
        @Override
        protected Void doInBackground(Data... data) {
            dataDao.insertData(data);
            return null;
        }
    }

    private static class UpdateDataAsyncTask extends AsyncTask<Data, Void, Void> {
        private DataDao dataDao;

        public UpdateDataAsyncTask(DataDao wordDao) {
            this.dataDao = dataDao;
        }

        /**
         *  将`更新`操作在后台运行
         */
        @Override
        protected Void doInBackground(Data... data) {
            dataDao.updateData(data);
            return null;
        }
    }

    private static class DeleteDataAsyncTask extends AsyncTask<Data, Void, Void> {
        private DataDao dataDao;

        public DeleteDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        /**
         *  将`删除`操作在后台运行
         */
        @Override
        protected Void doInBackground(Data... data) {
            dataDao.deleteData(data);
            return null;
        }
    }

    private static class DeleteAllDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private DataDao dataDao;

        public DeleteAllDataAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        /**
         *  将`删除全部`操作在后台运行
         */
        @Override
        protected Void doInBackground(Void... voids) {
            dataDao.deleteAllData();
            return null;
        }
    }

    // AsyncTask

    private static class LoadDataByUserIdAsyncTask extends AsyncTask<User, Void, Void> {
        private DataDao dataDao;
        private String userName;

        public LoadDataByUserIdAsyncTask(DataDao dataDao) {
            this.dataDao = dataDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            for (User user:users) {
                this.userName = user.getUsername();
                dataDao.loadDataByUserName(userName);
            }
            return null;
        }
    }


}
