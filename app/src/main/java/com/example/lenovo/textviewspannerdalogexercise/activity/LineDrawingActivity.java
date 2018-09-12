package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;
import static com.example.lenovo.textviewspannerdalogexercise.R.id.chart;

/**
 * Created by xueww on 2017/12/11.
 */

public class LineDrawingActivity extends Activity {
    private final static String TAG = LineDrawingActivity.class.getSimpleName();

    private LineChart mLineChart;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_drawing);
        init();
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

    private void init() {
        mLineChart = (LineChart) findViewById(R.id.spread_line_chart);
        LineData lineData = getLineData(36, 100);//这里传过去的数据并没有什么用，可以不设置的
//        LineData lineDataTwo = getLineDataTwo();

        showChart(mLineChart, lineData, Color.rgb(137, 230, 81));
//        showChart(mLineChart, lineDataTwo, Color.rgb(137, 230, 81));

    }

    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);//是否在折线图上添加边框
        lineChart.setDescription("");//图表默认右下方的描述

        lineChart.setNoDataTextDescription("给表格添加数据");//折线图没有数据的时候

        lineChart.setDrawGridBackground(false);//设置图表内格子背景是否显示，默认是false
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); //设置格子背景色,参数是Color类型对象

        lineChart.setTouchEnabled(true); //可点击

        lineChart.setDragEnabled(true);  //可拖拽
        lineChart.setScaleEnabled(false);  //可缩放

        lineChart.setPinchZoom(false);//设置x轴和y轴能否同时缩放。默认是否

//        lineChart.setBackgroundColor(color); //设置背景颜色

        /**
         * 设置数据
         */
        lineChart.setData(lineData);//设置数据
        Legend legend = lineChart.getLegend();//// 设置比例图标示，就是那个一组y的value的
        legend.setForm(Legend.LegendForm.LINE);//样式
        legend.setFormSize(6f);//表格大小
        legend.setTextColor(Color.WHITE); //字体颜色
        legend.setEnabled(false);//设置图例不可用

        /**
         * x轴相关
         */
        lineChart.setVisibleXRange(0, 300); //x轴可显示的坐标范围
        XAxis xAxis = lineChart.getXAxis();//x轴的标识
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//x轴显示位置，这个样式只在右侧x轴的下部显示
        xAxis.setTextColor(Color.GRAY);    //字体的颜色
        xAxis.setTextSize(10f); //字体大小
        xAxis.setGridColor(Color.GRAY);//网格线颜色
        xAxis.setDrawGridLines(false); //不显示网格线
//        xAxis.setTypeface(sadadas);//字体样式

        xAxis.setLabelsToSkip(60);    //设置坐标相隔多少，参数是int类型
//        xAxis.resetLabelsToSkip();   //将自动计算坐标相隔多少

        /**
         * y轴相关
         */
        YAxis axisLeft = lineChart.getAxisLeft(); //y轴左边标示
        YAxis axisRight = lineChart.getAxisRight(); //y轴右边标示
        axisLeft.setTextColor(Color.TRANSPARENT); //字体颜色
        axisLeft.setTextSize(10f); //字体大小
        axisLeft.setAxisMaxValue(6f); //y轴最大值
        axisLeft.setLabelCount(6, true); //显示格数
        axisLeft.setGridColor(Color.GRAY); //网格线颜色
//        axisLeft.setTypeface(mTf);

        axisRight.setDrawAxisLine(false); //是否绘制坐标轴的线，即含有坐标的那条线，默认是true
        axisRight.setDrawGridLines(false);//是否显示X坐标轴上的刻度竖线，默认是true
        axisRight.setDrawLabels(false);//是否显示y坐标轴上的刻度，默认是true
        axisLeft.setDrawGridLines(false);//将x轴上表格显示的线隐藏

