package com.androstock.smsapp;

import android.content.Context;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import android.content.Context;
import androidx.room.Room;

@Database(entities = {User.class,Message.class},version = 3,exportSchema = false)
public abstract class userRoomDatabse extends RoomDatabase {
    public abstract userDao userDao();
    public abstract messageDao messageDao();
    public static userRoomDatabse noteRoomInstance;

    public static userRoomDatabse getDatabase(final Context context)
    {
        if(noteRoomInstance==null)
        {
                noteRoomInstance=Room.databaseBuilder(context.getApplicationContext(),userRoomDatabse.class,"user_database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return noteRoomInstance;
    }

    public static void destroyInstance() {
        noteRoomInstance = null;
    }
}

