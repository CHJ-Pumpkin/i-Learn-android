package com.a209350309.i_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a209350309.i_learn.Entity.Course;
import com.a209350309.i_learn.Service.CourseService;

public class Courses extends AppCompatActivity {
    EditText text_course_name;
    EditText text_course_teacher;
    Button btnAddCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        text_course_name=findViewById(R.id.text_course_name);
        text_course_teacher=findViewById(R.id.text_course_teacher);
        btnAddCourse=findViewById(R.id.btnAddCourse);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);//fragment中获取SharedPreferences需要多加一步getActivity()方法
        String username = preferences.getString("username", "");

        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_name=text_course_name.getText().toString();
                String teacher=text_course_teacher.getText().toString();
                CourseService courseService=new CourseService(Courses.this);
                Course course=new Course();
                course.setCourse_name(course_name);
                course.setCourse_teacher(teacher);
                course.setUsername(username);
                boolean flag=courseService.AddCourse(course);
                if (flag){
                    Toast.makeText(Courses.this, "添加完毕", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Courses.this, Test.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Courses.this, "添加失败", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}