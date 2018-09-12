package com.example.lenovo.textviewspannerdalogexercise.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import static com.iflytek.cloud.thirdparty.aw.d;
import static com.iflytek.cloud.thirdparty.aw.e;

/**
 * Created by xue on 2018/8/13.
 * 圆角imageview
 */

@SuppressLint("AppCompatCustomView")
public class FilletImageView extends ImageView {
    private static final String STATE_INSTANCE = "state_instance";//实例
    private static final String STATE_TYPE = "state_type";//类型
    private static final String STATE_BORDER_RADIUS = "state_border_radius";//边框圆角弧度
    private static final String STATE_BORDER_WIDTH = "state_border_width";//边框宽度
    private static final String STATE_BORDER_COLOR = "state_border_color";//边框颜色

    private static final int TYPE_NORMAL = -1;//正常的imageview
    private static final int TYPE_CIRCLE = 0;//圆形的imageview
    private static final int TYPE_ROUND = 1;//圆角的imageview
    private static final int BODER_RADIUS_DEFAULT = 10;//默认的边框弧度
    private static final int BORDER_WIDTH = 0;//边框宽度
    private static final int BORDER_COLOR = Color.BLACK;//边框颜色

    private int borderRadius;//边框圆角弧度
    private int type;//类型
    private int border_width;//边框宽度
    private int border_color;//边框颜色
    private Paint paint;
    private Paint border_paint;//边框画笔
    private Matrix matrix;//矩阵
    private int width;
    private int radius;
    private BitmapShader bitmapShader;//位图着色器
    /**
     * Rect的参数是  int型
     * RectF的参数是float型
     */
    private RectF rectF;//Rect类主要用于表示坐标系中的一块矩形区域，并可以对其做一些简单操作


    public FilletImageView(Context context) {
        super(context);
    }

