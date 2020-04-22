package com.shinta.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.username);
        mTextPassword = (EditText)findViewById(R.id.password);
        mTextCnfPassword = (EditText)findViewById(R.id.confirm_password);
        mButtonRegister = (Button)findViewById(R.id.registerBtn);
        mTextViewLogin = (TextView) findViewById(R.id.register);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if (pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd);
                    if (val > 0){
                        Toast.makeText(RegisterActivity.this,"You have Register", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveToLogin);
                    }

                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Password is Not Matching", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
