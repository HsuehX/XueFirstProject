package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.content.Context;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.ListActivity;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.content;
import static android.R.attr.key;

/**
 * Created by xueww on 2017/12/25.
 */

public class ListAdapter extends BaseAdapter {
    private List<SpannerDialogBean> mList;
    private Context mContext;
    private Map<String, Boolean> mSelectStates = new HashMap<String, Boolean>();
    private Map<Integer, Boolean> map = new HashMap<>();// 存放已被选中的CheckBox
    private boolean mShowSelector = false;

    public ListAdapter(Context mContext, List<SpannerDialogBean> list) {
        this.mList = new ArrayList<SpannerDialogBean>();
        this.mList.addAll(list);
        this.mContext = mContext;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview_notice_query, parent, false);
//            convertView = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE).inflate(R.layout.item_listview_notice_query, parent, false);
            viewHolder = new ViewHolder(convertView);
            //给view赋值，


//            viewHolder.mCheckCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    mSelectStates.put(compoundButton.getTag().toString(), isChecked);
////                    synchronizeCheck();
//                }
//            });


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTvTitle.setText(mList.get(position).getText());
        //这为什么要写if外面呢，因为上面的if判断是判断view是否被赋值，如果已经被赋值（给予xml文件），则直接调用，不再重新赋值。
        //如果写在if里面的话，listview滑动一次（也就是刷新一次），view就被重新new一次，加载的永远是最上面第一次显示的数据
        viewHolder.mTvTime.setText(mList.get(position).getValue());

        viewHolder.mCheckCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    map.put(position, true);
                } else {
                    map.remove(position);
                }
            }
        });

        if (map != null && map.containsKey(position)) {
            viewHolder.mCheckCb.setChecked(true);
        } else {
            viewHolder.mCheckCb.setChecked(false);
        }


        return convertView;
    }

//
//    @Override
//    public View getView(final int position, View view, ViewGroup viewGroup) {
//        final ViewHolder viewHolder;
//        SpannerDialogBean notice = notices.get(position);
//
//        if (view == null) {
//            view = LayoutInflater.from(context).inflate(R.layout.item_listview_notice_query, viewGroup, false);
//            viewHolder = new ListAdapter.ViewHolder(view);
//
//            viewHolder.mCheckCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    mSelectStates.put(compoundButton.getTag().toString(), isChecked);
//                    synchronizeCheck();
//                }
//            });
//            view.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        viewHolder.mCheckCb.setVisibility(View.GONE);
//        viewHolder.mTvTitle.setText(notice.getText());
//        viewHolder.mTvTime.setText(notice.getmNnum());
//        viewHolder.mCheckCb.setTag(getItemKey(notice));
//        viewHolder.mCheckCb.setVisibility(mShowSelector ? View.VISIBLE : View.GONE);
//        viewHolder.mCheckCb.setChecked(mSelectStates.get(getItemKey(notice)));
//        return view;
//    }
//
//    public void synchronizeCheck() {
//        //控制同步全选
//        int checkedCount = 0;
//        for (Map.Entry<String, Boolean> item : mSelectStates.entrySet()) {
//            if (item.getValue()) {
//                checkedCount++;
//            }
//        }
//
//        if (checkedCount == notices.size()) {
//            context.setCheckAllCb();
//        } else {
//            context.cancelCheckAllCb();
//        }
//    }
//
//    @Override
//    public void notifyDataSetChanged() {
//        if (getCount() == 0) {
//            mSelectStates.clear();
//        } else if (getCount() != mSelectStates.size()) {
//            Map<String, Boolean> tempSelectStates = new HashMap<String, Boolean>();
//            for (int i = 0; i < getCount(); i++) {
//                String key = getItemKey((SpannerDialogBean) getItem(i));
//                boolean state = mSelectStates.containsKey(key) ? mSelectStates.get(key) : false;
//                tempSelectStates.put(key, state);
//            }
//            mSelectStates.clear();
//            mSelectStates.putAll(tempSelectStates);
//        }
//        super.notifyDataSetChanged();
//    }
//
//    /**
//     * 获取指定条目的键值
//     *
//     * @param notice
//     * @return
//     */
//    public String getItemKey(SpannerDialogBean notice) {
////        return notice != null ? notice.getId() : null;
//        return notice != null ? notice.getmNnum() : null;
//    }
//
//    /**
//     * 获取数据对象的选择状态
//     */
//    public Map<Integer, Boolean> getSelectStates() {
//        Map<Integer, Boolean> result = new HashMap<Integer, Boolean>();
//        for (int i = 0; i < getCount(); i++) {
//            String key = getItemKey((SpannerDialogBean) getItem(i));
//            result.put(i, mSelectStates.get(key));
//        }
//        return result;
//    }
//
//    public void showCheckBox() {
//        mShowSelector = true;
//        notifyDataSetChanged();
//    }
//
//    public void hideCheckBox() {
//        mShowSelector = false;
//        notifyDataSetChanged();
//    }
//

    /**
     * 设置全选
     */
    public void setCheckAll() {
        for (Map.Entry<String, Boolean> item : mSelectStates.entrySet()) {
            item.setValue(true);
        }
        notifyDataSetChanged();
    }

    /**
     * 取消全选
     */
    public void cancelCheckAll() {
        for (Map.Entry<String, Boolean> item : mSelectStates.entrySet()) {
            item.setValue(false);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {

        /**
         * checkBox
         */
        @Bind(R.id.check_cb)
        CheckBox mCheckCb;

        /**
         * 标题
         */
        @Bind(R.id.tv_notice_title)
        TextView mTvTitle;

        /**
         * 时间
         */
        @Bind(R.id.tv_time)
        TextView mTvTime;

        /**
         * 阅读标识
         */
        @Bind(R.id.tv_read_flag)
        TextView mTvReadFlag;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
