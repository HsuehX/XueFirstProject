package com.example.lenovo.textviewspannerdalogexercise.activity.photo.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.photo.PhotoBaseFragment;

import butterknife.Bind;

/**
 * Created by xue on 2018/4/20.
 */

public class ChooseTakePhotoFragment extends PictureSelectFragment {

    /**
     * Toolbar
     */
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.main_frag_picture_iv)
    ImageView mPictureIv;

    public static ChooseTakePhotoFragment newInstance() {
        return new ChooseTakePhotoFragment();
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_choose_take_photo;
    }

    @Override
    public void initViews(View view) {
//        initToolbar(toolbar);
    }

    @Override
    public void initEvents() {
        // 设置图片点击监听
        mPictureIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });
        // 设置裁剪图片结果监听
        setOnPictureSelectedListener(new OnPictureSelectedListener() {
            @Override
            public void onPictureSelected(Uri fileUri, Bitmap bitmap) {
                mPictureIv.setImageBitmap(bitmap);

                String filePath = fileUri.getEncodedPath();
                String imagePath = Uri.decode(filePath);
                Toast.makeText(mContext, "图片已经保存到:" + imagePath, Toast.LENGTH_LONG).show();
            }
        });
    }

}

