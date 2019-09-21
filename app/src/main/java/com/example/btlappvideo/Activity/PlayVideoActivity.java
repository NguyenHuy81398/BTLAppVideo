package com.example.btlappvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.btlappvideo.Adapter.VideoCategory_Apdapter;
import com.example.btlappvideo.Class.HotVideo;
import com.example.btlappvideo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayVideoActivity extends AppCompatActivity {

    VideoView vdVideo;
    TextView tvTimeVideo, tvTimeMaxVideo;
    ImageView btnPlayVideo;
    SeekBar sbVideo;
    boolean control = true;
    LinearLayout controlvideo;
    VideoCategory_Apdapter videoCategory_apdapter;
    RecyclerView rvListVideo;
    ImageView btnFullScreen;
    boolean fullscreen = true;
    RelativeLayout rlViewVideo;
    TextView tvTitleVideo, tvDateVideo;
    ImageView imgVideo, btnPreviousVideo, btnNextVideo;
    ProgressBar pbLoadVideo;
    int position;
    ArrayList<HotVideo> list_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#B40404'>" + "Video" + "</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vdVideo = findViewById(R.id.vdVideo);
        tvTimeVideo = findViewById(R.id.tvtimevideo);
        tvTimeMaxVideo = findViewById(R.id.tvtimemaxvideo);
        btnPlayVideo = findViewById(R.id.btnplayvideo);
        sbVideo = findViewById(R.id.sbVideo);
        controlvideo = findViewById(R.id.controlvideo);
        rvListVideo = findViewById(R.id.rvListVideo);
        btnFullScreen = findViewById(R.id.btnFullScreen);
        rlViewVideo = findViewById(R.id.rlViewVideo);
        tvTitleVideo = findViewById(R.id.tvTitleVideo);
        tvDateVideo = findViewById(R.id.tvDateVideo);
        imgVideo = findViewById(R.id.imgVideo);
        btnPreviousVideo = findViewById(R.id.btnPreviousVideo);
        btnNextVideo = findViewById(R.id.btnNextVideo);

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
            }
        });

        btnFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullscreen == true){
                    FullScreen();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    btnFullScreen.setImageResource(R.drawable.ic_fullscreen_exit);
                    fullscreen = false;
                }else{
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    FullScreenExit();
                    btnFullScreen.setImageResource(R.drawable.ic_fullscreen);
                    fullscreen = true;
                }
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
        list_video = (ArrayList<HotVideo>) intent.getSerializableExtra("list_video");
        position = intent.getIntExtra("position", 1);

        if(position == 0){
            btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous_min);
            btnNextVideo.setImageResource(R.drawable.ic_skip_next);
        }
        if(position == list_video.size()-1){
            btnNextVideo.setImageResource(R.drawable.ic_skip_next_max);
            btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous);
        }

        tvTitleVideo.setText(list_video.get(position).getTitle());
        tvDateVideo.setText(list_video.get(position).getDate_published());
        Glide.with(this).load(list_video.get(position).getAvatar()).into(imgVideo);

        Uri uri = Uri.parse(list_video.get(position).getFile_mp4());
        vdVideo.setVideoURI(uri);

        vdVideo.start();

        Handler handlerTimeMax = new Handler();
        handlerTimeMax.postDelayed(new Runnable() {
            @Override
            public void run() {
                SetTimeVideo();
                UpdateTimeVideo();
            }
        }, 2500);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        videoCategory_apdapter = new VideoCategory_Apdapter(getBaseContext(), list_video);
        rvListVideo.setAdapter(videoCategory_apdapter);
        rvListVideo.setLayoutManager(layoutManager);
        videoCategory_apdapter.setiSendVideo(new VideoCategory_Apdapter.ISendVideo() {
            @Override
            public void iOnClickListVideo(int positionlist) {
                position = positionlist;

                if(position == 0){
                    btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous_min);
                    btnNextVideo.setImageResource(R.drawable.ic_skip_next);
                }
                if(position == list_video.size()-1){
                    btnNextVideo.setImageResource(R.drawable.ic_skip_next_max);
                    btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous);
                }

                tvTitleVideo.setText(list_video.get(position).getTitle());
                tvDateVideo.setText(list_video.get(position).getDate_published());
                Glide.with(getBaseContext()).load(list_video.get(position).getAvatar()).into(imgVideo);

                Uri uri = Uri.parse(list_video.get(position).getFile_mp4());
                vdVideo.setVideoURI(uri);

                vdVideo.start();

                Handler handlerTimeMax = new Handler();
                handlerTimeMax.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SetTimeVideo();
                        UpdateTimeVideo();
                    }
                }, 2500);
            }
        });

        btnNextVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if(vdVideo.isPlaying()){
                    vdVideo.stopPlayback();
                }
                if (position < list_video.size()-1){

                    tvTitleVideo.setText(list_video.get(position).getTitle());
                    tvDateVideo.setText(list_video.get(position).getDate_published());
                    Glide.with(getBaseContext()).load(list_video.get(position).getAvatar()).into(imgVideo);
                    Uri uri = Uri.parse(list_video.get(position).getFile_mp4());
                    vdVideo.setVideoURI(uri);

                    vdVideo.start();

                    Handler handlerTimeMax = new Handler();
                    handlerTimeMax.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SetTimeVideo();
                            UpdateTimeVideo();
                        }
                    }, 2500);


                    btnNextVideo.setImageResource(R.drawable.ic_skip_next);
                }
                if (position == list_video.size()-1){
                    btnNextVideo.setImageResource(R.drawable.ic_skip_next_max);
                }
                if(position > 0){
                    btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous);
                }
            }
        });

        btnPreviousVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(vdVideo.isPlaying()){
                    vdVideo.stopPlayback();
                }
                if (position > -1){

                    tvTitleVideo.setText(list_video.get(position).getTitle());
                    tvDateVideo.setText(list_video.get(position).getDate_published());
                    Glide.with(getBaseContext()).load(list_video.get(position).getAvatar()).into(imgVideo);
                    Uri uri = Uri.parse(list_video.get(position).getFile_mp4());
                    vdVideo.setVideoURI(uri);

                    vdVideo.start();

                    Handler handlerTimeMax = new Handler();
                    handlerTimeMax.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SetTimeVideo();
                            UpdateTimeVideo();
                        }
                    }, 2500);
                }
                if (position == 0){
                    btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous_min);
                }
                if(position < list_video.size()){
                    btnNextVideo.setImageResource(R.drawable.ic_skip_next);
                }
            }
        });
    }

    private void SetTimeVideo(){
        final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
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
        btnPreviousVideo.setVisibility(View.GONE);
        btnNextVideo.setVisibility(View.GONE);
    }

    private void HienControl(){
        btnNextVideo.setVisibility(View.VISIBLE);
        btnPreviousVideo.setVisibility(View.VISIBLE);
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
                vdVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        position++;

                        if (position > list_video.size()-1){
                            position = 0;
                        }

                        if(position == 0){
                            btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous_min);
                            btnNextVideo.setImageResource(R.drawable.ic_skip_next);
                        }

                        if(vdVideo.isPlaying()){
                            vdVideo.stopPlayback();
                        }
                        if (position < list_video.size()-1){

                            tvTitleVideo.setText(list_video.get(position).getTitle());
                            tvDateVideo.setText(list_video.get(position).getDate_published());
                            Glide.with(getBaseContext()).load(list_video.get(position).getAvatar()).into(imgVideo);
                            Uri uri = Uri.parse(list_video.get(position).getFile_mp4());
                            vdVideo.setVideoURI(uri);

                            vdVideo.start();

                            Handler handlerTimeMax = new Handler();
                            handlerTimeMax.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    SetTimeVideo();
                                    UpdateTimeVideo();
                                }
                            }, 2500);

                            btnNextVideo.setImageResource(R.drawable.ic_skip_next);
                        }
                        if (position == list_video.size()-1){
                            btnNextVideo.setImageResource(R.drawable.ic_skip_next_max);
                            btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous);
                        }
                        if(position > 0){
                            btnPreviousVideo.setImageResource(R.drawable.ic_skip_previous);
                        }
                    }
                });

                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void FullScreen(){
        getSupportActionBar().hide();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlViewVideo.getLayoutParams();
        params.width = params.MATCH_PARENT;
        params.height = params.MATCH_PARENT;
        rlViewVideo.setLayoutParams(params);
    }

    private void FullScreenExit(){
        getSupportActionBar().show();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlViewVideo.getLayoutParams();
        params.width = params.MATCH_PARENT;
        params.height = 435;
        rlViewVideo.setLayoutParams(params);
    }
}
