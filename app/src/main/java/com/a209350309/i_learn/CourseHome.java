package com.a209350309.i_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CourseHome extends AppCompatActivity {
    Button btnhomework;
    Button btnexam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_home);

        Intent intent =getIntent();
        String course_name = intent.getStringExtra("course_name_item2");
        System.out.println(course_name);
        btnhomework=findViewById(R.id.btnhomework);
        btnexam=findViewById(R.id.btnexam);

        btnhomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CourseHome.this, Homeworks.class);
                intent.putExtra("course_name",course_name);
                startActivity(intent);
            }
        });

        btnexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CourseHome.this, Exams.class);
                intent.putExtra("course_name",course_name);
                startActivity(intent);
            }
        });
    }
}