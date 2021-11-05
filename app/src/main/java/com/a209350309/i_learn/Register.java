package com.a209350309.i_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a209350309.i_learn.Entity.User;
import com.a209350309.i_learn.Service.UserService;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class Register extends AppCompatActivity {
    EditText etName;
    EditText etPassword;
    EditText etPassword2;
    EditText etTelephone;
    Button btnRegister;
    TextView toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SQLiteStudioService.instance().start(this);
        etName=findViewById(R.id.etName);
        etPassword=findViewById(R.id.etPassword);
        etPassword2=findViewById(R.id.etPassword2);
        etTelephone=findViewById(R.id.etTelephone);
        btnRegister=findViewById(R.id.btnRegister);
        toLogin=findViewById(R.id.toLogin);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etName.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                String password2=etPassword2.getText().toString().trim();
                String telephone=etTelephone.getText().toString().trim();
                if (!password.equals(password2)){
                    Toast.makeText(Register.this,"两次密码不一致！",Toast.LENGTH_LONG).show();
                }else {
                    UserService uService=new UserService(Register.this);
                    User user=new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setTelephone(telephone);
                    System.out.println("111");
                    uService.register(user);
                    System.out.println("222");
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }


}