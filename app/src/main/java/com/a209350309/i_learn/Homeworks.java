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

import com.a209350309.i_learn.Service.HomeworkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Homeworks extends AppCompatActivity {
    private HomeworkService homeworkService=null;
    private ListView listView3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework);


        homeworkService=new HomeworkService(Homeworks.this);
        listView3 = (ListView)findViewById(R.id.listView3);
        show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_option_homework,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_LONG).show();
        switch (item.getItemId()){
            case R.id.menu_homework:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void show(){
        Intent intent =getIntent();
        String course_name = intent.getStringExtra("course_name");
        System.out.println(course_name);
        Cursor homework=homeworkService.SelectHomework(course_name);
        List<HashMap<String ,Object>> data3 = new ArrayList<HashMap<String ,Object>>();
        while (homework.moveToNext()){
            HashMap<String , Object> item3 = new HashMap<String , Object>();
            item3.put("homeworkId_item3",homework.getString(homework.getColumnIndex("id")));
            item3.put("homework_name_item3",homework.getString(homework.getColumnIndex("homework_name")));
            item3.put("homework_content_item3",homework.getString(homework.getColumnIndex("homework_content")));
            data3.add(item3);
            System.out.println(homework.getString(homework.getColumnIndex("homework_name")));
        }

        SimpleAdapter adapter = new SimpleAdapter(Homeworks.this, data3, R.layout.item3,
                new String[]{"homeworkId_item3","homework_name_item3", "homework_content_item3"}, new int[]{R.id.homeworkId_item3,R.id.homework_name_item3,R.id.homework_content_item3});
        listView3.setAdapter(adapter);
    }
}