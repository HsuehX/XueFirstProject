package com.example.lenovo.textviewspannerdalogexercise.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.ItemCheckBoxActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xueww on 2017/11/16.
 */

public class ItemCheckBoxAdapter extends BaseAdapter {
    private ItemCheckBoxActivity context;
    private List<String> adapteList;
    private String boxPosition;

    public ItemCheckBoxAdapter(ItemCheckBoxActivity context, List<String> adapteList) {
        this.context = context;
        this.adapteList = adapteList;
    }

    public String getBoxPosition() {
        return boxPosition;
    }

    @Override
    public int getCount() {
        return adapteList.size() != 0 && !adapteList.isEmpty() ? adapteList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return adapteList != null && !adapteList.isEmpty() ? adapteList.get(i) : null;
    }

    public String getPeiFaData() {
        return adapteList.get(Integer.valueOf(boxPosition));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ItemCheckBoxAdapter.ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_check_box, viewGroup, false);
            viewHolder = new ItemCheckBoxAdapter.ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemCheckBoxAdapter.ViewHolder) view.getTag();
        }
        viewHolder.mMailnoTv.setText(adapteList.get(i) == null ? "" : adapteList.get(i));
        viewHolder.mLinkManTv.setText(adapteList.get(i) == null ? "" : adapteList.get(i));
        viewHolder.mAddressTv.setText(adapteList.get(i) == null ? "" : adapteList.get(i));

        viewHolder.mCheckCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!viewHolder.mCheckCb.isChecked()) {
                    boxPosition = "";//如果点击  将boxPosition设置为空
                } else {
                    boxPosition = String.valueOf(i);//如果没点击 将boxPosition设置为当前item位置
                }
                notifyDataSetChanged();
            }
        });

        /**
         * 这里设置出来只能选择一个
         */
        if (!String.valueOf(i).equals(boxPosition)) {//将i(就是position)转成string与boxPosition进行比较
            viewHolder.mCheckCb.setChecked(false);
        } else {
            viewHolder.mCheckCb.setChecked(true);
        }
        return view;
    }

    /***
     * 这里的写法是   将CheckBox设置为不可点击，同时将整个item的点击事件视为CheckBox的点击事件
     * 在itemOnClick事情触发时，赋予boxPosition的值 再通过boxPosition的值进行判断
     * @param s
     */
    public void setBoxPosition(String s) {//
        boxPosition = s;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        /**
         * 邮路code
         */
        @Bind(R.id.mailno_tv)
        TextView mMailnoTv;

        /**
         * 交接对象
         */
        @Bind(R.id.linkman_tv)
        TextView mLinkManTv;

        /**
         * 发车时间
         */
        @Bind(R.id.address_tv)
        TextView mAddressTv;

        /**
         * 复选框
         */
        @Bind(R.id.check_cb)
        public CheckBox mCheckCb;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

