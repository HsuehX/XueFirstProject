package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;
import com.example.lenovo.textviewspannerdalogexercise.view.AmountView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xueww on 2017/12/26.
 */

public class TestLoadContentAdapter extends BaseAdapter {
    private List<SpannerDialogBean> mList;
    private Context mContext;
    private int count = 0;
    private Map<Integer, Boolean> map = new HashMap<>();

    public TestLoadContentAdapter(Context mContext, List<SpannerDialogBean> list) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_test_load_content, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.content_title_tv);
            viewHolder.mAmountView = (AmountView) convertView.findViewById(R.id.amount_view);
//            viewHolder.reductionIv = (ImageView) convertView.findViewById(R.id.number_reduction_iv);
//            viewHolder.addIv = (ImageView) convertView.findViewById(R.id.number_add_iv);
//            viewHolder.conutTv = (TextView) convertView.findViewById(R.id.content_buy_count_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titleTv.setText(mList.get(position).getText());

//        viewHolder.addIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;
//                map.put(position, true);
//            }
//        });
//
//        viewHolder.reductionIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count--;
//                map.put(position, true);
//            }
//        });
//        if (map != null && map.containsKey(position)) {
//            viewHolder.conutTv.setText(count + "");
//        } else {
////            viewHolder.conutTv.setText(false);
//        }

        viewHolder.mAmountView.setGoods_storage(50);
        viewHolder.mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
//                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                if(amount==0){

                }
            }
        });


//        final ViewHolder finalViewHolder = viewHolder;
//        viewHolder.addIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;
//                finalViewHolder.conutTv.setText("" + count);
//            }
//        });
//        viewHolder.reductionIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count--;
//                finalViewHolder.conutTv.setText("" + count);
//            }
//        });
        return convertView;
    }

    public final class ViewHolder {
        public TextView titleTv;
        public ImageView reductionIv;
        public ImageView addIv;
        public TextView conutTv;
        public AmountView mAmountView;
    }
}