//        lineChart.animateX(2500);  //立即执行动画

        /**
         * 点击事件
         */
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                Toast.makeText(getApplicationContext(), "横" + mLineChart.getWidth() + "纵" + mLineChart.getHeight(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        lineChart.setOnTouchListener(chartViewOnTouchListener);
    }

    private View.OnTouchListener chartViewOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
//            Toast.makeText(getApplicationContext(), "横" + event.getX() + "纵" + event.getY(), Toast.LENGTH_SHORT).show();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Toast.makeText(getApplicationContext(), "横" + event.getX() + "纵" + event.getY(), Toast.LENGTH_SHORT).show();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };

    /**
     * //x轴刻度0~36，y轴0~1000
     * 和相关属性
     *
     * @param count
     * @param range
     * @return
     */
    private LineData getLineData(int count, float range) {

        List<String> yValuesTest = new ArrayList<String>();
        yValuesTest.add(new String("分钟广播"));
        yValuesTest.add(new String("心跳异常"));
        yValuesTest.add(new String("MDM生存时间"));
        yValuesTest.add(new String("设备出错"));
        yValuesTest.add(new String("设备出错"));
        yValuesTest.add(new String("删除日志"));

        /**
         * 横坐标，x轴只有一个
         */
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < 1500; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add(i / 60 + ":00");
        }


        //y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
//            float value = (int) (Math.random() * 10);//设置了个随机数 使y轴成不同样式分布
            yValues.add(new Entry(1, 60 * i));
        }

        /**
         * 第二条
         */
        //y轴的数据
        ArrayList<Entry> yValues2 = new ArrayList<Entry>();
//        for (int i = 0; i < 16; i++) {
////            yValues2.add(new Entry(2, 100 * i));
//            yValues2.add(new Entry(0, 60 * i));
//            yValues2.add(new Entry(2, 120));
//            yValues2.add(new Entry(2, 120));
//        }
        yValues2.add(new Entry(0, 60));
        yValues2.add(new Entry(2, 120));
        yValues2.add(new Entry(2, 180));
        yValues2.add(new Entry(0, 240));
        yValues2.add(new Entry(2, 300));
        yValues2.add(new Entry(2, 360));
        yValues2.add(new Entry(0, 420));
        yValues2.add(new Entry(0, 480));
        yValues2.add(new Entry(2, 540));
        yValues2.add(new Entry(2, 600));
        yValues2.add(new Entry(0, 660));
        yValues2.add(new Entry(2, 720));
        yValues2.add(new Entry(2, 780));
        yValues2.add(new Entry(0, 840));
        yValues2.add(new Entry(0, 900));
        yValues2.add(new Entry(2, 960));
        yValues2.add(new Entry(2, 1020));
        yValues2.add(new Entry(0, 1080));
        yValues2.add(new Entry(2, 1140));
        yValues2.add(new Entry(2, 1200));
        yValues2.add(new Entry(0, 1260));


        /**
         * 第三条
         */
        //y轴的数据
        ArrayList<Entry> yValues3 = new ArrayList<Entry>();
        for (int i = 0; i < 24; i++) {
            yValues3.add(new Entry(3, 50 * i));
        }

//        yValues3.add(new Entry(0, 60));
//        yValues3.add(new Entry(3, 120));
//        yValues3.add(new Entry(3, 180));
//        yValues3.add(new Entry(0, 240));
//        yValues3.add(new Entry(3, 300));
//        yValues3.add(new Entry(3, 360));
//        yValues3.add(new Entry(0, 420));
//        yValues3.add(new Entry(0, 480));
//        yValues3.add(new Entry(3, 540));
//        yValues3.add(new Entry(3, 600));
//        yValues3.add(new Entry(0, 660));
//        yValues3.add(new Entry(3, 720));
//        yValues3.add(new Entry(3, 780));
//        yValues3.add(new Entry(0, 840));
//        yValues3.add(new Entry(0, 900));
//        yValues3.add(new Entry(3, 960));
//        yValues3.add(new Entry(3, 1020));
//        yValues3.add(new Entry(0, 1080));
//        yValues3.add(new Entry(3, 1140));
//        yValues3.add(new Entry(3, 1200));
//        yValues3.add(new Entry(0, 1260));

        /**
         * 第四条
         */
        //y轴的数据
        ArrayList<Entry> yValues4 = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
            yValues4.add(new Entry(4, 70 * i));
        }


        /**
         * 第五条
         */
        //y轴的数据
        ArrayList<Entry> yValues5 = new ArrayList<Entry>();
        for (int i = 0; i < 7; i++) {
            yValues5.add(new Entry(5, 80 * i));
        }

        /**
         * 附在第三条上面
         */
        //y轴的数据
        ArrayList<Entry> yValues6 = new ArrayList<Entry>();
