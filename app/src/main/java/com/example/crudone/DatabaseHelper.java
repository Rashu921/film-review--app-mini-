package com.example.crudone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String databaseName = "SliteDb";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SliteDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {

        MyDatabase.execSQL("create Table users(email TEXT primary key, password TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop Table if exists users");

        onCreate(MyDB);
    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);//insert
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});
        if(cursor.getCount() > 0) {   //rslt Q
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public SQLiteStatement compileStatement(String sql) {
        return null;
    }

}