    public FilletImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inital(context, attributeSet);
    }

    /**
     * TypedArray 通过检索res资源中结构的特定值的索引的到对应的资源
     * Android 自定义 View 的时候，需要使用 TypedArray 来获取 XML layout 中的属性值，
     * 使用完之后，需要调用 recycle() 方法将 TypedArray 回收。
     * obtainStyledAttributes()获得样式属性
     */
    private void inital(Context context, AttributeSet attrs) {
        matrix = new Matrix();
        paint = new Paint();
        border_paint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FilletImageView);
        //getDimension()参数：res资源文件属性，默认值
        //这个三个方法需要的参数全是尺寸（在res里自己定义的）
        borderRadius = dp2px(array.getDimension(R.styleable.FilletImageView_borderRadius, BODER_RADIUS_DEFAULT));
        type = array.getInt(R.styleable.FilletImageView_type, TYPE_NORMAL);
        border_width = dp2px(array.getDimension(R.styleable.FilletImageView_borderWidth, BORDER_WIDTH));
        border_color = array.getInt(R.styleable.FilletImageView_borderColor, BORDER_COLOR);
        array.recycle();//TypedArrayd的bean是pool  Pool是一个final类，本身不可变，不允许继承。
        // 而内部类SynchronizedPool通过继承SimplePool，并添加对象锁，实现了一个同步的对象池，
        // 保证了线程安全，同时也继承了对象池的优点，避免对象重复创建和销毁，减少了系统开销。
        //自定义view 使用TypedArray调用res资源文件，会随着每一次activity的create和create，所以会产生大量的内存资源的占用损耗，
        //所以要调用recycle()  使用池模式来回收
    }

    /***
     * 尺寸（显示的类型）
     * @param witdMeadsureSpec
     * @param heightMeasureSpec
     * getMeasuredWidth()获取的是view原始的大小，也就是这个view在XML文件中配置或者是代码中设置的大小。
     * getWidth（）获取的是这个view最终显示的大小，这个大小有可能等于原始的大小也有可能不等于原始大小。
     */
    protected void onMeasure(int witdMeadsureSpec, int heightMeasureSpec) {
        super.onMeasure(witdMeadsureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {//如果参数是圆
            width = Math.min(getMeasuredWidth(), getMeasuredHeight());//获取的是view原始的宽，高
            radius = width / 2 - border_width / 2;//宽/2   变成圆的半径 （用宽是因为 手机是长方形，宽比高短）
            setMeasuredDimension(width, width);//设置view的大小
        }
    }

    /**
     *
     */
    private void setBitmapShader() {
        /**
         * Drawable就是一个可画的对象，其可能是一张位图（BitmapDrawable），
         * 也可能是一个图形（ShapeDrawable），还有可能是一个图层（LayerDrawable）
         */
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        /**bitmap用来指定图案**/
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap == null) {
            return;
        }
        /**
         * TileMode.CLAMP:用边缘色彩填充多余空间
         * TileMode.REPEAT:重复原图像来填充多余空间
         * TileMode.MIRROR:重复使用镜像模式的图像来填充多余空间
         */
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;//大小
        int viewWidth = getWidth();//view宽
        int viewHeight = getHeight();//view高
        int drawableWidth = bitmap.getWidth();//取的是res里的大小  按照分辨率来
        int drawableHeight = bitmap.getHeight();
        float dx = 0;
        float dy = 0;

        float scale1 = 1.0f;
        float scale2 = 1.0f;
        //是否有边框
        final boolean fits = (drawableWidth < 0 || viewWidth == drawableWidth)
                && (drawableHeight < 0 || viewHeight == drawableHeight);
        if (type == TYPE_CIRCLE) {
            int size = Math.min(drawableWidth, drawableHeight);
            scale = width * 1.0f / size;
        } else if (type == TYPE_ROUND) {
            scale = Math.max(viewWidth * 1.0f / drawableWidth, viewHeight * 1.0f / drawableHeight);
        } else {
            return;
        }

        if (drawableWidth <= 0 || drawableHeight <= 0) {
            // 建立对应 bitmap 的画布  里面的参数是view显示的位置
            drawable.setBounds(0, 0, drawableWidth, drawableHeight);
            matrix = null;
        } else {
            drawable.setBounds(0, 0, drawableWidth, drawableHeight);
            //getScaleType()图片剪裁
            if (ScaleType.MATRIX == getScaleType()) {//matrix 用矩阵来绘制(从左上角起始的矩阵区域)
                //isIdentity()是否为矩阵
                if (matrix.isIdentity()) {
                    matrix = null;
                }
            } else if (fits) {
                matrix = null;
            } else if (ScaleType.CENTER == getScaleType()) {//如果是圆的话
                matrix.setTranslate(Math.round(viewWidth - drawableWidth) * 0.5f,
                        Math.round(viewHeight - drawableHeight) * 0.5f);
            } else if (ScaleType.CENTER_CROP == getScaleType()) {
                if (drawableWidth * viewHeight > drawableHeight * viewWidth) {
                    dx = (viewWidth - drawableWidth * scale) * 0.5f;
                } else {
                    dy = (viewHeight - drawableHeight * scale) * 0.5f;
                }
                matrix.setScale(scale, scale);
                matrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
            } else if (ScaleType.CENTER_INSIDE == getScaleType()) {
                if (drawableWidth <= viewWidth && drawableHeight <= viewHeight) {
                    scale = 1.0f;
                } else {
                    scale = Math.min((float) viewWidth / (float) drawableHeight,
                            (float) viewHeight / (float) drawableHeight);
                }
                dx = Math.round((viewWidth - drawableWidth * scale) * 0.5f);
                dy = Math.round((viewHeight - drawableHeight * scale) * 0.5f);
                matrix.setScale(scale, scale);//设置中心点
                matrix.postTranslate(dx, dy);
            } else {
                if (drawableWidth * viewHeight > drawableHeight * viewHeight) {//判断宽大还算是高大
                    dx = (viewWidth - drawableWidth * scale) * 0.5f;
                } else {
                    dy = (viewHeight - drawableHeight * scale) * 0.5f;
                }
                matrix.setScale(scale, scale);
                matrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
            }
        }
        if (ScaleType.FIT_XY == getScaleType() && matrix != null) {//非圆形
            scale1 = viewWidth * 1.0f / drawableWidth;
            scale2 = viewHeight * 1.0f / drawableHeight;
            matrix.setScale(scale1, scale2);//非圆形 宽高不同
        }
        bitmapShader.setLocalMatrix(matrix);// 设置shader的本地矩阵,如果localM为空将重置shader的本地矩阵。
        paint.setShader(bitmapShader); // 设置shader

    }

    /**
     * 画图
     *
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        paint.setAntiAlias(true);
        border_paint.setAntiAlias(true);
        border_paint.setStyle(Paint.Style.STROKE);//使用此样式绘制的几何和文本都将被填充
        border_paint.setColor(border_color);
        border_paint.setStrokeWidth(border_width);
        setBitmapShader();//设置view属性 形状 大小
        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(rectF, borderRadius, borderRadius, paint);//设置圆角的view(非圆形)
            if (border_width > 0) {
                canvas.drawRoundRect(rectF, borderRadius, borderRadius, border_paint);
            }
        } else if (type == TYPE_CIRCLE) {
            canvas.drawCircle(radius, radius, radius, paint);//设置圆形的view
            if (border_width > 0) {
                canvas.drawCircle(radius, radius, radius - border_width / 2, border_paint);
            }
        } else {
            getDrawable().draw(canvas);
        }
    }

    /**
     * dp转px
     */
    public int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    /**
     * 尺寸改变
     *
     * @param width
     * @param height
     * @param oldWidth
     * @param oldHeight
     */
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        if (type == TYPE_ROUND) {//如果是圆角
            rectF = new RectF(border_width / 2, border_width / 2,
                    getWidth() - border_width / 2, getHeight() - border_width / 2);
        }
    }

    /**
     * 保存实例状态
     *
     * @return
     */
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());//super必须写
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, borderRadius);
        bundle.putInt(STATE_BORDER_WIDTH, border_width);
        bundle.putInt(STATE_BORDER_COLOR, border_color);
        return bundle;
    }

    /**
     * 还原实例状态
     *
     * @param state
     */
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = new Bundle();
            super.onRestoreInstanceState(((Bundle) state).getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_BORDER_RADIUS);
            this.border_width = bundle.getInt(STATE_BORDER_WIDTH);
            this.border_color = bundle.getInt(STATE_BORDER_COLOR);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    /**
     * 设置view宽
     *
     * @param border_width
     */
    public void setBorder_width(int border_width) {
        int px = dp2px(border_width);
        if (this.border_width != px) {
            this.border_width = px;
            invalidate();//自动清屏/屏幕刷新   重新执行onDraw()方法
        }
    }

    /**
     * 设置颜色
     *
     * @param border_color
     */
    public void setBorder_color(int border_color) {
        if (this.border_color != border_color) {
            this.border_color = border_color;
            border_paint.setColor(border_color);
            invalidate();
        }
    }

    /**
     * 设置 弧度
     *
     * @param borderRadius
     */
    public void setBorderRadius(int borderRadius) {
        int px = dp2px(borderRadius);
        if (this.borderRadius != px) {
            this.borderRadius = px;
            invalidate();
        }
    }

    /***
     * 设置view类型
     * @param type
     */
    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE) {
                this.type = TYPE_NORMAL;
            }
            requestLayout();//重新执行View的绘制中的layout
        }
    }
}
