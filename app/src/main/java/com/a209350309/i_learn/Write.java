package com.a209350309.i_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a209350309.i_learn.Entity.Note;
import com.a209350309.i_learn.Service.NoteService;
import com.a209350309.i_learn.Service.UserService;

import java.util.Calendar;


public class Write extends AppCompatActivity {
    EditText text_write_title;
    EditText text_write_content;
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        text_write_title=findViewById(R.id.text_write_title);
        text_write_content=findViewById(R.id.text_write_content);
        btnWrite=findViewById(R.id.btnWrite);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);//fragment中获取SharedPreferences需要多加一步getActivity()方法
        String username = preferences.getString("username", "");

        Calendar calendar = Calendar.getInstance();
        String write_time=String.valueOf(calendar.get(Calendar.DATE));
        System.out.println(write_time);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject=text_write_title.getText().toString();
                String content=text_write_content.getText().toString();
                NoteService noteService = new NoteService(Write.this);
                Note note=new Note();
                note.setUsername(username);
                note.setSubject(subject);
                note.setContent(content);
                note.setWrite_time(write_time);
                boolean flag=noteService.note_write(note);
                if (flag){
                    Toast.makeText(Write.this, "笔记完毕", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Write.this, Test.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Write.this, "笔记失败", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}