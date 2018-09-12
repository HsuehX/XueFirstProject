package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.Service.SwipeDataService;
import com.example.lenovo.textviewspannerdalogexercise.db.dto.DemoDto;
import com.example.lenovo.textviewspannerdalogexercise.utils.Session;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xueww on 2017/12/25.
 */

public class SwipeDataAdapter extends ArrayAdapter<Session> {
       private List<DemoDto> mList;
    private Context mContext;
    private SwipeDataService mSwipeDataService;

//    public SwipeDataAdapter(Context mContext, List<DemoDto> list) {
//        this.mList = new ArrayList<DemoDto>();
//        this.mList.addAll(list);
//        this.mContext = mContext;
//        mSwipeDataService = new SwipeDataService(mContext);
//    }

    public SwipeDataAdapter(Context mContext, List<DemoDto> list) {
        super(mContext, 0, new ArrayList<Session>());
        this.mList = new ArrayList<DemoDto>();
        this.mList.addAll(list);
        this.mContext = mContext;
        mSwipeDataService = new SwipeDataService(mContext);
    }

    public void setData(List<DemoDto> list) {
        if (list != null) {
            mList = list;
            notifyDataSetChanged();
        } else {
            mList = new ArrayList<DemoDto>();
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

//    @Override
//    public Object getItem(int position) {
//        return mList.get(position);
//    }

    @Override
    public Session getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_swipe_data, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mAccountNumberTv.setText(mList.get(position).getCourseID());
        viewHolder.mTitleTv.setText(mList.get(position).getCourseTitle());
        viewHolder.mDateTv.setText(mList.get(position).getDate());
        return convertView;
    }


    public static class ViewHolder {
        @Bind(R.id.account_number_tv)
        TextView mAccountNumberTv;
        @Bind(R.id.title_tv)
        TextView mTitleTv;
        @Bind(R.id.date_tv)
        TextView mDateTv;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void delete(String courseID) {//设置删除方法
        mSwipeDataService.deleteDemoDto(courseID);
//        mList.remove(p);
//        notifyDataSetChanged();
//        listView.setSelection(p);
    }

    //设置滑动删除按钮的宽高属性的方法
    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
}
