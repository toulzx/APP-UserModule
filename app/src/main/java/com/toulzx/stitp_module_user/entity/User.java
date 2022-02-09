package com.toulzx.stitp_module_user.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    // Constructor

    public User(String username) {
        this.username = username;
    }

    // Getter

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    // Setter


    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
