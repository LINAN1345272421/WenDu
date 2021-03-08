package com.example.helloworld.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    public MyDBHelper(Context context, String name, int version) {
        super(context, name, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table wendudate(" +
                "id integer primary key autoincrement, " +
                "address varchar, " +
                "name varchar, " +
                "wendu float, " +
                "dateandtime varchar," +
                "special varchar," +
                "stuid varchar," +
                "msec long," +
                "stuclass varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
