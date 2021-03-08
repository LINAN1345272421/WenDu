package com.example.helloworld.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.date.WenDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryAdapter extends BaseAdapter {
    private List<WenDate> lists=new ArrayList<>();
    private Context context;
    public QueryAdapter(Context context,List<WenDate> lists) {
        this.context=context;
        this.lists=lists;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.list_item,null);
        }
        TextView list_name=(TextView)convertView.findViewById(R.id.list_stuname);
        TextView list_stuid=(TextView)convertView.findViewById(R.id.list_stuid);
        TextView list_wendu=(TextView)convertView.findViewById(R.id.list_wendu);
        TextView list_address=(TextView)convertView.findViewById(R.id.list_address);
        TextView list_special=(TextView)convertView.findViewById(R.id.list_special);
        TextView list_Time=(TextView)convertView.findViewById(R.id.list_time);
        WenDate wenDate=lists.get(position);
        list_name.setText(wenDate.getName());
        list_address.setText(wenDate.getAddress());
        list_special.setText(wenDate.getSpecial());
        list_stuid.setText(wenDate.getStuid());
        list_wendu.setText(wenDate.getWendu().toString());
        list_Time.setText(wenDate.getDateandtime().toString());
        return convertView;
    }
}
