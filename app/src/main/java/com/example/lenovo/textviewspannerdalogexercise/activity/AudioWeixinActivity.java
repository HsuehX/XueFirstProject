package com.example.lenovo.textviewspannerdalogexercise.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lenovo.textviewspannerdalogexercise.R;
import com.example.lenovo.textviewspannerdalogexercise.adapter.RecorderAdapter;
import com.example.lenovo.textviewspannerdalogexercise.bean.Recorder;
import com.example.lenovo.textviewspannerdalogexercise.manager.MediaManager;
import com.example.lenovo.textviewspannerdalogexercise.view.audioweixin.AudioRecordButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xue on 2018/4/18.
 */

public class AudioWeixinActivity extends Activity {
    AudioRecordButton button;

    private ListView mlistview;
    private ArrayAdapter<Recorder> mAdapter;
    private View viewanim;
    private List<Recorder> mDatas = new ArrayList<Recorder>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioweixin);

        mlistview = (ListView) findViewById(R.id.listview);

        button = (AudioRecordButton) findViewById(R.id.recordButton);
        button.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {

            @Override
            public void onFinished(float seconds, String filePath) {
                // TODO Auto-generated method stub
                Recorder recorder = new Recorder(seconds, filePath);
                mDatas.add(recorder);
                mAdapter.notifyDataSetChanged();
                mlistview.setSelection(mDatas.size() - 1);
            }
        });

        mAdapter = new RecorderAdapter(this, mDatas);
        mlistview.setAdapter(mAdapter);

        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                // 播放动画
                if (viewanim != null) {//让第二个播放的时候第一个停止播放
                    viewanim.setBackgroundResource(R.id.id_recorder_anim);
//                    viewanim.setBackgroundResource(R.drawable.adj);
                    viewanim = null;
                }
                viewanim = view.findViewById(R.id.id_recorder_anim);
                viewanim.setBackgroundResource(R.drawable.play);
                AnimationDrawable drawable = (AnimationDrawable) viewanim
                        .getBackground();
                drawable.start();

                // 播放音频
                MediaManager.playSound(mDatas.get(position).getFilePathString(),
                        new MediaPlayer.OnCompletionListener() {

                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                viewanim.setBackgroundResource(R.id.id_recorder_anim);
//                                viewanim.setBackgroundResource(R.drawable.v_anim3);
                            }
                        });
            }
        });
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        MediaManager.release();
    }
}
