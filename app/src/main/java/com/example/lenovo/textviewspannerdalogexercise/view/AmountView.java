package com.example.lenovo.textviewspannerdalogexercise.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;

import static com.example.lenovo.textviewspannerdalogexercise.R.id.number_add_iv;
import static com.example.lenovo.textviewspannerdalogexercise.R.id.number_reduction_iv;

//import static com.example.lenovo.textviewspannerdalogexercise.R.id.btnDecrease;
//import static com.example.lenovo.textviewspannerdalogexercise.R.id.btnIncrease;

/**
 * Created by xueww on 2017/12/27.
 */

public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 0; //购买数量
    private int goods_storage = 0; //商品库存

    private OnAmountChangeListener mListener;

    private TextView etAmount;
    private ImageView mAddIv;
    private ImageView mReductionIv;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (TextView) findViewById(R.id.content_buy_count_tv);
        mReductionIv = (ImageView) findViewById(number_reduction_iv);
        mAddIv = (ImageView) findViewById(number_add_iv);
        mReductionIv.setOnClickListener(this);
        mAddIv.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

//        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
//        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
//        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
//        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
//        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
//        obtainStyledAttributes.recycle();

//        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
//        mReductionIv.setLayoutParams(btnParams);
//        mAddIv.setLayoutParams(btnParams);
//        if (btnTextSize != 0) {
////            mReductionIv.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
////            mAddIv.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
//        }
//
//        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
//        etAmount.setLayoutParams(textParams);
//        if (tvTextSize != 0) {
//            etAmount.setTextSize(tvTextSize);
//        }

        judgeCount();
    }

    private void judgeCount() {
        if (amount > 0) {
            etAmount.setVisibility(View.VISIBLE);
            mReductionIv.setVisibility(View.VISIBLE);
        } else {
            etAmount.setVisibility(View.GONE);
            mReductionIv.setVisibility(View.GONE);
        }
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
//        this.amount = goods_storage;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == number_reduction_iv) {
            if (amount > 0) {
                amount--;
                judgeCount();
                etAmount.setText(amount + "");
            }
        } else if (i == number_add_iv) {
            if (amount < goods_storage) {
                amount++;
                judgeCount();
                etAmount.setText(amount + "");
            }
        }

        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            etAmount.setText(goods_storage + "");
            return;
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}
