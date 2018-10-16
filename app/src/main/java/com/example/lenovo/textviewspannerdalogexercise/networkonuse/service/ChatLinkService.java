package com.example.lenovo.textviewspannerdalogexercise.networkonuse.service;

import android.content.Context;

import com.example.lenovo.textviewspannerdalogexercise.networkonuse.NetService;
import com.example.lenovo.textviewspannerdalogexercise.networkonuse.base.ViewCallback;
import com.example.lenovo.textviewspannerdalogexercise.networkonuse.subscriber.ProgressHttpSubscriber;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by xue on 2018/10/16.
 */

public class ChatLinkService {
    Context mConetxt;

    public ChatLinkService(Context ctx) {
        mConetxt = ctx;
    }

    /**
     * 群聊接口
     */
    public void groupChat(String user, String say, final ViewCallback cb) {
        NetService.getInstance().groupChat(user, say, new ProgressHttpSubscriber<HashMap<String, String>>(mConetxt) {
            @Override
            public void onNext(HashMap<String, String> stringObjectHashMap) {
//                mSentBt.setText("已发送");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                cb.returnResult(false, "网络连接失败，请检查网络设置");
                e.printStackTrace();
            }
        });
    }
}
