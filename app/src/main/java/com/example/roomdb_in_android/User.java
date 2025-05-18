package com.example.roomdb_in_android;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public User(String name) {
        this.name = name;
    }
}
