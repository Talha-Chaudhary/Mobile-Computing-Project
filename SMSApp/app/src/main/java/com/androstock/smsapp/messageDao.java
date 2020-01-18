package com.androstock.smsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface messageDao {

    @Insert
    void insert(Message msg);

    @Query("SELECT * FROM message WHERE KEY_PHONE LIKE :phoneNumber AND Password LIKE :password  ")
    List<Message> getAllUser(String phoneNumber, String password);

    @Query("DELETE FROM message WHERE KEY_PHONE= :phoneNumber AND Password= :password")
    void delete(String phoneNumber,String password);


}
