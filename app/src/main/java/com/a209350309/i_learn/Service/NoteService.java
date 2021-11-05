package com.a209350309.i_learn.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a209350309.i_learn.Db.DatabaseHelper;
import com.a209350309.i_learn.Entity.Note;
import com.a209350309.i_learn.Entity.User;

public class NoteService {
    private DatabaseHelper databaseHelper;
    public NoteService(Context context){
        databaseHelper =new DatabaseHelper(context);
    }

    public boolean note_write(Note note){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="insert into notes(username,subject,content,write_time) values(?,?,?,?)";
        Object obj[]={note.getUsername(),note.getSubject(),note.getContent(),note.getWrite_time()};
        sdb.execSQL(sql,obj);
        System.out.println("444");
        return true;
    }

    public Cursor SelectNotes(String username){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="select * from notes where username=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
//        cursor.close();
        return cursor;

    }

    public Cursor SelectNote(String writeId){
        SQLiteDatabase sdb= databaseHelper.getReadableDatabase();
        String sql="select * from notes where id=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{writeId});
//        cursor.close();
        return cursor;

    }
}
