package com.example.btlappvideo.UserFunction.VideoFavorite;

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

public class VideoFavourite_Adapter extends RecyclerView.Adapter<VideoFavourite_Adapter.Viewhoder> {
    Context context;
    List<HotVideo> videoFavouriteList;

    public VideoFavourite_Adapter(Context context, List<HotVideo> videoFavouriteList) {
        this.context = context;
        this.videoFavouriteList = videoFavouriteList;
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
        final HotVideo itemVideoFavourite = videoFavouriteList.get(position);

        holder.tvTitleVideoCategory.setText(itemVideoFavourite.getTitle());
        holder.tvDateVideoCategory.setText(itemVideoFavourite.getDate_published());
        Glide.with(context).load(itemVideoFavourite.getAvatar()).into(holder.imgVideoCategory);
    }

    @Override
    public int getItemCount() {
        return videoFavouriteList.size();
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
