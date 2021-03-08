package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.helloworld.Adapter.QueryAdapter;
import com.example.helloworld.DAO.WenDao;
import com.example.helloworld.date.WenDate;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class QueryActivity extends AppCompatActivity {
    private Intent intent;
    private EditText query_stuid;
    private WenDao wenDao;
    private WenDate wenDate;
    private List<WenDate> listWenDate=new ArrayList<>();
    private ListView query_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        intent=getIntent();
        query_stuid=(EditText)findViewById(R.id.query_stuid);
        query_stuid.setText(intent.getStringExtra("stuid"));
    }
    public void queryWenDate(View view){
        query_stuid=(EditText)findViewById(R.id.query_stuid);
        wenDao=new WenDao(this);
        listWenDate=wenDao.queryData("stuid",query_stuid.getText().toString());
        query_list=(ListView)findViewById(R.id.query_list);
        QueryAdapter queryAdapter=new QueryAdapter(QueryActivity.this,listWenDate);
        query_list.setAdapter(queryAdapter);
    }
}