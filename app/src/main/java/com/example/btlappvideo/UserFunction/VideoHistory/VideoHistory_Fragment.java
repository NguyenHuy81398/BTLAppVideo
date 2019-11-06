package com.example.btlappvideo.UserFunction.VideoHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlappvideo.Adapter.SQLHelperVideo;
import com.example.btlappvideo.Model.HotVideo;
import com.example.btlappvideo.R;

import java.util.ArrayList;
import java.util.List;

public class VideoHistory_Fragment extends Fragment {
    private static final String TAG = "VideoHistory_Fragment";
    VideoHistory_Adapter history_adapter;
    RecyclerView rvVideoHistory;
    SQLHelperVideo sqlHelperVideo;
    ProgressBar pbVideoHistory;

    public static VideoHistory_Fragment newInstance() {

        Bundle args = new Bundle();

        VideoHistory_Fragment fragment = new VideoHistory_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videohistory_fragment, container, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rvVideoHistory = view.findViewById(R.id.rvVideoHistory);
        pbVideoHistory = view.findViewById(R.id.pbLoaddingHistory);

        List<HotVideo> list_video = new ArrayList<>();
        sqlHelperVideo = new SQLHelperVideo(getContext());
        list_video = sqlHelperVideo.getAllProductAdvanced();

        history_adapter = new VideoHistory_Adapter(getContext(), list_video);
        rvVideoHistory.setAdapter(history_adapter);
        rvVideoHistory.setLayoutManager(layoutManager);

        pbVideoHistory.setVisibility(View.GONE);
        return view;
    }
}
