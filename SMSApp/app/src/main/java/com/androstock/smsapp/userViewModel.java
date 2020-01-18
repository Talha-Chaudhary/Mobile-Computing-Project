package com.androstock.smsapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class userViewModel  {
    public userDao notedao;
    public userRoomDatabse database;
    public List<User> mUser;


  /*  public userViewModel(@NonNull Application application) {
        super(application);
        database=userRoomDatabse.getDatabase(application);
        notedao=database.userDao();
        mUser=notedao.getAllUser();
    }*/
    public static void insert(final userRoomDatabse db,User user){
        db.userDao().insert(user);
    }

    public static void delete(final userRoomDatabse db,User user)
    {
        db.userDao().delete(user);
    }

    public  static List<User> getAllUser(final userRoomDatabse db)
    {
        List<User> m=db.userDao().getAllUser();;
        return m;
    }


    public static void insert(final userRoomDatabse db,Message message){
        db.messageDao().insert(message);
    }

    public static void delete(final userRoomDatabse db,String phoneNumber,String Password)
    {
        db.messageDao().delete(phoneNumber,Password);
    }

    public  static List<Message> getAllUser(final userRoomDatabse db,String phoneNumber,String Password)
    {
        return db.messageDao().getAllUser(phoneNumber,Password);
    }

}
