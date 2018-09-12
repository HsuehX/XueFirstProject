package com.example.lenovo.textviewspannerdalogexercise.activity.webreceiver;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by xueww on 2017/12/8.
 */

public class MessageTools {
    public static void ShowDialog(Context context, String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text);

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}