//        for (int i = 0; i < 16; i++) {
////            yValues2.add(new Entry(2, 100 * i));
//            yValues2.add(new Entry(0, 60 * i));
//            yValues2.add(new Entry(2, 120));
//            yValues2.add(new Entry(2, 120));
//        }
        yValues6.add(new Entry(0, 60));
        yValues6.add(new Entry(3, 120));
        yValues6.add(new Entry(3, 180));
        yValues6.add(new Entry(0, 240));
        yValues6.add(new Entry(3, 300));
        yValues6.add(new Entry(3, 360));
        yValues6.add(new Entry(0, 420));
        yValues6.add(new Entry(3, 1140));
        yValues6.add(new Entry(3, 1200));
        yValues6.add(new Entry(0, 1260));



        // 折线  折线显示的数据是y轴的数据yValues，改成xValues就是x轴的
        LineDataSet lineDataSet = new LineDataSet(yValues, "");
//        LineDataSet lineDataSet2 = new LineDataSet(yValues2, "第二条");//第二条
        LineDataSet lineDataSet3 = new LineDataSet(yValues3, "");//
        LineDataSet lineDataSet4 = new LineDataSet(yValues4, "");//
        LineDataSet lineDataSet5 = new LineDataSet(yValues5, "");//

        //折线的参数
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(20f);// 显示的圆形大小
        lineDataSet.setColor(Color.TRANSPARENT);// 折线显示颜色
        lineDataSet.setCircleColor(Color.BLUE);// 圆形的颜色
        lineDataSet.setDrawCircleHole(false);//设置折线的圆点是否为空心
        lineDataSet.setHighLightColor(Color.BLUE); // 设置点击某个点时，横竖两条线的颜色
        lineDataSet.setHighlightEnabled(true);//允许通过点击高亮突出
        lineDataSet.setValueTextColor(Color.BLUE); //数值显示的颜色
        lineDataSet.setValueTextSize(8f);     //数值显示的大小
        lineDataSet.setDrawValues(false);//不显示文本信息
//        lineDataSet.setValueTypeface(mTf);/设置字体样式


        //第二条线
//        ArrayList<Entry> yValuesDuan = new ArrayList<Entry>();

