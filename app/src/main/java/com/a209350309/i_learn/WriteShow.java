package com.a209350309.i_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a209350309.i_learn.Service.NoteService;
import com.a209350309.i_learn.ui.me.MeFragment;
import com.a209350309.i_learn.ui.notes.NotesFragment;

public class WriteShow extends AppCompatActivity {
    TextView show_text_write_title;
    TextView show_text_write_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_show);

        Intent intent =getIntent();
        String writeId = intent.getStringExtra("writeId");

        NoteService noteService=new NoteService(WriteShow.this);
        Cursor note=noteService.SelectNote(writeId);

        show_text_write_title=findViewById(R.id.show_text_write_title);
        show_text_write_content=findViewById(R.id.show_text_write_content);


        if (note==null){
            Toast.makeText(WriteShow.this, "您的notes表中无数据！！！", Toast.LENGTH_SHORT).show();
        }else {
            while (note.moveToNext()){
                String title=note.getString(note.getColumnIndex("subject"));
                String content=note.getString(note.getColumnIndex("content"));
                System.out.println(title+"|"+content);
                show_text_write_title.setText(title);
                show_text_write_content.setText(content);
            }
        }
    }
}