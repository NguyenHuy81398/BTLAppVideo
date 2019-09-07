package com.example.btlappvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.btlappvideo.R;

import java.text.SimpleDateFormat;

public class PlayVideoActivity extends AppCompatActivity {

    VideoView vdVideo;
    TextView tvTimeVideo, tvTimeMaxVideo;
    ImageView btnPlayVideo;
    SeekBar sbVideo;
    boolean control = true;
    RelativeLayout controlvideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        vdVideo = findViewById(R.id.vdVideo);
        tvTimeVideo = findViewById(R.id.tvtimevideo);
        tvTimeMaxVideo = findViewById(R.id.tvtimemaxvideo);
        btnPlayVideo = findViewById(R.id.btnplayvideo);
        sbVideo = findViewById(R.id.sbVideo);
        controlvideo = findViewById(R.id.controlvideo);

        TruyenVideo();

        btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vdVideo.isPlaying()){
                    btnPlayVideo.setImageResource(R.drawable.ic_play);
                    vdVideo.pause();
                }else {
                    btnPlayVideo.setImageResource(R.drawable.ic_pause);
                    vdVideo.start();
                }
                SetTimeVideo();
                UpdateTimeVideo();
            }
        });

        sbVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vdVideo.seekTo(sbVideo.getProgress());
            }
        });

        vdVideo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(control == true){
                    AnControl();
                    control = false;
                }else {
                    HienControl();
                    Handler handlerControl = new Handler();
                    handlerControl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AnControl();
                        }
                    }, 3500);
                    control = true;
                }
                return false;
            }
        });
    }

    private void TruyenVideo(){
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String file_mp4 = intent.getStringExtra("file_mp4");

        getSupportActionBar().hide();
        setTitle(title);
        Uri uri = Uri.parse(file_mp4);
        vdVideo.setVideoURI(uri);
    }

    private void SetTimeVideo(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
        tvTimeMaxVideo.setText(timeFormat.format(vdVideo.getDuration()));
        sbVideo.setMax(vdVideo.getDuration());

        Handler handlerControl = new Handler();
        handlerControl.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnControl();
            }
        }, 3500);
    }

    private void AnControl(){
        btnPlayVideo.setVisibility(View.GONE);
        controlvideo.setVisibility(View.GONE);
    }

    private void HienControl(){
        btnPlayVideo.setVisibility(View.VISIBLE);
        controlvideo.setVisibility(View.VISIBLE);
    }

    private void UpdateTimeVideo(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                tvTimeVideo.setText(timeFormat.format(vdVideo.getCurrentPosition()));
                sbVideo.setProgress(vdVideo.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        }, 100);
    }
}
