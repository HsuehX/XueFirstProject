//package com.example.lenovo.textviewspannerdalogexercise.view;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.widget.TextView;
//
//import com.example.lenovo.textviewspannerdalogexercise.R;
//import com.github.mikephil.charting.components.MarkerView;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.highlight.Highlight;
//
//public abstract class MyMarkerView extends MarkerView {
//
//    private TextView tvContent;
//
//    public MyMarkerView(Context context, int layoutResource) {
//        super(context, layoutResource);
//
//        tvContent = (TextView) findViewById(R.id.tvContent);
//    }
//
//    @Override
//    public void draw(Canvas canvas, float posx, float posy) {
//        super.draw(canvas, posx, posy);
//    }
//
//    @Override
//    public void refreshContent(Entry entry, Highlight highlight) {
//
//    }
//
//    @Override
//    public int getXOffset() {
//        return 0;
//    }
//
//    @Override
//    public int getYOffset() {
//        return 0;
//    }
//}
