package com.a209350309.i_learn.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a209350309.i_learn.Courses;
import com.a209350309.i_learn.Db.DatabaseHelper;
import com.a209350309.i_learn.Entity.Course;

public class CourseService {
    private DatabaseHelper databaseHelper;
    public CourseService(Context context){
        databaseHelper =new DatabaseHelper(context);
    }

    public boolean AddCourse(Course course){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="insert into course(username,course_name,course_teacher) values(?,?,?)";
        Object obj[]={course.getUsername(),course.getCourse_name(),course.getCourse_teacher()};
        sdb.execSQL(sql,obj);
        System.out.println("555");
        return true;
    }

    public Cursor SelectCourse(String username){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="select * from course where username=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
//        cursor.close();
        return cursor;

    }
}
