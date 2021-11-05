package com.a209350309.i_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a209350309.i_learn.Service.UserService;

public class Login extends AppCompatActivity {
    EditText etNameLogin;
    EditText etPasswordLogin;
    Button btnLogin;
    TextView toRegister;
    CheckBox cbRemember;
    private SharedPreferences sp;
    private SharedPreferences sp_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp_item=getSharedPreferences("user_item",MODE_PRIVATE);

        etNameLogin=findViewById(R.id.etNameLogin);
        etPasswordLogin=findViewById(R.id.etPasswordLogin);
        cbRemember=findViewById(R.id.cbRemember);
        btnLogin=findViewById(R.id.btnLogin);
        toRegister=findViewById(R.id.toRegister);
        String name=sp_item.getString("username",null);
        System.out.println(name);
        if (name!=null){
            etNameLogin.setText(sp_item.getString("username",""));
            etPasswordLogin.setText(sp_item.getString("password",""));
            cbRemember.setChecked(true);
        }else {
            cbRemember.setChecked(false);
            System.out.println("111"+cbRemember.isChecked());
        }

        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etNameLogin.getText().toString();
                String password = etPasswordLogin.getText().toString();
                UserService uService = new UserService(Login.this);
                boolean flag = uService.login(username,password);

                if(flag){
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_LONG).show();
                    sp = getSharedPreferences("user",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",username);
                    editor.putString("password",password);
                    editor.commit();
                    boolean checkbox = cbRemember.isChecked();
                    if (checkbox==true){
                        sp_item = getSharedPreferences("user_item",MODE_PRIVATE);
                        SharedPreferences.Editor editor_item = sp_item.edit();
                        editor_item.putString("username",username);
                        editor_item.putString("password",password);
                        editor_item.commit();
                    }else {
                        sp_item = getSharedPreferences("user_item",MODE_PRIVATE);
                        SharedPreferences.Editor editor_item = sp_item.edit();
                        editor_item.putString("username",null);
                        editor_item.putString("password",null);
                        editor_item.commit();
                    }

                    Intent intent = new Intent(Login.this,Test.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}