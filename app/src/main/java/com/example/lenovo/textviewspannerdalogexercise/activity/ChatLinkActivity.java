package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.networkonuse.NetService;
import com.example.lenovo.textviewspannerdalogexercise.networkonuse.base.ViewCallback;
import com.example.lenovo.textviewspannerdalogexercise.networkonuse.service.ChatLinkService;
import com.example.lenovo.textviewspannerdalogexercise.networkonuse.subscriber.ProgressHttpSubscriber;
import com.example.lenovo.textviewspannerdalogexercise.utils.Constant;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xue on 2018/10/16.
 */

public class ChatLinkActivity extends Activity {
    @Bind(R.id.m_content_et)
    EditText mContentEt;
    @Bind(R.id.m_sent_bt)
    Button mSentBt;
    @Bind(R.id.m_content_tv)
    TextView mContentTv;
    @Bind(R.id.btn_link)
    Button mLinkBtn;
    @Bind(R.id.btn_close)
    Button mCloseBtn;

    private WebSocketClient mSocketClient;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mContentTv.setText(mContentTv.getText() + "\n" + msg.obj);
        }
    };

    ChatLinkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_link);
        ButterKnife.bind(this);

        service = new ChatLinkService(this);
//        initView();
    }

    private void initView() {
        mSentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSocketClient != null) {
                    mSocketClient.send(mContentEt.getText().toString().trim());
                }
            }
        });
    }

    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //TODO 这里URL 别忘了切换到自己的IP
//                    mSocketClient = new WebSocketClient(new URI("ws://192.168.5.101:8080/websocket"), new Draft_10()) {
                    mSocketClient = new WebSocketClient(new URI("ws://192.168.5.101:8080/websocket")) {

                        @Override
                        public void onOpen(ServerHandshake handshakedata) {
                            Log.e("picher_log", "打开通道" + handshakedata.getHttpStatus());
                            handler.obtainMessage(0, 111).sendToTarget();
                        }

                        @Override
                        public void onMessage(String message) {
                            Log.e("picher_log", "接收消息" + message);
                            handler.obtainMessage(0, message).sendToTarget();
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.e("picher_log", "通道关闭");
                            handler.obtainMessage(0, 111).sendToTarget();
                        }

                        @Override
                        public void onError(Exception ex) {
                            Log.e("picher_log", "链接错误");
                        }
                    };
                    mSocketClient.connect();

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void send() {
        String say = mContentEt.getText().toString().trim();
        service.groupChat("xue", say, new ViewCallback() {
            @Override
            public void returnResult(boolean result, Object... objects) {
                if (result) {
                } else {
                    mSentBt.setText(objects[0].toString());
                }
            }
        });
    }


    @OnClick({R.id.btn_link, R.id.m_sent_bt, R.id.btn_close})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_link:
                init();
                break;
            case R.id.m_sent_bt:
//                if (mSocketClient != null) {
//                    mSocketClient.send(mContentEt.getText().toString().trim());
//                }
                send();
                break;
            case R.id.btn_close:
                close();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        close();
    }

    private void close() {
        if (mSocketClient != null) {
            mSocketClient.close();
        }
    }
}