//        LineDataSet lineDataSet2 = null;
        ArrayList<LineDataSet> lineDataSetsTwo = new ArrayList<LineDataSet>();
        for (int i = 0; i < yValues2.size() - 1; i++) {
            if ((yValues2.get(i).getVal() - yValues2.get(i + 1).getVal()) == 0 && (yValues2.get(i).getVal() == 2)) {
                ArrayList<Entry> yValuesDuan = new ArrayList<Entry>();
                yValuesDuan.add(yValues2.get(i));
                yValuesDuan.add(yValues2.get(i + 1));
                LineDataSet lineDataSet2 = new LineDataSet(yValuesDuan, "");

                lineDataSet2.setLineWidth(1.75f); // 线宽
                lineDataSet2.setCircleSize(0);// 显示的圆形大小
                lineDataSet2.setDrawCircleHole(false);//设置折线的圆点是否为空心
                lineDataSet2.setColor(Color.YELLOW);// 折线显示颜色
                lineDataSet2.setCircleColor(Color.YELLOW);// 圆形的颜色
                lineDataSet2.setHighLightColor(Color.YELLOW); // 设置点击某个点时，横竖两条线的颜色
                lineDataSet2.setHighlightEnabled(true);//允许通过点击高亮突出
                lineDataSet2.setValueTextColor(Color.YELLOW); //数值显示的颜色
                lineDataSet2.setValueTextSize(8f);     //数值显示的大小
                lineDataSet2.setDrawValues(false);//不显示文本信息

                lineDataSetsTwo.add(lineDataSet2);
                continue;
            } else {
                continue;
            }

        }

        //第三条线
        lineDataSet3.setLineWidth(1.75f); // 线宽
        lineDataSet3.setCircleSize(0);// 显示的圆形大小
        lineDataSet3.setDrawCircleHole(false);//设置折线的圆点是否为空心
        lineDataSet3.setColor(Color.CYAN);// 折线显示颜色
        lineDataSet3.setCircleColor(Color.CYAN);// 圆形的颜色
        lineDataSet3.setHighLightColor(Color.CYAN); // 设置点击某个点时，横竖两条线的颜色
        lineDataSet3.setHighlightEnabled(true);//允许通过点击高亮突出
        lineDataSet3.setValueTextColor(Color.CYAN); //数值显示的颜色
        lineDataSet3.setValueTextSize(8f);     //数值显示的大小
        lineDataSet3.setDrawValues(false);//不显示文本信息

        //第四条线
        lineDataSet4.setLineWidth(1.75f); // 线宽
        lineDataSet4.setCircleSize(6f);// 显示的圆形大小
        lineDataSet4.setDrawCircleHole(false);//设置折线的圆点是否为空心
        lineDataSet4.setColor(Color.TRANSPARENT);// 折线显示颜色
        lineDataSet4.setCircleColor(Color.GREEN);// 圆形的颜色
        lineDataSet4.setHighLightColor(Color.GREEN); // 设置点击某个点时，横竖两条线的颜色
        lineDataSet4.setHighlightEnabled(true);//允许通过点击高亮突出
        lineDataSet4.setValueTextColor(Color.GREEN); //数值显示的颜色
        lineDataSet4.setValueTextSize(8f);     //数值显示的大小
        lineDataSet4.setDrawValues(false);//不显示文本信息

        //第五条线
        lineDataSet5.setLineWidth(1.75f); // 线宽
        lineDataSet5.setCircleSize(5f);// 显示的圆形大小
        lineDataSet5.setDrawCircleHole(false);//设置折线的圆点是否为空心
        lineDataSet5.setColor(Color.TRANSPARENT);// 折线显示颜色
        lineDataSet5.setCircleColor(Color.RED);// 圆形的颜色
        lineDataSet5.setHighLightColor(Color.RED); // 设置点击某个点时，横竖两条线的颜色
        lineDataSet5.setHighlightEnabled(true);//允许通过点击高亮突出
        lineDataSet5.setValueTextColor(Color.RED); //数值显示的颜色
        lineDataSet5.setValueTextSize(8f);     //数值显示的大小
        lineDataSet5.setDrawValues(false);//不显示文本信息


        //附在第三条上面
        ArrayList<LineDataSet> lineDataSetsSix = new ArrayList<LineDataSet>();
        for (int i = 0; i < yValues6.size() - 1; i++) {
            if ((yValues6.get(i).getVal() - yValues6.get(i + 1).getVal()) == 0 && (yValues6.get(i).getVal() == 3)) {
                ArrayList<Entry> yValuesDuan = new ArrayList<Entry>();
                yValuesDuan.add(yValues6.get(i));
                yValuesDuan.add(yValues6.get(i + 1));
                LineDataSet lineDataSet6 = new LineDataSet(yValuesDuan, "");

                lineDataSet6.setLineWidth(2.0f); // 线宽
                lineDataSet6.setCircleSize(0);// 显示的圆形大小
                lineDataSet6.setDrawCircleHole(false);//设置折线的圆点是否为空心
                lineDataSet6.setColor(Color.WHITE);// 折线显示颜色
                lineDataSet6.setCircleColor(Color.WHITE);// 圆形的颜色
                lineDataSet6.setHighLightColor(Color.WHITE); // 设置点击某个点时，横竖两条线的颜色
                lineDataSet6.setHighlightEnabled(true);//允许通过点击高亮突出
                lineDataSet6.setValueTextColor(Color.WHITE); //数值显示的颜色
                lineDataSet6.setValueTextSize(8f);     //数值显示的大小
                lineDataSet6.setDrawValues(false);//不显示文本信息

                lineDataSetsTwo.add(lineDataSet6);
                continue;
            } else {
                continue;
            }

        }

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // 添加数据集合
//        lineDataSets.add(lineDataSet2);
        lineDataSets.add(lineDataSet3);
        lineDataSets.add(lineDataSet4);
        lineDataSets.add(lineDataSet5);

        lineDataSets.addAll(lineDataSetsTwo);
        lineDataSets.addAll(lineDataSetsSix);
        //创建lineData
        LineData lineData = new LineData(xValues, lineDataSets);

        return lineData;
    }


}