package com.example.btlappvideo.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.btlappvideo.Model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLHelperUser extends SQLiteOpenHelper {

    final static String DB_NAME = "Account";
    final static  int DB_VERSION = 1;
    private static final String DB_NAME_TABLE = "User";
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelperUser(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySQL = "CREATE TABLE User (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "fullname TEXT NOT NULL," +
                "email TEXT NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(querySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_NAME_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public void insertUser(String username, String password, String fullname, String email){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("fullname", fullname);
        contentValues.put("email", email);

        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
    }

    public List<User> getAllUserAdvanced() {
        List<User> userList = new ArrayList<>();
        User user;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_NAME_TABLE, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            user = new User(id, username, password, fullname, email);
            userList.add(user);
        }
        return userList;
    }
}
