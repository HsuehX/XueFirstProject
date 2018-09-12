package com.example.lenovo.textviewspannerdalogexercise.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by xue on 2018/7/21.
 */

public class CustomScrollView extends ScrollView {

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float xLast, yLast, xDistance, yDistance;

    // ScrollView的子View， 也是ScrollView的唯一一个子View
    private View contentView;

    // 用于记录正常的布局位置
    private Rect originalRect = new Rect();


    public CustomScrollView(Context context) {
        super(context);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (contentView == null)
            return;
        // ScrollView中的唯一子控件的位置信息, 这个位置信息在整个控件的生命周期中保持不变
        originalRect.set(contentView.getLeft(), contentView.getTop(),
                contentView.getRight(), contentView.getBottom());
    }

    /**
     * 在这里解决滑动上下滑动，左右滑动冲突
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                xDistance = yDistance = 0f;
//                xLast = ev.getX();
//                yLast = ev.getY();
                // 因为ACTION_DOWN始终无法进入ScrollView的onTouchEvent，
                // 但是ScrollView的滚动需要在ACTION_DOWN时做一些准备
                onTouchEvent(ev);
                return false;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;   //表示向下传递事件
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
