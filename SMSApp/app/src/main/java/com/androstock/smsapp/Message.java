package com.androstock.smsapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "message")
public class Message {
    @PrimaryKey
    @NonNull
    String id;
    String _ID;
    String KEY_THREAD_ID;
    String KEY_NAME;
    String KEY_PHONE;
    String KEY_MSG;
    String KEY_TYPE;
    String KEY_TIMESTAMP;
    String KEY_TIME ;
    String Password;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getKEY_THREAD_ID() {
        return KEY_THREAD_ID;
    }

    public void setKEY_THREAD_ID(String KEY_THREAD_ID) {
        this.KEY_THREAD_ID = KEY_THREAD_ID;
    }

    public String getKEY_NAME() {
        return KEY_NAME;
    }

    public void setKEY_NAME(String KEY_NAME) {
        this.KEY_NAME = KEY_NAME;
    }

    public String getKEY_PHONE() {
        return KEY_PHONE;
    }

    public void setKEY_PHONE(String KEY_PHONE) {
        this.KEY_PHONE = KEY_PHONE;
    }

    public String getKEY_MSG() {
        return KEY_MSG;
    }

    public void setKEY_MSG(String KEY_MSG) {
        this.KEY_MSG = KEY_MSG;
    }

    public String getKEY_TYPE() {
        return KEY_TYPE;
    }

    public void setKEY_TYPE(String KEY_TYPE) {
        this.KEY_TYPE = KEY_TYPE;
    }

    public String getKEY_TIMESTAMP() {
        return KEY_TIMESTAMP;
    }

    public void setKEY_TIMESTAMP(String KEY_TIMESTAMP) {
        this.KEY_TIMESTAMP = KEY_TIMESTAMP;
    }

    public String getKEY_TIME() {
        return KEY_TIME;
    }

    public void setKEY_TIME(String KEY_TIME) {
        this.KEY_TIME = KEY_TIME;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Message(@NonNull String id, String _ID, String KEY_THREAD_ID, String KEY_NAME, String KEY_PHONE, String KEY_MSG, String KEY_TYPE, String KEY_TIMESTAMP, String KEY_TIME, String password) {
        this.id = id;
        this._ID = _ID;
        this.KEY_THREAD_ID = KEY_THREAD_ID;
        this.KEY_NAME = KEY_NAME;
        this.KEY_PHONE = KEY_PHONE;
        this.KEY_MSG = KEY_MSG;
        this.KEY_TYPE = KEY_TYPE;
        this.KEY_TIMESTAMP = KEY_TIMESTAMP;
        this.KEY_TIME = KEY_TIME;
        Password = password;
    }

    public Message() {
    }
}
