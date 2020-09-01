package com.bytedance.videoplayer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class videoPlayer extends AppCompatActivity {
    private Button pauseButton;
    private Button playButton;
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_video_play);
        setTitle("VideoPlayer");

        pauseButton = findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
            }
        });

        playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });

        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(getVideoPath(R.raw.bytedance));

        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        mediaController.show();
    }

    private boolean isPortrait;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isPortrait = newConfig.orientation == Configuration.ORIENTATION_PORTRAIT;
        fullScreen(!isPortrait);
    }

    public void fullScreen(boolean isFull){
        ActionBar actionBar = ((AppCompatActivity)videoPlayer.this).getSupportActionBar();
        if(actionBar != null){
            if(isFull){
                actionBar.hide();
                pauseButton.setVisibility(View.GONE);
                playButton.setVisibility(View.GONE);
            }else{
                actionBar.show();
                pauseButton.setVisibility(View.VISIBLE);
                playButton.setVisibility(View.VISIBLE);
            }
        }
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        if(isFull){
            layoutParams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(layoutParams);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }else {
            layoutParams.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(layoutParams);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }
}
