package com.example.roomdb_in_android;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
