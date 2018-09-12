package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.activity.bean.MsgBean;
import com.example.lenovo.textviewspannerdalogexercise.activity.bean.Utils;
import com.example.lenovo.textviewspannerdalogexercise.adapter.ChatAdapter;
import com.example.lenovo.textviewspannerdalogexercise.base.BaseActivity;
import com.example.lenovo.textviewspannerdalogexercise.utils.emoji_grid.EmojiAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xue on 2018/7/28.
 */

public class CharActivity extends BaseActivity implements View.OnClickListener {
    //表情扩展栏
    private List<Integer> emojiList;
    private int[] emojiIds = new int[107];
    private EmojiAdapter emojiAdapter;
    private GridView gridView;
    private ImageView imgEmoji;

    private ImageView iv_text_input;


    private ListView lv_list;
    private ChatAdapter mChatAdapter;
    private List<MsgBean> mList;
    private EditText et_input;
    private Button btn_input;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MsgBean bean = (MsgBean) msg.obj;

            mList.add(bean);
            mChatAdapter.notifyDataSetChanged();
            lv_list.setSelection(mList.size() - 1);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chat);
        initViews();
        initDatas();
        setEvent();
    }

    private void setEvent() {
        btn_input.setOnClickListener(this);
    }

    private void initDatas() {
        mList = new ArrayList<>();
        mChatAdapter = new ChatAdapter(this, mList);
        lv_list.setAdapter(mChatAdapter);
    }

    private void initViews() {
        lv_list = (ListView) findViewById(R.id.lv_list);
        et_input = (EditText) findViewById(R.id.et_input);
        btn_input = (Button) findViewById(R.id.btn_input);

        iv_text_input = (ImageView) findViewById(R.id.iv_text_input);


        imgEmoji = (ImageView) findViewById(R.id.img_emoji);
        imgEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gridView.getVisibility() == View.VISIBLE) {
                    gridView.setVisibility(View.GONE);
                } else {
                    gridView.setVisibility(View.VISIBLE);
                }
                closeSoftInput(CharActivity.this);
            }
        });

        initEmoji();
        et_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridView.setVisibility(View.GONE);
            }
        });


        iv_text_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSoftInput(CharActivity.this);
            }
        });

    }

    /**
     * 打开键盘.
     *
     * @param context the context
     */
    public static void showSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 关闭键盘事件.
     *
     * @param context the context
     */
    public static void closeSoftInput(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_input:
                final String send = et_input.getText().toString();
                if (TextUtils.isEmpty(send)) {
                    Toast.makeText(this, "can't be null", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    MsgBean bean = new MsgBean();
                    bean.setMsg(send);
                    bean.setType(MsgBean.Type.OUTCOMING);
                    bean.setDate(new Date());
                    mList.add(bean);
                    mChatAdapter.notifyDataSetChanged();
                    lv_list.setSelection(mList.size() - 1);
                    et_input.setText("");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            MsgBean bean = Utils.getMessage(send);
                            Message message = Message.obtain();
                            message.obj = bean;
                            mHandler.sendMessage(message);


                        }
                    }).start();


                }
                break;
        }

    }


    private void initEmoji() {
        gridView = (GridView) findViewById(R.id.emoji_grid);
        emojiList = new ArrayList<>();
        for (int i = 0; i < 107; i++) {
            if (i < 10) {
                int id = getResources().getIdentifier(
                        "f00" + i,
                        "drawable", getPackageName());
                emojiIds[i] = id;
                emojiList.add(id);
                Log.i("LHD", "emoji id: f00" + i);
            } else if (i < 100) {
                int id = getResources().getIdentifier(
                        "f0" + i,
                        "drawable", getPackageName());
                emojiIds[i] = id;
                emojiList.add(id);
                Log.i("LHD", "emoji id: f0" + i);
            } else {
                int id = getResources().getIdentifier(
                        "f" + i,
                        "drawable", getPackageName());
                emojiIds[i] = id;
                emojiList.add(id);
                Log.i("LHD", "emoji id: f" + i);
            }
        }
        emojiAdapter = new EmojiAdapter(CharActivity.this, emojiList);
        gridView.setAdapter(emojiAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("LHD", "emoji grid: " + i);
                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeResource(getResources(), emojiIds[i % emojiIds.length]);
                ImageSpan imageSpan = new ImageSpan(CharActivity.this, bitmap);
                String str = null;
                if (i < 10) {
                    str = "f00" + i;
                } else if (i < 100) {
                    str = "f0" + i;
                } else {
                    str = "f" + i;
                }
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                et_input.getText().insert(et_input.getSelectionStart(), spannableString);
            }
        });
    }
}
