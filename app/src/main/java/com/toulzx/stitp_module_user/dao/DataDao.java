package com.toulzx.stitp_module_user.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.toulzx.stitp_module_user.entity.Data;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insertData(Data...data);

    @Update
    void updateData(Data...data);

    @Delete
    void deleteData(Data...data);

    @Query("DELETE FROM DATA")
    void deleteAllData();

    @Query("SELECT * FROM DATA WHERE USERNAME = :userName")
//    LiveData<List<Data>> loadDataByUserName(String userName);
    List<Data> loadDataByUserName(String userName);

    @Query("SELECT * FROM DATA ORDER BY ID DESC")
    LiveData<List<Data>> getAllDataLive();
}
