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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class LineChartFragment extends Fragment {
    private StudentDate stuDate;
    private List<WenDate> listwendate=new ArrayList<>();
    private List<StudentDate> liststudate=new ArrayList<>();
    private WenDate wenDate;
    private WenDao wenDao;
    private StuDao stuDao;
    private String stuid;
    private LineChart map_Linechart;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.map_line, container, false);
        Bundle bundle = getArguments();
        stuid=bundle.getString("stuid");
        map_Linechart=(LineChart)view.findViewById(R.id.map_Linechart);
        LineMapData();
        return view;
    }
    public void LineMapData(){
        stuDao=new StuDao(getContext());
        liststudate=stuDao.queryData("stuid",stuid);
        stuDate=liststudate.get(0);
        wenDao=new WenDao(getContext());
        listwendate=wenDao.queryDataFor("stuid",stuid);
        wenDate=listwendate.get(0);
        mapLineChart();
    }
    public void mapLineChart(){
        if (listwendate != null && listwendate.size() > 0) {

            List<Entry> list = new ArrayList<>();
            for (int i = 0; i < listwendate.size(); i++) {
                list.add(new Entry(i, listwendate.get(i).getWendu()));
                System.out.println(listwendate.get(i).getWendu());//其中两个数字对应的分别是   X轴   Y轴
            }

            //list是你这条线的数据  "题量" 是你对这条线的描述（也就是图例上的文字）
            LineDataSet lineDataSet = new LineDataSet(list, "温度");
            lineDataSet.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return ""+listwendate.get(dataSetIndex).getWendu();
                }
            });
            lineDataSet.setColor(getResources().getColor(R.color.teal_200));            //折线颜色
            lineDataSet.setFillColor(getResources().getColor(R.color.teal_200));        //折线以下填充颜色
            lineDataSet.setDrawFilled(true);                                                   //是否填充
            lineDataSet.setFillAlpha(10);                                                      //填充颜色透明度
            lineDataSet.setLineWidth(1f);                                                      //折线宽度
            //是否画折线点上的空心圆  false表示直接画成实心圆
            lineDataSet.setDrawCircleHole(false);
            // lineDataSet.setValueTextColor(getResources().getColor(R.color.tv_default_blue));   //折线上数值颜色
            //  lineDataSet.setCircleColor(getResources().getColor(R.color.tv_default_blue));      //折线上圆点的颜色
            lineDataSet.setCircleRadius(2);                                                    //圆点的半径
            lineDataSet.setValueTextSize(12);//折线字号
            LineData lineData = new LineData(lineDataSet);
            map_Linechart.setData(lineData);
            map_Linechart.setScaleXEnabled(true);   //支持x轴缩放
            map_Linechart.setScaleYEnabled(false);  //禁止y轴缩放
//          lineChart1.getXAxis().setLabelRotationAngle(-15);
//          map_Linechart.getXAxis().setDrawGridLines(false);                   //是否绘制X轴上的网格线（背景里面的竖线
//          map_Linechart.getAxisLeft().setDrawGridLines(false);                //是否绘制Y轴上的网格线（背景里面的横线）
//          lineChart1.getXAxis().setGridColor(getResources().getColor(R.color.tv_default_gray));
//          lineChart1.getAxisLeft().setGridColor(getResources().getColor(R.color.tv_default_gray));
            map_Linechart.getXAxis().setGranularity(1);
            map_Linechart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
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
            map_Linechart.getDescription().setEnabled(false);                   //隐藏文字介绍
            map_Linechart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);   //X轴所在位置   默认为上面
            map_Linechart.getLegend().setEnabled(false);                        //隐藏图例
//            lineChart1.getAxisLeft().setEnabled(false);                      //隐藏左边的y轴
            map_Linechart.getAxisRight().setEnabled(false);                     //隐藏右边的y轴

            //数据更新
            map_Linechart.notifyDataSetChanged();
            map_Linechart.invalidate();
            map_Linechart.animateY(500); //折线在Y轴的动画  参数是动画执行时间 毫秒为单位
        }
    }
}
