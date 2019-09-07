package com.example.btlappvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.btlappvideo.Class.ItemCategory;
import com.example.btlappvideo.R;
import com.example.btlappvideo.Adapter.VideoCategory_Apdapter;

import java.util.ArrayList;

public class PlayVideoCategoryActivity extends AppCompatActivity {
    TextView tvtitlevideocategory;
    VideoView vdVideoCategory;
    VideoCategory_Apdapter videoCategory_apdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvtitlevideocategory = findViewById(R.id.tvtitlevideocategory);
        vdVideoCategory = findViewById(R.id.vdVideoCategory);
        RecyclerView rvListVideoCategory = findViewById(R.id.rvListVideoCategory);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String file_mp4 = intent.getStringExtra("file_mp4");
        ArrayList<ItemCategory> itemCategories = (ArrayList<ItemCategory>) intent.getSerializableExtra("list_video");

        setTitle(title);

        tvtitlevideocategory.setText(title);
        Uri uri = Uri.parse(file_mp4);
        vdVideoCategory.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        vdVideoCategory.setMediaController(mediaController);
        mediaController.setAnchorView(vdVideoCategory);
        vdVideoCategory.getDuration();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        videoCategory_apdapter = new VideoCategory_Apdapter(this, itemCategories);
        rvListVideoCategory.setAdapter(videoCategory_apdapter);
        rvListVideoCategory.setLayoutManager(layoutManager);

    }
}
