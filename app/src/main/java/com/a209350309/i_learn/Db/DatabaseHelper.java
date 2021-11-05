package com.a209350309.i_learn.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="i_learn.db";
    static int dbVersion=1;
    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }
    //只在创建的时候用一次
    public void onCreate(SQLiteDatabase db) {
        String sql="create table user(id integer primary key autoincrement,username varchar(20),telephone varchar(20),password varchar(20))";
        db.execSQL(sql);
        db.execSQL("create table notes(id integer primary key autoincrement,username varchar(20),subject varchar(20),content varchar(100),write_time datetime)");
        db.execSQL("create table course(id integer primary key autoincrement,username varchar(20),course_name varchar(20),course_teacher varchar(20))");
        db.execSQL("create table homeworks(id integer primary key autoincrement,course_name varchar(20),homework_name varchar(20),homework_content varchar(100))");
        db.execSQL("create table exams(id integer primary key autoincrement,course_name varchar(20),exam_name varchar(20),exam_content varchar(100))");
        db.execSQL("create table messages(id integer primary key autoincrement,username varchar(20),message_content varchar(100),message_time varchar(20))");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
