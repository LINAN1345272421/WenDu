package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.DAO.StuDao;
import com.example.helloworld.date.StudentDate;

public class RegActivity extends AppCompatActivity {
    private EditText text_reg_stuid;
    private EditText text_reg_stuname;
    private EditText text_reg_stuphone;
    private EditText text_reg_stuclass;
    private StuDao studao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
    }
    public void onClickReg_reg(View view){
        text_reg_stuid=(EditText)findViewById(R.id.reg_stuid);
        text_reg_stuclass=(EditText)findViewById(R.id.reg_stuclass);
        text_reg_stuname=(EditText)findViewById(R.id.reg_stuname);
        text_reg_stuphone=(EditText)findViewById(R.id.reg_stuphone);
        StudentDate stuDate=new StudentDate(text_reg_stuclass.getText().toString(),
                text_reg_stuid.getText().toString(),
                text_reg_stuname.getText().toString(),
                text_reg_stuphone.getText().toString() );
        studao=new StuDao(this,stuDate);
        long flag=studao.insertDB();
        if(flag>0){
            Toast.makeText(RegActivity.this,"注册成功，请返回登录",Toast.LENGTH_LONG).show();
            Intent intent=new Intent();
            intent.putExtra("stuid",text_reg_stuid.getText().toString());
            intent.putExtra("stuphone",text_reg_stuphone.getText().toString());
            setResult(1,intent);
            finish();
        }
        else{
            Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_LONG).show();
        }
    }
}