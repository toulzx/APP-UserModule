package com.toulzx.stitp_module_user;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.toulzx.stitp_module_user.entity.Data;
import com.toulzx.stitp_module_user.entity.User;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private MyRepository myRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new MyRepository(application);
    }

    public LiveData<List<User>> getAllUsersLive() {
        return myRepository.getAllUsersLive();
    }
    public LiveData<List<Data>> getAllDataLive() {
        return myRepository.getAllDataLive();
    }

    // interface

    public void insertUser(User... users) {
        myRepository.insertUser(users);
    }
    public void updateUser(User... users) {
        myRepository.updateUser(users);
    }
    public void deleteUser(User... users) {
        myRepository.deleteUser(users);
    }
    public void deleteAllUsers() {
        myRepository.deleteAllUser();
    }

    public void insertData(Data... data) {
        myRepository.insertData(data);
    }
    public void updateData(Data... data) {
        myRepository.updateData(data);
    }
    public void deleteData(Data... data) {
        myRepository.deleteData(data);
    }
    public void deleteAllData() {
        myRepository.deleteAllData();
    }

    public User getUser(String userName) {
        return myRepository.getUser(userName);
    }
    public List<Data> loadDataByUserName(String userName) {
        return myRepository.loadDataByUserName(userName);
    }


}
