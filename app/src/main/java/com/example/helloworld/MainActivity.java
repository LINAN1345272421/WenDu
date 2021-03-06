package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.baidu.entity.pb.PoiResult;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.example.helloworld.DAO.StuDao;
import com.example.helloworld.DAO.WenDao;
import com.example.helloworld.DBHelper.MyDBHelper;
import com.example.helloworld.DBHelper.StuDBHelper;
import com.example.helloworld.date.StudentDate;
import com.example.helloworld.date.WenDate;

import org.w3c.dom.Text;

import java.security.KeyPairGenerator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText text_main_name;
    private EditText text_main_time;
    private EditText text_main_wendu;
    private EditText text_main_address;
    private WenDate wendate=new WenDate();
    private TextView mTv = null;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private Intent intent;
    private CheckBox main_box_1;
    private CheckBox main_box_2;
    private CheckBox main_box_3;
    private CheckBox main_box_4;
    private CheckBox main_box_5;
    private WenDao wendao;
    private StuDao studao;
    private CDateTime dt=new CDateTime();
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //?????????BDLocation?????????????????????????????????????????????get??????????????????????????????????????????
            //??????????????????????????????????????????????????????
            //??????????????????????????????????????????????????????BDLocation???????????????
            String addr = location.getAddrStr();    //????????????????????????
            String country = location.getCountry();    //????????????
            String province = location.getProvince();    //????????????
            String city = location.getCity();    //????????????
            String district = location.getDistrict();    //????????????
            String street = location.getStreet();    //??????????????????
            String adcode = location.getAdCode();    //??????adcode
            String town = location.getTown();
            //??????????????????
            text_main_address=(EditText)findViewById(R.id.main_address);
            text_main_address.setText(country+province+city+district+town+street);
        }
    }
    public void autoAddress(View view)
    {
        mLocationClient = new LocationClient(getApplicationContext());
        //??????LocationClient???
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        mLocationClient.setLocOption(option);
        //??????????????????
        mLocationClient.start();
    }
    public void autoTimeAndDate(View view)
    {
        text_main_time=(EditText)findViewById(R.id.main_time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd??? HH:mm");// HH:mm:ss
//??????????????????
        Date date = new Date(System.currentTimeMillis());
        text_main_time.setText(simpleDateFormat.format(date));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("MainActivity","MainActivity??????");
        // MyDBHelper mydbh=new MyDBHelper(this,"mydb.db",1);
        //SQLiteDatabase sqldb=mydbh.getReadableDatabase();
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        intent=getIntent();
        text_main_name=(EditText)findViewById(R.id.main_name);
        text_main_name.setText(intent.getStringExtra("stuname"));
    }
    public void createDB(View view)
    {
    }
    public void insertDB(View view) throws ParseException {
        text_main_time = (EditText) findViewById(R.id.main_time);
        text_main_address = (EditText) findViewById(R.id.main_address);
        text_main_name = (EditText) findViewById(R.id.main_name);
        text_main_wendu = (EditText) findViewById(R.id.main_wendu);
        main_box_1 = (CheckBox) findViewById(R.id.main_box_1);
        main_box_2 = (CheckBox) findViewById(R.id.main_box_2);
        main_box_3 = (CheckBox) findViewById(R.id.main_box_3);
        main_box_4 = (CheckBox) findViewById(R.id.main_box_4);
        main_box_5 = (CheckBox) findViewById(R.id.main_box_5);
        String time = text_main_time.getText().toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy???MM???dd??? HH:mm");
        Date date =  simpleDateFormat.parse(time);
        long msec=date.getTime();
        String address = text_main_address.getText().toString();
        String name = text_main_name.getText().toString();
        Float wendu = Float.parseFloat(text_main_wendu.getText().toString());
        String stuid = intent.getStringExtra("stuid");
        String stuclass=intent.getStringExtra("stuclass");
        String special = "";
        if (main_box_1.isChecked()) { special =special+main_box_1.getText().toString() + "|"; }
        if (main_box_2.isChecked()) { special =special+main_box_2.getText().toString() + "|"; }
        if (main_box_3.isChecked()) { special =special+main_box_3.getText().toString() + "|"; }
        if (main_box_4.isChecked()) { special =special+main_box_4.getText().toString() + "|"; }
        if (main_box_5.isChecked()) { special =special+main_box_5.getText().toString() + "|"; }
        wendate=new WenDate(time,address,wendu,special,stuid,name,stuclass,msec);
        wendao=new WenDao(this,wendate);
        if(wendao.queryForCame(intent.getStringExtra("stuid"),msec)==0){
            long flag=wendao.insertDB();
            if(flag>0){
                Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_LONG).show();
            }
        }else{
            wendao.updateData(wendate,msec);
            Toast.makeText(MainActivity.this,"?????????????????????",Toast.LENGTH_LONG).show();
        }
    }
    public void queryWenDate(View view){
        intent=getIntent();
        Intent intent1=new Intent();
        intent1.putExtra("stuid",intent.getStringExtra("stuid"));
        intent1.setClass(MainActivity.this,QueryActivity.class);
        startActivity(intent1);
    }
    public void situationWendu(View view){
        intent=getIntent();
        TextView main_num_txt=(TextView)findViewById(R.id.main_num_txt);
        TextView main_num=(TextView)findViewById(R.id.main_num);
        TextView main_un_txt=(TextView)findViewById(R.id.main_un_txt);
        TextView main_un=(TextView)findViewById(R.id.main_un);
        TextView main_no_txt=(TextView)findViewById(R.id.main_no_txt);
        TextView main_no=(TextView)findViewById(R.id.main_no);
        TextView main_sate=(TextView)findViewById(R.id.main_sate);
        TextView main_sate_txt=(TextView)findViewById(R.id.main_sate_txt);
        wendao=new WenDao(this);
        studao=new StuDao(this);
        int now=wendao.getDifCount(intent.getStringExtra("stuclass"));
        int un=wendao.getUnWen(intent.getStringExtra("stuclass"));
        int sum=studao.getDifCount(intent.getStringExtra("stuclass"));
        System.out.println("??????????????????"+now);
        System.out.println("???????????????"+wendao.getCount());
        System.out.println("???????????????"+un);
        System.out.println("????????????"+intent.getStringExtra("stuclass")+":"+sum);
        studao.queryData("stuclass","%%");
        wendao.queryData("stuid","%%");
        main_no.setText(sum-now+"");
        main_num.setText(now+"");
        main_un.setText(un+"");
        main_no_txt.setText("?????????");
        main_num_txt.setText("????????????");
        main_un_txt.setText("????????????");
        main_sate.setText(intent.getStringExtra("stuclass"));
        main_sate_txt.setText("?????????????????????????????????????????????");
    }
    public void toForm(View view){
        Intent intent1=new Intent();
        intent=getIntent();
        intent1.setClass(MainActivity.this,FormActivity.class);
        intent1.putExtra("stuid", intent.getStringExtra("stuid"));
        startActivity(intent1);
    }
    public void toMap(View view){
        Intent intent1=new Intent();
        intent=getIntent();
        intent1.setClass(MainActivity.this,MapActivity.class);
        intent1.putExtra("stuid", intent.getStringExtra("stuid"));
        startActivity(intent1);
    }
    public void toMapshow(View view){
        Intent intent1=new Intent();
        intent1.setClass(MainActivity.this,MapshowActivity.class);
        startActivity(intent1);
    }
}