package com.example.helloworld.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.helloworld.DAO.StuDao;
import com.example.helloworld.DAO.WenDao;
import com.example.helloworld.R;
import com.example.helloworld.date.StudentDate;
import com.example.helloworld.date.WenDate;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class BarChartFragment extends Fragment {
    private StudentDate stuDate;
    private List<WenDate> listwendate=new ArrayList<>();
    private List<StudentDate> liststudate=new ArrayList<>();
    private WenDate wenDate;
    private WenDao wenDao;
    private StuDao stuDao;
    private String stuid;
    private BarChart map_Barchart;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.map_bar, container, false);
        Bundle bundle = getArguments();
        stuid=bundle.getString("stuid");
        map_Barchart=(BarChart)view.findViewById(R.id.map_Barchart);
        BarMapData();
        return view;
    }
    public void BarMapData(){
        stuDao=new StuDao(getContext());
        liststudate=stuDao.queryData("stuid",stuid);
        stuDate=liststudate.get(0);
        wenDao=new WenDao(getContext());
        listwendate=wenDao.queryDataFor("stuid",stuid);
        wenDate=listwendate.get(0);
        mapBarChart();
    }
    public void mapBarChart(){
        if (listwendate != null && listwendate.size() > 0) {

            List<BarEntry> list = new ArrayList<>();
            for (int i = 0; i < listwendate.size(); i++) {
                list.add(new BarEntry(i, listwendate.get(i).getWendu()));
                System.out.println(listwendate.get(i).getWendu());//其中两个数字对应的分别是   X轴   Y轴
            }
            BarDataSet barDataSet=new BarDataSet(list,"温度");
            barDataSet.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return ""+listwendate.get(dataSetIndex).getWendu();
                }
            });
            BarData barData=new BarData(barDataSet);
            map_Barchart.setData(barData);
            map_Barchart.setScaleYEnabled(false);  //禁止y轴缩放
            map_Barchart.getXAxis().setGranularity(1);
            map_Barchart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float v, AxisBase axisBase) {
                    if (v < listwendate.size()) {
                        String s = String.valueOf(listwendate.get((int) v).getDateandtime());
                        String[] time=s.split(" ");
                        String month = time[0].substring(5, 7);
                        String day = time[0].substring(8,10);
                        return month + "." + day;
                    }
                    return "";
                }
            });
            map_Barchart.getDescription().setEnabled(false);//隐藏右下角英文
            map_Barchart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);//X轴的位置 默认为上面
            map_Barchart.getAxisRight().setEnabled(false);//隐藏右侧Y轴   默认是左右两侧都有Y轴
            map_Barchart.notifyDataSetChanged();
            map_Barchart.invalidate();
        }
    }
}
