package com.example.helloworld.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StuDBHelper extends SQLiteOpenHelper {
    private Context context;
    public StuDBHelper(Context context, String name, int version) {
        super(context, name, null, version);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table studate(" +
                "id integer primary key autoincrement, " +
                "stuid varchar, " +
                "stuname varchar, " +
                "stuphone varchar, " +
                "stuclass varchar)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
