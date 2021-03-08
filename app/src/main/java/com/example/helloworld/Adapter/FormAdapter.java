package com.example.helloworld.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.helloworld.R;
import com.example.helloworld.date.WenDate;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FormAdapter extends BaseAdapter {
    private List<WenDate> lists=new ArrayList<>();
    private Context context;
    public FormAdapter(Context context,List<WenDate> lists) {
        this.context=context;
        this.lists=lists;
    }
    @Override
    public int getCount() {
        if(lists.size()<14){
            return lists.size();
        }else{
            return 14;
        }
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
            convertView= LayoutInflater.from(context).inflate(R.layout.list_form,null);
        }
        TextView list_wendu=(TextView)convertView.findViewById(R.id.form_list_wendu);
        TextView list_address=(TextView)convertView.findViewById(R.id.form_list_address);
        TextView list_special=(TextView)convertView.findViewById(R.id.form_list_special);
        TextView list_Time=(TextView)convertView.findViewById(R.id.form_list_time);
        TextView list_stuation=(TextView)convertView.findViewById(R.id.form_list_stuation);
        WenDate wenDate=lists.get(position);
        list_address.setText(wenDate.getAddress());
        list_special.setText(wenDate.getSpecial());
        list_wendu.setText(wenDate.getWendu().toString());
        list_Time.setText(wenDate.getDateandtime().toString());
        if(wenDate.getWendu()<37)
        {
            list_stuation.setText("良好");
        }else{
            list_stuation.setText("发热");
        }
        return convertView;
    }
}
