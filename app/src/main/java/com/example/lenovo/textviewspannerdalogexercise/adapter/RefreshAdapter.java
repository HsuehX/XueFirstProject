package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xue on 2018/2/12.
 */

public class RefreshAdapter extends BaseAdapter {
//    public ArrayList<HashMap<String, String>> list;
    private List<SpannerDialogBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public RefreshAdapter(Context context, List<SpannerDialogBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.item_refresh_list, null);
            holder = new ViewHolder();
            holder.txt = (TextView) view.findViewById(R.id.textView1);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.txt.setText(list.get(position).getText());
        return view;
    }

    static class ViewHolder {

        TextView txt;
    }
}
