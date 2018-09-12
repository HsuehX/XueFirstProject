package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.lenovo.textviewspannerdalogexercise.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by xueww on 2017/12/11.
 */

public class PortDrawingActivity extends Activity {
    LinearLayout chart;
    GraphicalView chartView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_drawing);
        chart = (LinearLayout) findViewById(R.id.chart);
        showChart();
    }

    private void showChart() {
        XYMultipleSeriesDataset mDataSet=getDataSet();
        XYMultipleSeriesRenderer mRefender=getRefender();
        chartView= ChartFactory.getLineChartView(this, mDataSet, mRefender);
        chart.addView(chartView);
    }

    private XYMultipleSeriesDataset getDataSet() {
        XYMultipleSeriesDataset seriesDataset=new XYMultipleSeriesDataset();
        XYSeries xySeries1=new XYSeries("北京最近7天最高温度变化趋势");
        xySeries1.add(1, 36);
        xySeries1.add(2, 30);
        xySeries1.add(3, 27);
        xySeries1.add(4, 29);
        xySeries1.add(5, 34);
        xySeries1.add(6, 28);
        xySeries1.add(7, 33);
        seriesDataset.addSeries(xySeries1);

        XYSeries xySeries2=new XYSeries("北京最近7天最低温度变化趋势");
        xySeries2.add(1, 27);
        xySeries2.add(2, 22);
        xySeries2.add(3, 20);
        xySeries2.add(4, 21);
        xySeries2.add(5, 25);
        xySeries2.add(6, 22);
        xySeries2.add(7, 23);
        seriesDataset.addSeries(xySeries2);

        return seriesDataset;
    }
    private XYMultipleSeriesRenderer getRefender() {
        /*描绘器，设置图表整体效果，比如x,y轴效果，缩放比例，颜色设置*/
        XYMultipleSeriesRenderer seriesRenderer=new XYMultipleSeriesRenderer();

        seriesRenderer.setChartTitleTextSize(20);//设置图表标题的字体大小(图的最上面文字)
        seriesRenderer.setMargins(new int[] { 40, 30, 30, 20 });//设置外边距，顺序为：上左下右
        //坐标轴设置
        seriesRenderer.setAxisTitleTextSize(16);//设置坐标轴标题字体的大小
        seriesRenderer.setYAxisMin(-50);//设置y轴的起始值
        seriesRenderer.setYAxisMax(50);//设置y轴的最大值
        seriesRenderer.setXAxisMin(0.5);//设置x轴起始值
        seriesRenderer.setXAxisMax(7.5);//设置x轴最大值
        seriesRenderer.setXTitle("日期");//设置x轴标题
        seriesRenderer.setYTitle("温度");//设置y轴标题
        //颜色设置
        seriesRenderer.setApplyBackgroundColor(true);//是应用设置的背景颜色
        seriesRenderer.setLabelsColor(0xFF85848D);//设置标签颜色
        seriesRenderer.setBackgroundColor(Color.argb(100, 255, 231, 224));//设置图表的背景颜色
        //缩放设置
        seriesRenderer.setZoomButtonsVisible(false);//设置缩放按钮是否可见
        seriesRenderer.setZoomEnabled(false); //图表是否可以缩放设置
        seriesRenderer.setZoomInLimitX(7);
//      seriesRenderer.setZoomRate(1);//缩放比例设置
        //图表移动设置
        seriesRenderer.setPanEnabled(false);//图表是否可以移动

        //legend(最下面的文字说明)设置
//      seriesRenderer.setShowLegend(true);//控制legend（说明文字 ）是否显示
//      seriesRenderer.setLegendHeight(80);//设置说明的高度，单位px
//      seriesRenderer.setLegendTextSize(16);//设置说明字体大小
        //坐标轴标签设置
        seriesRenderer.setLabelsTextSize(14);//设置标签字体大小
        seriesRenderer.setXLabelsAlign(Paint.Align.CENTER);
        seriesRenderer.setYLabelsAlign(Paint.Align.LEFT);
        seriesRenderer.setXLabels(0);//显示的x轴标签的个数
        seriesRenderer.addXTextLabel(1, "6/24");//针对特定的x轴值增加文本标签
        seriesRenderer.addXTextLabel(2, "6/25");
        seriesRenderer.addXTextLabel(3, "6/26");
        seriesRenderer.addXTextLabel(4, "6/27");
        seriesRenderer.addXTextLabel(5, "6/28");
        seriesRenderer.addXTextLabel(6, "6/29");
        seriesRenderer.addXTextLabel(7, "今天");
        seriesRenderer.setPointSize(3);//设置坐标点大小

        seriesRenderer.setMarginsColor(Color.WHITE);//设置外边距空间的颜色
        seriesRenderer.setClickEnabled(false);
        seriesRenderer.setChartTitle("北京最近7天温度变化趋势图");

        /*某一组数据的描绘器，描绘该组数据的个性化显示效果，主要是字体跟颜色的效果*/
        XYSeriesRenderer xySeriesRenderer1=new XYSeriesRenderer();
        xySeriesRenderer1.setAnnotationsColor(0xFFFF0000);//设置注释（注释可以着重标注某一坐标）的颜色
        xySeriesRenderer1.setAnnotationsTextAlign(Paint.Align.CENTER);//设置注释的位置
        xySeriesRenderer1.setAnnotationsTextSize(12);//设置注释文字的大小
        xySeriesRenderer1.setPointStyle(PointStyle.CIRCLE);//坐标点的显示风格
        xySeriesRenderer1.setPointStrokeWidth(3);//坐标点的大小
        xySeriesRenderer1.setColor(0xFFF46C48);//表示该组数据的图或线的颜色
        xySeriesRenderer1.setDisplayChartValues(false);//设置是否显示坐标点的y轴坐标值
        xySeriesRenderer1.setChartValuesTextSize(12);//设置显示的坐标点值的字体大小

        /*某一组数据的描绘器，描绘该组数据的个性化显示效果，主要是字体跟颜色的效果*/
        XYSeriesRenderer xySeriesRenderer2=new XYSeriesRenderer();
        xySeriesRenderer2.setPointStyle(PointStyle.CIRCLE);//坐标点的显示风格
        xySeriesRenderer2.setPointStrokeWidth(3);//坐标点的大小
        xySeriesRenderer2.setColor(0xFF00C8FF);//表示该组数据的图或线的颜色
        xySeriesRenderer2.setDisplayChartValues(false);//设置是否显示坐标点的y轴坐标值
        xySeriesRenderer2.setChartValuesTextSize(12);//设置显示的坐标点值的字体大小

        seriesRenderer.addSeriesRenderer(xySeriesRenderer1);
        seriesRenderer.addSeriesRenderer(xySeriesRenderer2);
        return seriesRenderer;
    }
}
