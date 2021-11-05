package com.a209350309.i_learn.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a209350309.i_learn.Db.DatabaseHelper;
import com.a209350309.i_learn.Entity.User;

import java.util.List;

public class UserService {
    private DatabaseHelper databaseHelper;
    public UserService(Context context){
        databaseHelper =new DatabaseHelper(context);
    }

    //登录用
    public boolean login(String username,String password){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    //注册用
    public boolean register(User user){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="insert into user(username,telephone,password) values(?,?,?)";
        Object obj[]={user.getUsername(),user.getTelephone(),user.getPassword()};
        sdb.execSQL(sql,obj);
        System.out.println("333");
        return true;
    }

    //查询
    public Cursor SelectLogin(String username){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="select * from user where username=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
//        cursor.close();
        return cursor;

    }
}
