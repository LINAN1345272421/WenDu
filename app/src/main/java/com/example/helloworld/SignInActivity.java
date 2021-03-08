package com.example.helloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.helloworld.DAO.StuDao;
import com.example.helloworld.date.StudentDate;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private EditText text_sign_stuid;
    private EditText text_sign_stuphone;
    private StuDao studao;
    private List<StudentDate> listStuDate=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        text_sign_stuphone=(EditText)findViewById(R.id.sign_stuphone);
        text_sign_stuid=(EditText)findViewById(R.id.sign_stuid);
        SharedPreferences sp=getSharedPreferences("datesave",MODE_PRIVATE);
        text_sign_stuid.setText(sp.getString("stuid",""));
        text_sign_stuphone.setText(sp.getString("stuphone",""));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        text_sign_stuid=(EditText)findViewById(R.id.sign_stuid);
        text_sign_stuphone=(EditText)findViewById(R.id.sign_stuphone);
        if(data!=null){
            text_sign_stuid.setText(data.getStringExtra("stuid"));
            text_sign_stuphone.setText(data.getStringExtra("stuphone"));
            SharedPreferences.Editor editor=getSharedPreferences("datesave",MODE_PRIVATE).edit();
            editor.putString("stuid",text_sign_stuid.getText().toString());
            editor.putString("stuphone",text_sign_stuphone.getText().toString());
            editor.commit();
        }
    }
    public void onClickSign_sign(View view){
        text_sign_stuid=(EditText)findViewById(R.id.sign_stuid);
        text_sign_stuphone=(EditText)findViewById(R.id.sign_stuphone);
        studao=new StuDao(this);
        listStuDate=studao.queryData("stuid","%"+text_sign_stuid.getText().toString()+"%");
        System.out.println(listStuDate.size());
        if(listStuDate.size()!=0) {
            if(text_sign_stuphone.getText().toString().equals(listStuDate.get(0).getStuPhone())){
                SharedPreferences.Editor editor=getSharedPreferences("datesave",MODE_PRIVATE).edit();
                editor.putString("stuid",text_sign_stuid.getText().toString());
                editor.putString("stuphone",text_sign_stuphone.getText().toString());
                editor.commit();
                Intent intent=new Intent();
                intent.setClass(SignInActivity.this,MainActivity.class);
                intent.putExtra("stuid",text_sign_stuid.getText().toString());
                intent.putExtra("stuname",listStuDate.get(0).getStuName());
                intent.putExtra("stuclass",listStuDate.get(0).getStuClass());
                startActivity(intent);
            }else{
                listStuDate.clear();
            }
        }
        if(listStuDate.size()==0){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("提示").setIcon(R.mipmap.ic_launcher).setMessage("用户名或手机号错误");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog ad=builder.create();
            ad.show();
        }
    }
    public void onClickSign_reg(View view){
        Intent intent=new Intent();
        intent.setClass(SignInActivity.this,RegActivity.class);
        startActivityForResult(intent,1);
    }
}