package com.example.lenovo.textviewspannerdalogexercise.activity.photo.activity;

import android.support.v4.app.FragmentTransaction;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.photo.PhotoBaseActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.photo.PhotoBaseFragment;
import com.example.lenovo.textviewspannerdalogexercise.activity.photo.fragment.ChooseTakePhotoFragment;

/**
 * Created by xue on 2018/4/19.
 */

public class ChooseTakePhotoActivity extends PhotoBaseActivity {

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_choose_take_photo);
    }

    @Override
    public void initViews() {
        initMainFragment();
    }

    /**
     * 初始化内容Fragment
     *
     * @return void
     */
    public void initMainFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PhotoBaseFragment mFragment = ChooseTakePhotoFragment.newInstance();
        transaction.replace(R.id.main_act_container, mFragment, mFragment.getFragmentName());
        transaction.commit();
    }

    @Override
    public void initEvents() {

    }
}

