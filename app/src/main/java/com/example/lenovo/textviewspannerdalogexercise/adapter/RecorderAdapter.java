package com.example.lenovo.textviewspannerdalogexercise.adapter;

import java.util.List;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.Recorder;

/**
 * 仿微信语音的adapter
 */
public class RecorderAdapter extends ArrayAdapter<Recorder> {


    private LayoutInflater inflater;

    private int mMinItemWith;// 设置对话框的最大宽度和最小宽度
    private int mMaxItemWith;

    public RecorderAdapter(Context context, List<Recorder> dataList) {
        super(context, -1, dataList);
        // TODO Auto-generated constructor stub
        inflater = LayoutInflater.from(context);

        // 获取系统宽度
        WindowManager wManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wManager.getDefaultDisplay().getMetrics(outMetrics);
        mMaxItemWith = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWith = (int) (outMetrics.widthPixels * 0.15f);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_audioweixin, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.seconds = (TextView) convertView.findViewById(R.id.recorder_time);
            viewHolder.length = convertView.findViewById(R.id.recorder_length);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


//        viewHolder.viewanim = (View) convertView.findViewById(R.id.id_recorder_anim);

        viewHolder.seconds.setText(Math.round(getItem(position).getTime()) + "\"");
        ViewGroup.LayoutParams lParams = viewHolder.length.getLayoutParams();
        lParams.width = (int) (mMinItemWith + mMaxItemWith / 60f * getItem(position).getTime());
        viewHolder.length.setLayoutParams(lParams);

        return convertView;
    }

    class ViewHolder {
        TextView seconds;// 时间
        View length;// 对话框长度
        View viewanim;//对话框的动画
    }

}
