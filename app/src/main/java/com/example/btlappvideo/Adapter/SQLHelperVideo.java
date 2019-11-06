package com.example.btlappvideo.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.btlappvideo.Model.HotVideo;
import java.util.ArrayList;
import java.util.List;

public class SQLHelperVideo extends SQLiteOpenHelper {

    final static String DB_NAME = "Video";
    final static  int DB_VERSION = 1;
    private static final String DB_NAME_TABLE = "VideoHistory";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    Cursor cursor;

    public SQLHelperVideo(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String querySQL = "CREATE TABLE VideoHistory (" +
                "id TEXT NOT NULL PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "avatar TEXT NOT NULL," +
                "file_mp4 TEXT NOT NULL," +
                "date_published TEXT NOT NULL" +
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

    public void insertVideoHistory(String id, String title, String avatar, String file_mp4, String date_published){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("title", title);
        contentValues.put("avatar", avatar);
        contentValues.put("file_mp4", file_mp4);
        contentValues.put("date_published", date_published);

        sqLiteDatabase.insert(DB_NAME_TABLE, null, contentValues);
    }

    public List<HotVideo> getAllProductAdvanced() {
        List<HotVideo> videoHistoryList = new ArrayList<>();
        HotVideo videoHistory;

        sqLiteDatabase = getReadableDatabase();
        cursor = sqLiteDatabase.query(false, DB_NAME_TABLE, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String avatar = cursor.getString(cursor.getColumnIndex("avatar"));
            String file_mp4 = cursor.getString(cursor.getColumnIndex("file_mp4"));
            String date_published = cursor.getString(cursor.getColumnIndex("date_published"));
            videoHistory = new HotVideo(id, title, avatar, file_mp4, date_published);
            videoHistoryList.add(videoHistory);
        }
        return videoHistoryList;
    }
}
