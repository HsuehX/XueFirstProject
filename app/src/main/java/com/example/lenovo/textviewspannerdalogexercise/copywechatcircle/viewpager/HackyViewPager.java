package com.example.lenovo.textviewspannerdalogexercise.copywechatcircle.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by xue on 2018/8/29.
 *
 * 图片缩放会干扰onTouch（onInterceptTouchEvent）事件（数组越界）
 * 并没有解决 只是掩盖了结果 使其表面不发生错误
 *
 *
 */

public class HackyViewPager extends ViewPager {
    private static final String TAG = "HackyViewPager";

    public HackyViewPager(Context context) {
        super(context);
    }

    public HackyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            // 不理会
            Log.e(TAG, "hacky viewpager error1");
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            // 不理会
            Log.e(TAG, "hacky viewpager error2");
            return false;
        }
    }
}
