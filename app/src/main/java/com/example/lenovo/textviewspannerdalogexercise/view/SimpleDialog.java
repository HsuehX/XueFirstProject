package com.example.lenovo.textviewspannerdalogexercise.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.bean.KeyToValueList;
import com.example.lenovo.textviewspannerdalogexercise.bean.SpannerDialogBean;
import com.example.lenovo.textviewspannerdalogexercise.view.wheelview.CommonSpinComplexAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 */

public class SimpleDialog implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Context mContext;
    private AlertDialog mDialog;
    private final int COMPLEXLIST = 2;
    private int mTheme = -1;
    private int mLayoutResId;
    //    private List<String> mList = new ArrayList<String>();
    private List<String> mList = new ArrayList<>();
    private LinearLayout mRootView;
//    private OnItemClickListener mOnItemTextClickListener;

    private ListView mListView;
    private TextView mTitleText;
    //    private CommonSpinMultipleAdapter mAdapter;
    private SimpleAdapter mAdapter;
    private int mResIdForListView;
    private ListViewOnItemCLickListener mListOnItemClickListener;

    private int mCurrentSelectionWithTheme3;


//    public CommonSpinDialog(Context context) {
//        this.mContext = context;
//        mLayoutResId = R.layout.dialog_common_spin_single_list;
//        initialize();
//
//    }

    public SimpleDialog(Context context, int theme) {
        this.mContext = context;
        this.mTheme = theme;
        switch (mTheme) {
            case COMPLEXLIST:
                mLayoutResId = R.layout.dialog_common_spin_complex_list;
                break;
            default:
                break;
        }
        initialize();
    }

    private void initialize() {
        mDialog = new AlertDialog.Builder(mContext).create();
        show();
        dismiss();
    }

    public void create() {
        if (mDialog != null) {
            Window window = mDialog.getWindow();
            mDialog.setCanceledOnTouchOutside(true);
            if (window != null) {
                window.setContentView(mLayoutResId);
//                window.setWindowAnimations(R.style.common_spin_dialog_anim);
                window.setGravity(Gravity.BOTTOM);
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if (mTheme == COMPLEXLIST) {
//                    mAdapter = new CommonSpinMultipleAdapter(mContext, mList, COMPLEXLIST);
                    mAdapter = new SimpleAdapter(mContext, mList, COMPLEXLIST);
                    mListView = (ListView) window.findViewById(R.id.common_dialog_multiple_listview);
                    mTitleText = (TextView) window.findViewById(R.id.common_dialog_multiple_title);
                    mListView.setOnItemClickListener(this);
                    mListView.setAdapter(mAdapter);
                    mListView.setSelection(mCurrentSelectionWithTheme3);
                    mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            mListView.setSelection(mCurrentSelectionWithTheme3);
                            mAdapter.changeSelected(mCurrentSelectionWithTheme3);
                        }
                    });
                }

                window.findViewById(R.id.common_dialog_spin_cancel).setOnClickListener(this);
            }
        }
    }

//    private void initializeSingleListViews() {
//        if (mList == null) {
//            return;
//        }
//
//        int size = mList.size();
//        for (int i = 0; i < size; i++) {
//            View view = View.inflate(mContext, R.layout.item_common_dialog_spin, null);
//            mRootView.addView(view);
//            ((TextView) view.findViewById(R.id.common_dialog_item_tv)).setText(mList.get(i));
////            (view.findViewById(R.id.common_dialog_item_text)).setBackgroundResource(R.drawable.selector_common_dialog_item_bg);
//            view.setOnClickListener(this);
//            view.setTag(mList.get(i));
//        }
//    }

//    public void setDataList(ArrayList<String> list) {
//        if (mList != null) {
//            mList.clear();
//            mList.addAll(list);
//        }
//    }

    public void setDataList(ArrayList<String> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
        }
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void show(int resId) {

        if (mDialog != null) {
            mDialog.show();
        }

        if (mTheme == COMPLEXLIST && mListView != null) {
            mListView.requestFocus();
            mListView.setItemChecked(mCurrentSelectionWithTheme3, true);
            mAdapter.changeSelected(mCurrentSelectionWithTheme3);
        }

        mResIdForListView = resId;
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    //    public void notifyDataSetAllChanged(List<String> list) {
//        if (mList != null) {
//            mList.clear();
//            mList.addAll(list);
//            mAdapter.notifiDataSetAllChanged(mList);
//        }
//    }
    public void notifyDataSetAllChanged(List<String> list) {
        if (mList != null) {
            mList.clear();
            mList.addAll(list);
            mAdapter.notifiDataSetTwoAllChanged(mList);
        }
    }


//    public void setOnItemTextCLickListener(OnItemClickListener listener) {
//        this.mOnItemTextClickListener = listener;
//    }

    public void setOnListItemClickListener(ListViewOnItemCLickListener listener) {
        this.mListOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_dialog_spin_cancel:
                dismiss();
                return;
            default:
                break;
        }

    }

    public void setTitleText(String text) {
        if (mTitleText != null) {
            mTitleText.setText(text);
        }
    }

    public void listViewClickSelf(int resId) {
        mResIdForListView = resId;
        if (mListView != null) {
            mListView.performItemClick(null, 0, 0);
        }
    }

//    public int getCurrentSelectionWithTheme3() {
//        return mCurrentSelectionWithTheme3;
//    }

    public void setCurrentSelectionWithTheme3(int mCurrentSelectionWithTheme3) {
        this.mCurrentSelectionWithTheme3 = mCurrentSelectionWithTheme3;
        if (mListView != null) {
            mListView.setSelection(mCurrentSelectionWithTheme3);
            mAdapter.changeSelected(mCurrentSelectionWithTheme3);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListOnItemClickListener != null && mList != null && mList.size() > 0) {
//            mListOnItemClickListener.onListItemClickListener(mList.get(position), mResIdForListView, position);
            mListOnItemClickListener.onListItemClickListener(mList.get(position), mResIdForListView, position);
//            mAdapter.changeSelected(position);//刷新
        } else {
            if (mListOnItemClickListener != null && mList != null) {
                mListOnItemClickListener.onListItemClickListener("", mResIdForListView, position);
            }
        }
        dismiss();
    }

//    public interface OnItemClickListener {
//        void onTextItemClickListener(String text, int position);
//    }

    public interface ListViewOnItemCLickListener {
        void onListItemClickListener(String text, int resId, int position);
    }
}