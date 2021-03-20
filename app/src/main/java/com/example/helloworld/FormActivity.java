package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.helloworld.DAO.StuDao;
import com.example.helloworld.DAO.WenDao;
import com.example.helloworld.date.StudentDate;
import com.example.helloworld.date.WenDate;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class FormActivity extends AppCompatActivity {
    private StuDao stuDao;
    private WenDao wenDao;
    private WenDate wenDate;
    private StudentDate stuDate;
    private List<WenDate> listwendate=new ArrayList<>();
    private List<StudentDate> liststudate=new ArrayList<>();
    private TextView form_time;
    private TextView form_name;
    private TextView form_stuid;
    private TextView form_wendu;
    private TextView form_phone;
    private ListView form_list;
    private EditText show_stuid;
    private TextView show_show;
    private  final int REQUEST_EXTERNAL_STORAGE = 1;
    private  String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        Intent intent=getIntent();
        String stuid=intent.getStringExtra("stuid");
        show_stuid=(EditText)findViewById(R.id.show_stuid);
        show_stuid.setText(stuid);
    }
    public void startForm(View view){
        show_stuid=(EditText)findViewById(R.id.show_stuid);
        show_show=(TextView)findViewById(R.id.show_show);
        String stuid=show_stuid.getText().toString();
        stuDao=new StuDao(this);
        liststudate=stuDao.queryData("stuid",stuid);
        stuDate=liststudate.get(0);
        wenDao=new WenDao(this);
        listwendate=wenDao.queryDataFor("stuid",stuid);
        wenDate=listwendate.get(0);
        try {
            System.out.println(Environment.getExternalStorageDirectory());
            Workbook wb=Workbook.getWorkbook(new File("/storage/emulated/0/$MuMu共享文件夹/excel1.xls"));
            WritableWorkbook wbook = wb.createWorkbook(new File("/storage/emulated/0/$MuMu共享文件夹/excel1.xls"),wb);
// 创建一个工作表 第一个工作区
            WritableSheet wsheet = wbook.getSheet(0);
            Label time = new Label(5, 1, wenDate.getDateandtime());wsheet.addCell(time);
            Label name = new Label(1, 2, stuDate.getStuName());wsheet.addCell(name );
            Label stuId = new Label(5, 2, stuDate.getStuID());wsheet.addCell(stuId);
            if(wenDate.getWendu()<37){
                Label wendusitu = new Label(1, 3, "良好");wsheet.addCell(wendusitu);
            }else {
                Label wendusitu = new Label(1, 3, "发热");wsheet.addCell(wendusitu);
            }
            Label phone = new Label(5, 3, stuDate.getStuPhone());wsheet.addCell(phone);
            if(listwendate.size()<14){
                for(int i=0;i<listwendate.size();i++){
                    Label Time=new Label(0,6+i,listwendate.get(i).getDateandtime());wsheet.addCell(Time);
                    Label wendu = new Label(1, 6+i, listwendate.get(i).getWendu().toString());wsheet.addCell(wendu);
                    if(listwendate.get(i).getWendu()<37){
                        Label situation = new Label(2, 6+i,"良好");wsheet.addCell(situation);
                    }else{
                        Label situation = new Label(2, 6+i,"发热");wsheet.addCell(situation);
                    }
                    Label address = new Label(3, 6+i,listwendate.get(i).getAddress());wsheet.addCell(address);
                    Label special = new Label(5, 6+i,listwendate.get(i).getSpecial());wsheet.addCell(special);
                }
            }else {
                for(int i=0;i<14;i++){
                    Label Time=new Label(0,6+i,listwendate.get(i).getDateandtime());wsheet.addCell(Time);
                    Label wendu = new Label(1, 6+i, listwendate.get(i).getWendu().toString());wsheet.addCell(wendu);
                    if(listwendate.get(i).getWendu()<37){
                        Label situation = new Label(2, 6+i,"良好");wsheet.addCell(situation);
                    }else{
                        Label situation = new Label(2, 6+i,"发热");wsheet.addCell(situation);
                    }
                    Label address = new Label(3, 6+i,listwendate.get(i).getAddress());wsheet.addCell(address);
                    Label special = new Label(5, 6+i,listwendate.get(i).getSpecial());wsheet.addCell(special);
                }
            }
// 把值加到工作表中
// 写入文件
            wbook.write();
            wbook.close();
            show_show.setText("14天健康表生成成功！");
            System.out.println("创建成功!");
        } catch (Exception e) {
// TODO: handle exception
            e.printStackTrace();
        }
    }
    public  void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
    public void Null(){
                /*
        form_time=(TextView)findViewById(R.id.form_time);
        form_name=(TextView)findViewById(R.id.form_name);
        form_phone=(TextView)findViewById(R.id.form_phone);
        form_stuid=(TextView)findViewById(R.id.form_stuid);
        form_wendu=(TextView)findViewById(R.id.form_wendu);
        form_name.setText(listwendate.get(0).getName());
        form_stuid.setText(listwendate.get(0).getStuid());
        form_phone.setText(stuDate.getStuPhone());
        if(listwendate.get(0).getWendu()<37){
            form_wendu.setText("良好");
        }else{
            form_wendu.setText("发热");
        }
        String[] date=listwendate.get(0).getDateandtime().split(" ");
        form_time.setText(date[0]);;*/
        // 在path路径下建立一bai个excel文件
//            File extDir = Environment.getExternalStorageDirectory();
//            File file=new File( extDir.getAbsolutePath() + extDir.pathSeparatorChar +"\\test222.txt");
//            if (!file.exists()) {
//                try {
//                    verifyStoragePermissions(this);
//                    file.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
    }
}