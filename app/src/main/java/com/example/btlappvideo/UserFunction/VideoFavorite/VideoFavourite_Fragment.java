package com.example.btlappvideo.UserFunction.VideoFavorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlappvideo.Adapter.SQLHelperFavourite;
import com.example.btlappvideo.Model.HotVideo;
import com.example.btlappvideo.R;

import java.util.List;

public class VideoFavourite_Fragment extends Fragment {
    private static final String TAG = "VideoFavourite_Fragment";
    VideoFavourite_Adapter videoFavourite_adapter;
    RecyclerView rvVideoFavourite;
    ProgressBar pbVideoFavourite;
    SQLHelperFavourite sqlHelperFavourite;
    TextView tvFavouriteNull;

    public static VideoFavourite_Fragment newInstance() {

        Bundle args = new Bundle();

        VideoFavourite_Fragment fragment = new VideoFavourite_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videofavourite_fragment, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvVideoFavourite = view.findViewById(R.id.rvVideoFavourite);
        pbVideoFavourite = view.findViewById(R.id.pbLoaddingFavourite);
        tvFavouriteNull = view.findViewById(R.id.tvFavouriteNull);

        tvFavouriteNull.setVisibility(View.GONE);

        List<HotVideo> list_video;
        sqlHelperFavourite = new SQLHelperFavourite(getContext());
        list_video = sqlHelperFavourite.getAllVideoFavouriteAdvanced();

        if(list_video.size() == 0){
            pbVideoFavourite.setVisibility(View.GONE);
            tvFavouriteNull.setVisibility(View.VISIBLE);
        }else {
            videoFavourite_adapter = new VideoFavourite_Adapter(getContext(), list_video);
            rvVideoFavourite.setAdapter(videoFavourite_adapter);
            rvVideoFavourite.setLayoutManager(layoutManager);
            pbVideoFavourite.setVisibility(View.GONE);
        }
        return view;
    }
}
