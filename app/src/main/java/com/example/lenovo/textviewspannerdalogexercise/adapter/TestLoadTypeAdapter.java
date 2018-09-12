package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueww on 2017/12/26.
 */

public class TestLoadTypeAdapter extends BaseAdapter {
    private List<SpannerDialogBean> mList;
    private Context mContext;
    private int isSelected = 0;

    public TestLoadTypeAdapter(Context mContext, List<SpannerDialogBean> list) {
        this.mList = new ArrayList<SpannerDialogBean>();
        this.mList.addAll(list);
        this.mContext = mContext;
    }

    public void setData(List<SpannerDialogBean> plist) {
        if (plist != null) {
            mList = plist;
            notifyDataSetChanged();
        } else {
            mList = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_test_loadt_type, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.typeTv = (TextView) convertView.findViewById(R.id.show_type_tv);
            viewHolder.chooseTv = (ImageView) convertView.findViewById(R.id.choose_iv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.typeTv.setText(mList.get(position).getText());
        if (isSelected == position) {
            viewHolder.chooseTv.setVisibility(View.VISIBLE);
        } else {
            viewHolder.chooseTv.setVisibility(View.GONE);
        }
        return convertView;
    }

    public void isSelected(int position) {
        if (isSelected != position) {
            isSelected = position;
            notifyDataSetChanged();
        }
    }

    public final class ViewHolder {
        public TextView typeTv;
        public ImageView chooseTv;
    }
}
