package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ScrollingView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.MainActivity;
import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.adapter.ItemCheckBoxAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by xueww on 2017/11/16.
 */

public class ItemCheckBoxActivity extends Activity {
    @Bind(R.id.btn_piliang)
    public Button mPiLiangBtn;
    @Bind(R.id.btn_saomiao)
    public Button mSaoMiaoBtn;
    @Bind(R.id.et_mailno)
    public TextView mMailNoEt;
    @Bind(R.id.peifa_list)
    public ListView mListView;
    @Bind(R.id.et_ludanno)
    public EditText mYouLuEt;
    @Bind(R.id.scroll)
    public ScrollView scrollView;

    private List<String> adapteList = new ArrayList<>();
    private ItemCheckBoxAdapter adapter;
    public String res;
    private String mailNo;

    private View bottomSheet;
    private BottomSheetBehavior behavior;

    private Button mOtherBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_slip);
        ButterKnife.bind(this);
        init();
        initView();
        initSprecial();
    }

    private void initSprecial() {
        bottomSheet = findViewById(R.id.slip_bottom_sheet_rl);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, @BottomSheetBehavior.State int newState) {
                String state = "null";
                switch (newState) {
                    case 1:
                        state = "STATE_DRAGGING";
                        break;
                    case 2:
                        state = "STATE_SETTLING";
                        break;
                    case 3:
                        state = "STATE_EXPANDED";
                        break;
                    case 4:
                        state = "STATE_COLLAPSED";
                        break;
                    case 5:
                        state = "STATE_HIDDEN";
                        break;
                }
                Log.d("MainActivity", "newState:" + state);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                Log.d("BottomSheetDemo", "slideOffset:" + slideOffset);
            }
        });


//        Button dialogBtn = (Button) findViewById(R.id.bottom_sheet_dialog_btn);
//        dialogBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog dialog = new BottomSheetDialog(BottomSheetDemo.this);
//                dialog.setContentView(R.layout.bottom_sheet_dialog_layout);
//                dialog.show();
//            }
//        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//            }
//        });
    }

    private void initView() {
        View view = LayoutInflater.from(ItemCheckBoxActivity.this).inflate(R.layout.layout_bottom_sheet, null);
        mListView.addFooterView(view);//当listview滑动打底部的时候加上要显示的控件
        mOtherBtn = (Button)view.findViewById(R.id.other_btn);
        mOtherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"谢谢观赏^_^",Toast.LENGTH_SHORT).show();
            }
        });

        //这里这么写是为了只能选择一个
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (("" + i).equals(adapter.getBoxPosition())){
                    adapter.setBoxPosition("");
                } else {
                    adapter.setBoxPosition("" + i);
                }
            }
        });


        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
//
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                scrollView.requestDisallowInterceptTouchEvent(false);
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (visibleItemCount + firstVisibleItem == totalItemCount) {
//                    View lastVisibleItemView = mListView.getChildAt(totalItemCount - firstVisibleItem - 1);
////                    view的getBottom()表示的是该View底部到父控件的左上角的垂直距离。
////                    当ListView的最后一个child View的getBottom()小于等于ListView的getHeight()时，就表示ListView滚动到底部或已经在底部了，不能再向下滑动了。
//                    if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == view.getHeight()) {
//                        // 滑动到了底部
//                        scrollView.requestDisallowInterceptTouchEvent(false);
//                        mListView.addFooterView(view);
//                        Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
//                    } else {
//                        scrollView.requestDisallowInterceptTouchEvent(true);
//                        Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    scrollView.requestDisallowInterceptTouchEvent(true);
//                    Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }



    private void init() {
        adapter = new ItemCheckBoxAdapter(this, adapteList);
        adapteList.add("北京");
        adapteList.add("大连");
        adapteList.add("沈阳");
        adapteList.add("营口");
        adapteList.add("北京");
        adapteList.add("大连");
        adapteList.add("沈阳");
        adapteList.add("营口");
        mListView.setAdapter(adapter);
    }
}
