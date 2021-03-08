package com.example.helloworld.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.helloworld.CDateTime;
import com.example.helloworld.DBHelper.MyDBHelper;
import com.example.helloworld.date.StudentDate;
import com.example.helloworld.date.WenDate;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class WenDao {
    private Context context;
    private WenDate wenDate;
    private MyDBHelper mydbh;
    private SQLiteDatabase sqldb;
    private List<WenDate> listWenDate=new ArrayList<>();
    public WenDao(Context context,WenDate wenDate) {
        this.context=context;
        this.wenDate=wenDate;
        mydbh=new MyDBHelper(context,"wendudb.db",1);
        sqldb=mydbh.getReadableDatabase();
    }
    public WenDao(Context context){
        this.context=context;
        mydbh=new MyDBHelper(context,"wendudb.db",1);
        sqldb=mydbh.getReadableDatabase();
    }
    public long insertDB()
    {
        ContentValues contentvalues=new ContentValues();
        contentvalues.put("stuid",wenDate.getStuid());
        contentvalues.put("address",wenDate.getAddress());
        contentvalues.put("wendu", wenDate.getWendu());
        contentvalues.put("dateandtime",wenDate.getDateandtime());
        contentvalues.put("special",wenDate.getSpecial());
        contentvalues.put("name",wenDate.getName());
        contentvalues.put("stuclass",wenDate.getStuclass());
        contentvalues.put("msec",wenDate.getMsec());
        long flag=sqldb.insert("wendudate",null,contentvalues);
        return flag;
    }
    public List<WenDate> queryData(String querystr, String str)
    {
        String [] strs={str};
        Cursor cursor=sqldb.rawQuery("select * from wendudate where "+querystr+" like ?",strs);
        if(cursor.moveToFirst())
        {
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String stuid=cursor.getString(cursor.getColumnIndex("stuid"));
                String address=cursor.getString(cursor.getColumnIndex("address"));
                Float wendu=cursor.getFloat(cursor.getColumnIndex("wendu"));
                String dateandtime=cursor.getString(cursor.getColumnIndex("dateandtime"));
                String special=cursor.getString(cursor.getColumnIndex("special"));
                String stuclass=cursor.getString(cursor.getColumnIndex("stuclass"));
                Long msec=cursor.getLong(cursor.getColumnIndex("msec"));
                System.out.println("wendudate：：："+name+stuid+address+wendu+dateandtime+special+stuclass+msec);
                wenDate =new WenDate(dateandtime,address,wendu,special,stuid,name,stuclass,msec);
                listWenDate.add(wenDate);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listWenDate;
    }
    public List<WenDate> queryDataFor(String querystr, String str)
    {
        String [] strs={str};
        Cursor cursor=sqldb.rawQuery("select * from wendudate where stuid=? order by msec desc",strs);
        if(cursor.moveToFirst())
        {
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String stuid=cursor.getString(cursor.getColumnIndex("stuid"));
                String address=cursor.getString(cursor.getColumnIndex("address"));
                Float wendu=cursor.getFloat(cursor.getColumnIndex("wendu"));
                String dateandtime=cursor.getString(cursor.getColumnIndex("dateandtime"));
                String special=cursor.getString(cursor.getColumnIndex("special"));
                String stuclass=cursor.getString(cursor.getColumnIndex("stuclass"));
                Long msec=cursor.getLong(cursor.getColumnIndex("msec"));
                System.out.println("wendudate：：："+name+stuid+address+wendu+dateandtime+special+stuclass+msec);
                wenDate =new WenDate(dateandtime,address,wendu,special,stuid,name,stuclass,msec);
                listWenDate.add(wenDate);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return listWenDate;
    }
    public void deleteDate(String deletestr,String str)
    {
        String [] strs={str};
        int flag=sqldb.delete("wendudate",deletestr+"=?",strs);
        System.out.println("FLAG"+flag);
    }
    public int getCount(){
        Cursor cursor = sqldb.rawQuery("select count(id) from wendudate",null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }
    public int getDifCount(String stuclass){
        CDateTime dt=new CDateTime();
        Long starttime=dt.startOfDay();
        Long endtime=dt.endOfDay();
        String[] strs={starttime.toString(),endtime.toString(),stuclass};
        Cursor cursor=sqldb.rawQuery("select count(*) from wendudate where ( msec between ? and ? ) and stuclass=? ",strs);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        cursor.close();
        return count;
    }
    public int getUnWen(String stuclass){
        CDateTime dt=new CDateTime();
        Long starttime=dt.startOfDay();
        Long endtime=dt.endOfDay();
        String[] strs={starttime.toString(),endtime.toString()};
        Cursor cursor=sqldb.rawQuery("select count(*) from wendudate where ( msec between ? and ? ) and wendu>=37 ",strs);
        cursor.moveToFirst();
        int count=cursor.getInt(0);
        cursor.close();
        return count;
    }
    public int queryForCame(String stuid,long msec){
        CDateTime dt=new CDateTime(msec);
        Long startTime=dt.startOfDay(msec);
        Long endTime=dt.endOfDay(msec);
        String [] strs={startTime.toString(),endTime.toString(),stuid};
        Cursor cursor=sqldb.rawQuery("select * from wendudate where ( msec between ? and ? ) and stuid=?",strs);
        if(cursor.getCount()>0){
            return 1;
        }else {
            return 0;
        }
    }
    public int updateData(WenDate wendate,long msec)
    {
        CDateTime dt=new CDateTime(msec);
        Long startTime=dt.startOfDay(msec);
        Long endTime=dt.endOfDay(msec);
        String [] strs={startTime.toString(),endTime.toString(),wendate.getStuid()};
        ContentValues contentvalues=new ContentValues();
        contentvalues.put("stuid",wenDate.getStuid());
        contentvalues.put("address",wenDate.getAddress());
        contentvalues.put("wendu", wenDate.getWendu());
        contentvalues.put("dateandtime",wenDate.getDateandtime());
        contentvalues.put("special",wenDate.getSpecial());
        contentvalues.put("name",wenDate.getName());
        contentvalues.put("stuclass",wenDate.getStuclass());
        contentvalues.put("msec",wenDate.getMsec());
        int flag=sqldb.update("wendudate",contentvalues,"( msec between ? and ? ) and ( stuid=? )",strs);
        return flag;
    }
}
