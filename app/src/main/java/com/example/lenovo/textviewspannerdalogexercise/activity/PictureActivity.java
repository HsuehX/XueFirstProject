package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xueww on 2017/12/10.
 */

public class PictureActivity extends Activity {
    @Bind(R.id.zjxj_iv)
    ImageView zjxjIv;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        loadPictureVoidOne();
        loadPictureVoidTwo();
        loadPictureVoidSize();

        /**第三种**/
//        imageLoader = ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));这么写不行
        //Image Loader是个单例，init返回的是个构造者对象
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        //默认图片（未加载），空图片（加载的是空图片），加载失败,,高速缓存，磁盘缓存
        options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher).showImageForEmptyUri(R.mipmap.ic_action_discard)
                .showImageOnFail(R.mipmap.ic_launcher_round).cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        String url = "http://pic26.photophoto.cn/20130116/0005018395884700_b.jpg";
        imageLoader.displayImage(url, zjxjIv, options);
    }


    private void loadPictureVoidOne() {
        //先加上这句话不然会报java.lang.IllegalStateException: ImageLoader must be init with configuration before using字面意思是在使用前要初始化
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        final ImageView mImageView = (ImageView) findViewById(R.id.image_one);
        String imageUrl = "http://a.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=76cc4c9eaa773912c4738d65cd29aa2e/71cf3bc79f3df8dc51cf85b2ce11728b47102826.jpg";
        ImageLoader.getInstance().loadImage(imageUrl, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view,
                                        FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                mImageView.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    /**
     * 加载图片的第二个方法 貌似比第一个简单点
     */
    private void loadPictureVoidTwo() {
        //先加上这句话不然会报java.lang.IllegalStateException: ImageLoader must be init with configuration before using字面意思是在使用前要初始化
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        final ImageView mImageView = (ImageView) findViewById(R.id.image_two);
        String imageUrl = "http://i1.hdslb.com/bfs/archive/5b84ec5c2bb423dce3f63f6c63076da2f3aa3061.jpg";

        ImageLoader.getInstance().loadImage(imageUrl, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }

        });
    }


    /**
     * 加载图片时控制加载图片的大小
     */
    private void loadPictureVoidSize() {
        //先加上这句话不然会报java.lang.IllegalStateException: ImageLoader must be init with configuration before using字面意思是在使用前要初始化
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        final ImageView mImageView = (ImageView) findViewById(R.id.image_size);
//        String imageUrl = "http://pic26.photophoto.cn/20130116/0005018395884700_b.jpg";

        String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1521440530707&di=ec93eeff5d4c86dbc96b9fd6c5f84cba&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D6972df886481800a6ee58906813533d6%2F16c14124ab18972b6c2d26cdeecd7b899f510afd.jpg";


        ImageSize mImageSize = new ImageSize(800, 800);//控制图片大小

        //只是多了一个参数，用来控制图片大小
        ImageLoader.getInstance().loadImage(imageUrl, mImageSize, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                mImageView.setImageBitmap(loadedImage);
            }

        });
    }
}
