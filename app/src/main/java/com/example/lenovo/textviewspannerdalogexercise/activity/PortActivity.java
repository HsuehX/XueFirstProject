package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by xueww on 2017/12/11.
 */

public class PortActivity extends Activity {
    private final static String TAG = PortActivity.class.getSimpleName();

    private LinearLayout chartLyt;

    private List<Integer> lists = new ArrayList<Integer>();
    private List<Integer> lists2 = new ArrayList<Integer>();

    private int mEventX;
    private int screenEventTagX = 0;        //当前点的X轴坐标
    private int screenEventTagY = 0;        //当前点的Y轴坐标

    private TextView showTv;

    private void setLists() {
        lists.clear();
        for (int i = 1; i < 20; i++) {
            int value = ((int) (Math.random() * 100));
            lists.add(value);
        }
    }

    private void setLists2() {
        lists2.clear();
        for (int i = 1; i < 20; i++) {
//            int value = ((int) (Math.random() * 100));
            lists2.add(i);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);
        chartLyt = (LinearLayout) findViewById(R.id.chart);

        init();
        drawTheChart();
    }

    private void init() {
        showTv = (TextView) findViewById(R.id.show_tv);
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }


    public void drawTheChart() {
        XYMultipleSeriesRenderer mRenderer = getXYMulSeriesRenderer();

        XYSeriesRenderer renderer = getXYSeriesRenderer();
        XYSeriesRenderer renderer2 = getXYSeriesRenderer2();//第二条

        mRenderer.addSeriesRenderer(renderer);
        mRenderer.addSeriesRenderer(renderer2);

        setLists();
        setLists2();

        XYMultipleSeriesDataset dataset = getDataSet();

        GraphicalView chartView = ChartFactory.getLineChartView(this, dataset, mRenderer);

        chartLyt.addView(chartView, 0);
        chartView.setOnTouchListener(chartViewOnTouchListener);
        //chartLyt.invalidate();
    }

    public XYSeriesRenderer getXYSeriesRenderer() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        //设置折线宽度
        renderer.setLineWidth(2);
        //设置折线颜色
        renderer.setColor(Color.GRAY);
        renderer.setDisplayBoundingPoints(true);
        //点的样式
        renderer.setPointStyle(PointStyle.CIRCLE);
        //设置点的大小
        renderer.setPointStrokeWidth(3);
        //设置数值显示的字体大小
        renderer.setChartValuesTextSize(30);
        //显示数值
        renderer.setDisplayChartValues(true);
        return renderer;
    }


    /**
     * 第二条
     *
     * @return
     */
    public XYSeriesRenderer getXYSeriesRenderer2() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        //设置折线宽度
        renderer.setLineWidth(2);
        //设置折线颜色
        renderer.setColor(Color.BLUE);
        renderer.setDisplayBoundingPoints(true);
        //点的样式
        renderer.setPointStyle(PointStyle.CIRCLE);
        //设置点的大小
        renderer.setPointStrokeWidth(3);
        //设置数值显示的字体大小
        renderer.setChartValuesTextSize(30);
        //显示数值
        renderer.setDisplayChartValues(true);
        return renderer;
    }


    public XYMultipleSeriesDataset getDataSet() {
        XYMultipleSeriesDataset barDataset = new XYMultipleSeriesDataset();
        CategorySeries barSeries = new CategorySeries("2016年");

        for (int i = 0; i < lists.size(); i++) {
            barSeries.add(lists.get(i));
        }

        CategorySeries barSeries2 = new CategorySeries("2017年");

        for (int i = 0; i < lists2.size(); i++) {
            barSeries2.add(lists2.get(i));
        }

        barDataset.addSeries(barSeries.toXYSeries());
        barDataset.addSeries(barSeries2.toXYSeries());
        return barDataset;
    }

    public XYMultipleSeriesRenderer getXYMulSeriesRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setMarginsColor(Color.argb(0x00, 0xF3, 0xF3, 0xF3));

        // 设置背景颜色
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.WHITE);

        //设置Title的内容和大小
        renderer.setChartTitle("访问量统计");
        renderer.setChartTitleTextSize(50);

        //图表与四周的边距
        renderer.setMargins(new int[]{80, 80, 50, 50});

        //设置X,Y轴title的内容和大小
        renderer.setXTitle("日期");
        renderer.setYTitle("访问数");
        renderer.setAxisTitleTextSize(30);
        //renderer.setAxesColor(Color.WHITE);
        renderer.setLabelsColor(Color.BLACK);

        //图例文字的大小
        renderer.setLegendTextSize(20);

        // x、y轴上刻度颜色和大小
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setYLabelsColor(0, Color.BLACK);
        renderer.setLabelsTextSize(20);
        renderer.setYLabelsPadding(30);

        // 设置X轴的最小数字和最大数字，由于我们的数据是从1开始，所以设置为0.5就可以在1之前让出一部分
        // 有兴趣的童鞋可以删除下面两行代码看一下效果
        renderer.setPanEnabled(false, false);

        //显示网格
        renderer.setShowGrid(true);

        //X,Y轴上的数字数量
        renderer.setXLabels(10);
        renderer.setYLabels(10);

        // 设置X轴的最小数字和最大数字
        renderer.setXAxisMin(0);
        renderer.setXAxisMax(20);
        // 设置Y轴的最小数字和最大数字
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(100);

//        // 设置渲染器显示缩放按钮
//        renderer.setZoomButtonsVisible(true);
//        // 设置渲染器允许放大缩小
//        renderer.setZoomEnabled(true);
//        // 消除锯齿
//        renderer.setAntialiasing(true);

        // 刻度线与X轴坐标文字左侧对齐
        renderer.setXLabelsAlign(Paint.Align.LEFT);
        // Y轴与Y轴坐标文字左对齐
        renderer.setYLabelsAlign(Paint.Align.LEFT);

        // 允许左右拖动,但不允许上下拖动.
//        renderer.setPanEnabled(true, false);


//        renderer.setClickEnabled(true);//设置是否可以被点击
//        renderer.setSelectableBuffer(20);//点击区域大小
//        renderer.setExternalZoomEnabled(false);//设置是否可以缩放
//        renderer.setZoomEnabled(false);
        //上面这两完全没用
//        renderer.setPanEnabled(true);//设置是否可以平移

        return renderer;
    }

    private View.OnTouchListener chartViewOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mEventX = (int) event.getX();        //获取当前按下的坐标
            // 求图表像素坐标 换算成屏幕像素坐标
            screenEventTagX = chartLyt.getWidth();
            screenEventTagY = chartLyt.getHeight();
            Toast.makeText(getApplicationContext(), "横" + mEventX + "纵" + screenEventTagY, Toast.LENGTH_SHORT).show();
            showTv.setText("横" + screenEventTagX + "纵" + screenEventTagY);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    mEventX = (int) event.getX();        //获取当前按下的坐标
                    // 求图表像素坐标 换算成屏幕像素坐标
                    screenEventTagX = chartLyt.getWidth();
                    screenEventTagY = chartLyt.getHeight();
                    Toast.makeText(getApplicationContext(), "横" + mEventX + "纵" + screenEventTagY, Toast.LENGTH_SHORT).show();
                    showTv.setText("横" + screenEventTagX + "纵" + screenEventTagY);
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };

}
