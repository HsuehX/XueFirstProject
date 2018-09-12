package com.example.lenovo.textviewspannerdalogexercise.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;
import com.example.lenovo.textviewspannerdalogexercise.view.CommonSpinMultipleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xueww on 2017/9/12.
 */

public class SimpleAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList = new ArrayList<>();
    private int mtheme;


    int mSelect = 0;   //选中项

    public SimpleAdapter(Context context, List<String> list, int theme) {
        this.mContext = context;
        mList.addAll(list);
        this.mtheme = theme;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public void changeSelected(int positon) { //刷新方法
        if (mtheme == 2) {
            if (positon != mSelect) {
                mSelect = positon;
                notifyDataSetChanged();
            }
        } else {

        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_common_dialog_spin, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.common_dialog_item_tv);
            holder.mCodeTv = (TextView) convertView.findViewById(R.id.common_dialog_item_code_tv);
            holder.mArrowIv = (ImageView) convertView.findViewById(R.id.item_arrow_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(mList.get(position));
//        holder.mTextView.setText(mList.get(position).getText());
//        holder.mCodeTv.setText(mList.get(position).getmNnum());

        if (mtheme == 2) {
            if (mSelect == position) {
                holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.dialog_spin_item_text_pressed));
                holder.mCodeTv.setTextColor(mContext.getResources().getColor(R.color.dialog_spin_item_text_pressed));
                holder.mArrowIv.setVisibility(View.VISIBLE);
            } else {
                holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.dialog_spin_item_text));
                holder.mCodeTv.setTextColor(mContext.getResources().getColor(R.color.dialog_spin_item_text));
                holder.mArrowIv.setVisibility(View.GONE);
            }
        } else {
        }
        return convertView;
    }

    public void notifiDataSetTwoAllChanged(List<String> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    class ViewHolder {
        TextView mTextView;
        TextView mCodeTv;
        ImageView mArrowIv;
    }
}
