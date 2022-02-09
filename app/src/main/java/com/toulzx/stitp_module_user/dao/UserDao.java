package com.toulzx.stitp_module_user.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.toulzx.stitp_module_user.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User...users);

    @Update
    void updateUser(User...users);

    @Delete
    void deleteUser(User...users);

    @Query("DELETE FROM USER")
    void deleteAllUsers();

    @Query("SELECT * FROM USER ORDER BY ID DESC")
    LiveData<List<User>> getAllUsersLive();
}
