package com.example.lenovo.textviewspannerdalogexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.activity.AesEncryptionActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.AudioWeixinActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.CharActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.ChatLinkActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.EvaluationActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.GifActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.NetControlPictureSizeActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.OriginalHttpNetworkActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.PhoneticWritingActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.PhotoCutting7Activity;
import com.example.lenovo.textviewspannerdalogexercise.activity.ReadWenJianActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.RecyclerviewActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.RefreshListActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.WheelViewDaActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.WriteDetailedActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.ZeroBuweiActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.internet.SimpleRetrofitActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.onactivityresult.FirstActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.photo.activity.ChooseTakePhotoActivity;
import com.example.lenovo.textviewspannerdalogexercise.activity.uploadimage.ShangChuanActivity;
import com.example.lenovo.textviewspannerdalogexercise.copywechatcircle.CopyWeChatCircleActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xue on 2018/2/12.
 */

public class SecondActivity extends Activity {
    @Bind(R.id.refresh_up_tv)
    TextView refresh_up_tv;
    @Bind(R.id.aes_tv)
    TextView aes_tv;
    @Bind(R.id.buwei_tv)
    TextView buwei_tv;
    @Bind(R.id.gif_tv)
    TextView gif_tv;
    @Bind(R.id.wenjian_tv)
    TextView wenjian_tv;
    @Bind(R.id.phonetic_writing_tv)
    TextView phonetic_writing_tv;
    @Bind(R.id.audiowweixin_tv)
    TextView audiowweixin_tv;
    @Bind(R.id.net_picture_size_tv)
    TextView net_picture_size_tv;
    @Bind(R.id.original_http_network_tv)
    TextView original_http_network_tv;
    @Bind(R.id.activityresult_tv)
    TextView activityresult_tv;
    @Bind(R.id.head_portrait_tv)
    TextView head_portrait_tv;
    @Bind(R.id.recyclerview_tv)
    TextView recyclerview_tv;
    @Bind(R.id.chat_tv)
    TextView chat_tv;
    @Bind(R.id.wheelview_tv)
    TextView wheelview_tv;
    @Bind(R.id.evaluation_tv)
    TextView evaluation_tv;
    @Bind(R.id.simple_retrofit_tv)
    TextView simple_retrofit_tv;
    @Bind(R.id.tv_copy_wechat_circle)
    TextView tv_copy_wechat_circle;
    @Bind(R.id.tv_chat_link)
    TextView tv_chat_link;
    @Bind(R.id.tv_write)
    TextView tv_write;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.refresh_up_tv, R.id.aes_tv, R.id.buwei_tv, R.id.gif_tv, R.id.wenjian_tv, R.id.phonetic_writing_tv,
            R.id.audiowweixin_tv, R.id.net_picture_size_tv, R.id.original_http_network_tv, R.id.activityresult_tv,
            R.id.head_portrait_tv, R.id.recyclerview_tv, R.id.chat_tv, R.id.wheelview_tv, R.id.evaluation_tv,
            R.id.simple_retrofit_tv, R.id.tv_copy_wechat_circle, R.id.tv_chat_link, R.id.tv_write})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.refresh_up_tv:
                Intent second_activityIntent = new Intent(SecondActivity.this, RefreshListActivity.class);
                startActivity(second_activityIntent);
                break;
            case R.id.aes_tv:
                Intent aes_activityIntent = new Intent(SecondActivity.this, AesEncryptionActivity.class);
                startActivity(aes_activityIntent);
                break;
            case R.id.buwei_tv:
                Intent buwei_tvIntent = new Intent(SecondActivity.this, ZeroBuweiActivity.class);
                startActivity(buwei_tvIntent);
                break;
            case R.id.gif_tv:
                Intent gif_tvIntent = new Intent(SecondActivity.this, GifActivity.class);
                startActivity(gif_tvIntent);
                break;
            case R.id.wenjian_tv:
                Intent wenjian_tvIntent = new Intent(SecondActivity.this, ReadWenJianActivity.class);
                startActivity(wenjian_tvIntent);
                break;
            case R.id.phonetic_writing_tv:
                Intent phonetic_writing_tvIntent = new Intent(SecondActivity.this, PhoneticWritingActivity.class);
                startActivity(phonetic_writing_tvIntent);
                break;
            case R.id.audiowweixin_tv:
                Intent audioWeixin_tvIntent = new Intent(SecondActivity.this, AudioWeixinActivity.class);
                startActivity(audioWeixin_tvIntent);
                break;
            case R.id.net_picture_size_tv:
                Intent net_picture_size_tvIntent = new Intent(SecondActivity.this, PhotoCutting7Activity.class);
                startActivity(net_picture_size_tvIntent);
                break;
            case R.id.original_http_network_tv:
                Intent original_http_network_tvIntent = new Intent(SecondActivity.this, OriginalHttpNetworkActivity.class);
                startActivity(original_http_network_tvIntent);
                break;
            case R.id.activityresult_tv:
                Intent activityresult_tvIntent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(activityresult_tvIntent);
                break;
            case R.id.head_portrait_tv:
                Intent head_portrait_tvIntent = new Intent(SecondActivity.this, ShangChuanActivity.class);
                startActivity(head_portrait_tvIntent);
                break;
            case R.id.recyclerview_tv:
                Intent recyclerview_tvIntent = new Intent(SecondActivity.this, RecyclerviewActivity.class);
                startActivity(recyclerview_tvIntent);
                break;
            case R.id.chat_tv:
                Intent chat_tvIntent = new Intent(SecondActivity.this, CharActivity.class);
                startActivity(chat_tvIntent);
                break;
            case R.id.wheelview_tv:
                Intent wheelview_tvIntent = new Intent(SecondActivity.this, WheelViewDaActivity.class);
                startActivity(wheelview_tvIntent);
                break;
            case R.id.evaluation_tv:
                Intent evaluation_tvIntent = new Intent(SecondActivity.this, EvaluationActivity.class);
                startActivity(evaluation_tvIntent);
                break;
            case R.id.simple_retrofit_tv:
                Intent simple_retrofit_tvIntent = new Intent(SecondActivity.this, SimpleRetrofitActivity.class);
                startActivity(simple_retrofit_tvIntent);
                break;
            case R.id.tv_copy_wechat_circle:
                Intent copy_wechat_circleIntent = new Intent(SecondActivity.this, CopyWeChatCircleActivity.class);
                startActivity(copy_wechat_circleIntent);
                break;
            case R.id.tv_chat_link:
                Intent tv_chat_linkIntent = new Intent(SecondActivity.this, ChatLinkActivity.class);
                startActivity(tv_chat_linkIntent);
                break;
            case R.id.tv_write:
                Intent tv_writeIntent = new Intent(SecondActivity.this, WriteDetailedActivity.class);
                startActivity(tv_writeIntent);
                break;
            default:
                break;
        }
    }
}
