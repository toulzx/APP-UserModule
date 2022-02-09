package com.toulzx.stitp_module_user.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userName")
    private String userName;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "cupboardName")
    private String cupboardName;
    @ColumnInfo(name = "cableIndex")
    private String cableIndex;
    @ColumnInfo(name = "cableContent")
    private String cableContent;

    // Constructor

    public Data(String userName, String time, String cupboardName, String cableIndex, String cableContent) {
        this.userName = userName;
        this.time = time;
        this.cupboardName = cupboardName;
        this.cableIndex = cableIndex;
        this.cableContent = cableContent;
    }

    // Getter

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getCupboardName() {
        return cupboardName;
    }

    public String getCableIndex() {
        return cableIndex;
    }

    public String getCableContent() {
        return cableContent;
    }

    public String getUserName() {
        return userName;
    }

    // Setter

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCupboardName(String cupboardName) {
        this.cupboardName = cupboardName;
    }

    public void setCableIndex(String cableIndex) {
        this.cableIndex = cableIndex;
    }

    public void setCableContent(String cableContent) {
        this.cableContent = cableContent;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
