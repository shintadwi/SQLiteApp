package com.shinta.sqliteapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;


public class MainActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    ViewGroup progressView;
    protected boolean isProgressShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = findViewById(R.id.username);
        mTextPassword = findViewById(R.id.password);
        mButtonLogin = findViewById(R.id.loginBtn);
        mTextViewRegister = findViewById(R.id.register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.CheckUser(user, pwd);

                if(res==true)
                {
                    Toast.makeText(MainActivity.this,"Succesfully Logged In", Toast.LENGTH_SHORT).show();
                    Intent contentIntent = new Intent(MainActivity.this, ContentActivity.class);
                    startActivity(contentIntent);;

                }

                else{
                    Toast.makeText(MainActivity.this,"Username atau Password Anda Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
