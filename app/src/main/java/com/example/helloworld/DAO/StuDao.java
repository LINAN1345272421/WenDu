package com.example.helloworld.DAO;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.DBHelper.MyDBHelper;
import com.example.helloworld.DBHelper.StuDBHelper;
import com.example.helloworld.R;
import com.example.helloworld.date.StudentDate;

import java.util.ArrayList;
import java.util.List;

public class StuDao {
    private Context context;
    private StudentDate stuDate;
    private StuDBHelper studbh;
    private SQLiteDatabase sqldb;
    private List<StudentDate> listStudentDate=new ArrayList<>();
    private StudentDate studate;
    public StuDao(Context context,StudentDate stuDate) {
        this.context=context;
        this.stuDate=stuDate;
        studbh=new StuDBHelper(context,"astudb.db",1);
        sqldb=studbh.getReadableDatabase();
    }
    public StuDao(Context context){
        this.context=context;
        studbh=new StuDBHelper(context,"astudb.db",1);
        sqldb=studbh.getReadableDatabase();
    }
    public long insertDB()
    {
        ContentValues contentvalues=new ContentValues();
        contentvalues.put("stuid",stuDate.getStuID());
        contentvalues.put("stuname",stuDate.getStuName());
        contentvalues.put("stuphone",stuDate.getStuPhone());
        contentvalues.put("stuclass",stuDate.getStuClass());
        long flag=sqldb.insert("studate",null,contentvalues);
        return flag;
    }
    public List<StudentDate> queryData(String querystr,String str)
    {
        String [] strs={str};
        Cursor cursor=sqldb.rawQuery("select * from studate where "+querystr+" like ?",strs);
        if(cursor.moveToFirst())
        {
            do{
                String stuName=cursor.getString(cursor.getColumnIndex("stuname"));
                String stuId=cursor.getString(cursor.getColumnIndex("stuid"));
                String stuClass=cursor.getString(cursor.getColumnIndex("stuclass"));
                String stuPhone=cursor.getString(cursor.getColumnIndex("stuphone"));
                System.out.println(stuName+stuId+stuClass+stuPhone);
                studate=new StudentDate(stuClass,stuId,stuName,stuPhone);
                listStudentDate.add(studate);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listStudentDate;
    }
    public void deleteDate(String deletestr,String str)
    {
        String [] strs={str};
        int flag=sqldb.delete("studate",deletestr+"=?",strs);
        System.out.println("FLAG"+flag);
    }
    public int getCount(){
        Cursor cursor = sqldb.rawQuery("select count(id) from studate",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }
    public int getDifCount(String stuclass){
        String []strs={stuclass};
        Cursor cursor=sqldb.rawQuery("select count(*) from studate where stuclass=? ",strs);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        cursor.close();
        return count;
    }
}
