package com.androstock.smsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface userDao {

@Insert
    void insert(User user);

    @Query("SELECT * FROM User")
    List<User> getAllUser();
    @Delete
    void delete(User user);



}
