package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.helloworld.Adapter.BarChartFragment;
import com.example.helloworld.Adapter.LineChartFragment;
import com.example.helloworld.Adapter.PagerLIneAdapter;
import com.example.helloworld.DAO.StuDao;
import com.example.helloworld.DAO.WenDao;
import com.example.helloworld.date.StudentDate;
import com.example.helloworld.date.WenDate;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private LineChart map_Linechart;
    private EditText map_stuid;
    private Button map_btn;
    private ViewPager vpager_one;
    private List<Fragment> aList=new ArrayList<>();
    private PagerLIneAdapter mAdapter;
    private LineChartFragment lineChartFragment=new LineChartFragment();
    private BarChartFragment barChartFragment=new BarChartFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map_stuid=(EditText)findViewById(R.id.map_stuid);
        Intent intent=getIntent();
        String stuid=intent.getStringExtra("stuid");
        map_stuid=(EditText)findViewById(R.id.map_stuid);
        map_stuid.setText(stuid);
    }
    public void onClickMap(View view){
        Bundle bundle = new Bundle();
        bundle.putString("stuid",map_stuid.getText().toString());
        lineChartFragment.setArguments(bundle);
        barChartFragment.setArguments(bundle);
        vpager_one = (ViewPager) findViewById(R.id.map_show);
        aList = new ArrayList<Fragment>();
        aList.add(lineChartFragment);
        aList.add(barChartFragment);
        mAdapter = new PagerLIneAdapter(getSupportFragmentManager(),aList);
        vpager_one.setAdapter(mAdapter);
    }
}