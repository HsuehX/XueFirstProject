package com.example.lenovo.textviewspannerdalogexercise.view.msi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lenovo.textviewspannerdalogexercise.R;


/**
 * 语音转成汉字   新一代里面用的方法
 */
public class AudioRecordMicView extends View {

    private Drawable mic_1Drawable;
    private Drawable mic_2Drawable;
    private Rect mic_1DrawableRect;
    private Rect mic_2DrawableRect;
    private Rect spaceRect;

    private int maxLevel = 30;

    public AudioRecordMicView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public AudioRecordMicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AudioRecordMicView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mic_1Drawable = getResources().getDrawable(R.drawable.outside_icon_voiceline_nor);
        mic_2Drawable = getResources().getDrawable(R.drawable.outside_icon_voiceline_pre);
        mic_1DrawableRect = new Rect();
        mic_2DrawableRect = new Rect();
        spaceRect = new Rect();

    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setLevel(int level) {
        int progress = (level * mic_2DrawableRect.width()) / (maxLevel * 2);
        setProgress(progress);
    }

    public void setProgress(int progress) {
        spaceRect.set((mic_2DrawableRect.width()/2)-50 - progress, mic_2DrawableRect.top, (mic_2DrawableRect.width()/2)+50 +progress, mic_2DrawableRect.bottom);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mic_1Drawable.getIntrinsicWidth(), mic_1Drawable.getIntrinsicHeight());
        mic_1DrawableRect.set(0, 0, mic_1Drawable.getIntrinsicWidth(), mic_1Drawable.getIntrinsicHeight());
        mic_1Drawable.setBounds(mic_1DrawableRect);
        mic_2DrawableRect.set(0, 0, mic_2Drawable.getIntrinsicWidth(), mic_2Drawable.getIntrinsicHeight());
        mic_2Drawable.setBounds(mic_2DrawableRect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mic_1Drawable.draw(canvas);
        canvas.save();
        canvas.clipRect(spaceRect, Region.Op.REPLACE);
        mic_2Drawable.draw(canvas);
        canvas.restore();
    }
}
