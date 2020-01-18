package com.androstock.smsapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class PasswordEntity extends AppCompatActivity {

    //public userViewModel nvm;

    Button btn_encrypt;
    LoadSms loadsmsTask;
    EditText password;
    EditText confirmPassword;
    String name;
    String address;
    int thread_id_main;
    private Handler handler = new Handler();
    Thread t;
    ArrayList<HashMap<String, String>> smsList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> customList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> tmpList = new ArrayList<HashMap<String, String>>();

    userRoomDatabse userdatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_entity);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        btn_encrypt=findViewById(R.id.btn_encrypt);
        startLoadingSms();
        btn_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    Intent intent = getIntent();
                    name = intent.getStringExtra("name");
                    address = intent.getStringExtra("address");
                    thread_id_main = Integer.parseInt(intent.getStringExtra("thread_id"));

                    userdatabse=userRoomDatabse.getDatabase(getApplicationContext());
                    userViewModel.insert(userdatabse,new User(UUID.randomUUID().toString(),name,address,thread_id_main+"",password.getText().toString()));
                    if(insert())
                    {
                        Intent intent1=new Intent(PasswordEntity.this,Chat.class);
                        intent1.putExtra("name",name);
                        intent1.putExtra("address",address);
                        intent1.putExtra("thread_id",thread_id_main);

                        startActivity(intent1);
                    }
                }
            }
        });

        //nvm.insert(new Message("username","text","id"));

    }
    public boolean insert()
    {
        try{
        //userRoomDatabse msgdatabse=userRoomDatabse.getDatabase(getApplicationContext());
        for ( int i=0; i<smsList.size();i++){
            userViewModel.insert(userdatabse,new Message(UUID.randomUUID().toString(),smsList.get(i).get(Function._ID),smsList.get(i).get(Function.KEY_THREAD_ID),smsList.get(i).get(Function.KEY_NAME),smsList.get(i).get(Function.KEY_PHONE),smsList.get(i).get(Function.KEY_MSG),smsList.get(i).get(Function.KEY_TYPE),smsList.get(i).get(Function.KEY_TIMESTAMP),smsList.get(i).get(Function.KEY_TIME),password.getText().toString()));
        }

        return true;}
        catch(Exception e)
        {
            return  false;
        }
    }




    class LoadSms extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tmpList.clear();
        }

        protected String doInBackground(String... args) {
            String xml = "";

            try {
                Uri uriInbox = Uri.parse("content://sms/inbox");
                Cursor inbox = getContentResolver().query(uriInbox, null, "thread_id=" + thread_id_main, null, null);
                Uri uriSent = Uri.parse("content://sms/sent");
                Cursor sent = getContentResolver().query(uriSent, null, "thread_id=" + thread_id_main, null, null);
                Cursor c = new MergeCursor(new Cursor[]{inbox, sent}); // Attaching inbox and sent sms


                if (c.moveToFirst()) {
                    for (int i = 0; i < c.getCount(); i++) {
                        String phone = "";
                        String _id = c.getString(c.getColumnIndexOrThrow("_id"));
                        String thread_id = c.getString(c.getColumnIndexOrThrow("thread_id"));
                        String msg = c.getString(c.getColumnIndexOrThrow("body"));
                        String type = c.getString(c.getColumnIndexOrThrow("type"));
                        String timestamp = c.getString(c.getColumnIndexOrThrow("date"));
                        phone = c.getString(c.getColumnIndexOrThrow("address"));

                        tmpList.add(Function.mappingInbox(_id, thread_id, name, phone, msg, type, timestamp, Function.converToTime(timestamp)));
                        c.moveToNext();
                    }
                }
                c.close();

            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Collections.sort(tmpList, new MapComparator(Function.KEY_TIMESTAMP, "asc"));

            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            smsList.clear();
            smsList.addAll(tmpList);

        }
    }


    public void startLoadingSms()
    {
        final Runnable r = new Runnable() {
            public void run() {

                loadsmsTask = new LoadSms();
                loadsmsTask.execute();

                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(r, 0);
    }




}
