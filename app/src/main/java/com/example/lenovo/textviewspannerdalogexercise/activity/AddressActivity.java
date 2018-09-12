package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.Service.Service;
import com.example.lenovo.textviewspannerdalogexercise.bean.LocationBean;
import com.example.lenovo.textviewspannerdalogexercise.view.LocationWheelSelect;
import com.example.lenovo.textviewspannerdalogexercise.view.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xueww on 2017/9/14.
 */

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.sliding_btn)
    public Button mSlidingBtn;

    private TextView mAddressTv;
    private LocationBean mLocationBean;
    protected InputMethodManager mInputMethodManager;
    // 收件机构省
    private String mStrGetMailProvince;
    // 收件机构市
    private String mStrGetMailCity;
    // 收件机构县
    private String mStrGetMailCounty;
    private Service mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        mService = new Service();
        mAddressTv = (TextView) findViewById(R.id.address);
        mAddressTv.setOnClickListener(this);
        mLocationBean = mService.getLocationBean();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address:
                // 在此选择收件地址
                hideInputMethod();//隐藏软键盘
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mLocationBean.selectProvince = mStrGetMailProvince;
                        mLocationBean.selectCity = mStrGetMailCity;
                        mLocationBean.selectCounty = mStrGetMailCounty;
                        contrWindow(true);
                        new LocationWheelSelect().getLocation(AddressActivity.this, 0, 5, mLocationBean, "选择收件地址", mAddressTv,
                                new LocationWheelSelect.LocationCallback() {
                                    @Override
                                    public void selectLocation(String province, String city, String county, String xzqh) {
                                        mStrGetMailProvince = province;
                                        mStrGetMailCity = city;
                                        mStrGetMailCounty = county;
                                        mAddressTv.setText(mStrGetMailProvince
                                                + " > "
                                                + mStrGetMailCity
                                                + " > "
                                                + (StringUtils
                                                .isEmptyUnNull(mStrGetMailCounty) ? ""
                                                : mStrGetMailCounty));
                                        contrWindow(false);
                                    }

                                    @Override
                                    public void selectLcancel(String province, String city, String county, String xzqh) {
                                        contrWindow(false);
                                    }
                                });
                    }
                }, 100);
                break;
//            case R.id.sliding_btn:
//                Intent intent = new Intent(AddressActivity.this, SlidingActtivity.class);
//                startActivity(intent);
//                break;
            default:
                break;
        }
    }

    @OnClick({R.id.sliding_btn})
    public void onClcik(View v){
        switch (v.getId()){
            case R.id.sliding_btn:
                Intent intent = new Intent(AddressActivity.this, SlidingActtivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏软键盘
     */
    protected void hideInputMethod() {
        if (mInputMethodManager != null) {
            View view = getCurrentFocus();
            if (view != null) {
                mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


    /**
     * 屏幕背景控制
     */
    public void contrWindow(boolean isDark) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();

        if (isDark) {
            lp.alpha = 0.5f;
            getWindow().setAttributes(lp);
        } else {
            lp.alpha = 1f;
            getWindow().setAttributes(lp);
        }

    }
}
