package com.example.btlappvideo.UserFunction.VideoHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlappvideo.Model.HotVideo;
import com.example.btlappvideo.R;

import java.util.List;

public class VideoHistory_Adapter extends RecyclerView.Adapter<VideoHistory_Adapter.Viewhoder> {
    Context context;
    List<HotVideo> videoHistoryList;

    public VideoHistory_Adapter(Context context, List<HotVideo> videoHistoryList) {
        this.context = context;
        this.videoHistoryList = videoHistoryList;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_videocategory, parent, false);
        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final HotVideo itemVideoHistory = videoHistoryList.get(position);

        holder.tvTitleVideoCategory.setText(itemVideoHistory.getTitle());
        holder.tvDateVideoCategory.setText(itemVideoHistory.getDate_published());
        Glide.with(context).load(itemVideoHistory.getAvatar()).into(holder.imgVideoCategory);
    }

    @Override
    public int getItemCount() {
        return videoHistoryList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvTitleVideoCategory, tvDateVideoCategory;
        ImageView imgVideoCategory;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            imgVideoCategory = itemView.findViewById(R.id.imgvideocategory);
            tvTitleVideoCategory = itemView.findViewById(R.id.tvtitlevideocategory);
            tvDateVideoCategory = itemView.findViewById(R.id.tvdatevideocategory);

        }
    }
}
