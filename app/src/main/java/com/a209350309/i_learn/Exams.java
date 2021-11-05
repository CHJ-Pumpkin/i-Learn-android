package com.a209350309.i_learn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.a209350309.i_learn.Service.ExamService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Exams extends AppCompatActivity {
    private ExamService examService=null;
    private ListView listView4 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);

        examService=new ExamService(Exams.this);
        listView4 = (ListView)findViewById(R.id.listView4);
        show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_option_exam,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_LONG).show();
        switch (item.getItemId()){
            case R.id.menu_exam:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void show(){
        Intent intent =getIntent();
        String course_name = intent.getStringExtra("course_name");
        System.out.println(course_name);
        Cursor exam=examService.SelectExam(course_name);
        List<HashMap<String ,Object>> data4 = new ArrayList<HashMap<String ,Object>>();
        while (exam.moveToNext()){
            HashMap<String , Object> item4 = new HashMap<String , Object>();
            item4.put("examId_item4",exam.getString(exam.getColumnIndex("id")));
            item4.put("exam_name_item4",exam.getString(exam.getColumnIndex("exam_name")));
            item4.put("exam_content_item4",exam.getString(exam.getColumnIndex("exam_content")));
            data4.add(item4);
            System.out.println(exam.getString(exam.getColumnIndex("exam_name")));
        }

        SimpleAdapter adapter = new SimpleAdapter(Exams.this, data4, R.layout.item4,
                new String[]{"examId_item4","exam_name_item4", "exam_content_item4"}, new int[]{R.id.examId_item4,R.id.exam_name_item4,R.id.exam_content_item4});
        listView4.setAdapter(adapter);
    }
}