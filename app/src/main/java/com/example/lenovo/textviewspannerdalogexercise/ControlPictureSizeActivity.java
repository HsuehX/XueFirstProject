package com.example.lenovo.textviewspannerdalogexercise;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by xue on 2018/4/19.
 */

public class ControlPictureSizeActivity extends Activity {
    private final String TAG = "ControlPictureSize";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_picture_size);
        ButterKnife.bind(this);
        init();
        setPictureSize();
    }

    private void setPictureSize() {
        String readPath = "/sdcard/desktop.jpg";
        String writePah = "/sdcard/picSize.jpg";

        Bitmap myBitmap = BitmapFactory.decodeFile(readPath);
        File img = new File(writePah);
        try {
            img.createNewFile();
            FileOutputStream fos = new FileOutputStream(img);

            Bitmap endBit = Bitmap.createScaledBitmap(myBitmap, 600, 800, true); //创建新的图像大小
            endBit.compress(Bitmap.CompressFormat.PNG, 100, fos);
//        如果不想修改结果大小，则将上面两句话改为：
//            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("LongLogTag")
    private void init() {
        Camera camera = Camera.open();

        List<Camera.Size> pictureSizes = camera.getParameters().getSupportedPictureSizes();
        List<Camera.Size> previewSizes = camera.getParameters().getSupportedPreviewSizes();

        for (int i = 0; i < pictureSizes.size(); i++) {
            Camera.Size pSize = pictureSizes.get(i);
            Log.i(TAG + "-------initCamera", "---------------------PictureSize.width = " + pSize.width + "-----------------PictureSize.height = " + pSize.height);
        }

        for (int i = 0; i < previewSizes.size(); i++) {
            Camera.Size pSize = previewSizes.get(i);
            Log.i(TAG + "--------initCamera", "--------------------previewSize.width = " + pSize.width + "-----------------previewSize.height = " + pSize.height);
        }
    }
}
