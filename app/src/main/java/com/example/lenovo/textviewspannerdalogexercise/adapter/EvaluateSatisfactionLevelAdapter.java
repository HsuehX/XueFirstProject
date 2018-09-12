package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.lenovo.textviewspannerdalogexercise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2018/8/7.
 */

public class EvaluateSatisfactionLevelAdapter extends BaseAdapter {
    private List<String> listData = new ArrayList<>();
    private Context context;


    public EvaluateSatisfactionLevelAdapter(Context context, List<String> listData) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData != null && listData.size() != 0 && !listData.isEmpty() ? listData.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return listData != null && !listData.isEmpty() ? listData.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_evaluate_satisfaction_level, viewGroup, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mDishNameTv = (TextView) view.findViewById(R.id.tv_dish_name);
        viewHolder.mDishNameTv.setText(listData.get(i));
        return view;
    }

    public final class ViewHolder {
        TextView mDishNameTv;
        ImageView mGoodIv;
        ImageView mBadIv;
    }
}
