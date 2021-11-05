package com.a209350309.i_learn.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a209350309.i_learn.Db.DatabaseHelper;

public class MessageService {
    private DatabaseHelper databaseHelper;
    public MessageService(Context context){
        databaseHelper =new DatabaseHelper(context);
    }

    public Cursor SelectMessage(String username){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="select * from messages where username=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
//        cursor.close();
        return cursor;

    }
}
