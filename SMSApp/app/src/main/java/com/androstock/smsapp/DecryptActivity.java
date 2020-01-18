package com.androstock.smsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.UUID;

public class DecryptActivity extends AppCompatActivity {

    Button btn_encrypt;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);


        password=findViewById(R.id.password);
        btn_encrypt=findViewById(R.id.btn_encrypt);
        btn_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(password.getText().toString()!="")
                {
                    Intent intent = getIntent();
                    String name = intent.getStringExtra("name");
                    String address = intent.getStringExtra("address");
                    String thread_id_main = intent.getStringExtra("thread_id");

                    boolean flag=true;
                    List<User> m=null;
                    userRoomDatabse databse=userRoomDatabse.getDatabase(getApplicationContext());
                    m=userViewModel.getAllUser(databse);
                    for(int i=0;i<m.size();i++)
                    {
                        if(address.equals(m.get(i).address  ))
                        {
                            if(password.getText().toString().equals(m.get(i).Password))
                            {
                                userViewModel.delete(databse,m.get(i));
                            }
                        }
                    }
                    Intent intent1=new Intent(DecryptActivity.this,Chat.class);
                    intent1.putExtra("name",name);
                    intent1.putExtra("address",address);
                    intent1.putExtra("thread_id",thread_id_main);
                    startActivity(intent1);
                }
            }
        });
    }
}
