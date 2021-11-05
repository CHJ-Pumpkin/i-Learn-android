package com.a209350309.i_learn.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a209350309.i_learn.Db.DatabaseHelper;

public class ExamService {
    private DatabaseHelper databaseHelper;
    public ExamService(Context context){
        databaseHelper =new DatabaseHelper(context);
    }

    public Cursor SelectExam(String course_name){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="select * from exams where course_name=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{course_name});
//        cursor.close();
        return cursor;

    }
}
