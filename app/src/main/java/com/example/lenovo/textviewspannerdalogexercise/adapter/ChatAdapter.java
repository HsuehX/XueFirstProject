package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.bean.MsgBean;
import com.example.lenovo.textviewspannerdalogexercise.utils.EmojiUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xue on 2018/7/28.
 */

public class ChatAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<MsgBean> mList;
    private Context mContext;

    public ChatAdapter(Context context, List<MsgBean> list) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public MsgBean getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        MsgBean bean = mList.get(position);
        if (bean.getType() == MsgBean.Type.INCOMING) {
            return 0;
        } else {
            return 1;
        }


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        // MsgBean bean = getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (getItemViewType(position) == 0) {

                convertView = mLayoutInflater.inflate(R.layout.item_chat, parent, false);
                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_incoming_date);
                viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_incoming_content);


            } else {
                convertView = mLayoutInflater.inflate(R.layout.item_chat_send, parent, false);
                viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_outcoming_date);
                viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_outcoming_content);


            }
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.tv_date.setText(format.format(getItem(position).getDate()));

        String str = getItem(position).getMsg();                             //消息具体内容
        String check = "f0[0-9]{2}|f10[0-7]";              //正则表达式，用来判断消息内是否有表情
        //解析消息内容
        SpannableString spannableString = EmojiUtil.getExpressionString(mContext, str, check);

        viewHolder.tv_content.setText(spannableString);

//        viewHolder.tv_content.setText(getItem(position).getMsg());


        return convertView;
    }

    private static class ViewHolder {
        private TextView tv_date;
        private TextView tv_content;

    }
}
