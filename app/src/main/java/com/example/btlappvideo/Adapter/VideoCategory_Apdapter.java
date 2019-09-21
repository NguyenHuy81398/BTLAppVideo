package com.example.btlappvideo.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlappvideo.Class.HotVideo;
import com.example.btlappvideo.R;

import java.util.List;



public class VideoCategory_Apdapter extends RecyclerView.Adapter<VideoCategory_Apdapter.Viewhoder> {

    public interface ISendVideo{
        void iOnClickListVideo(int positionlist);
    }

    Context context;
    List<HotVideo> itemCategoryList;
    ISendVideo iSendVideo;

    public void setiSendVideo(ISendVideo iSendVideo) {
        this.iSendVideo = iSendVideo;
    }

    public VideoCategory_Apdapter(Context context, List<HotVideo> itemCategoryList) {
        this.context = context;
        this.itemCategoryList = itemCategoryList;
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
    public void onBindViewHolder(@NonNull final Viewhoder holder, final int position) {
        final HotVideo itemCategory = itemCategoryList.get(position);

        holder.tvTitleVideoCategory.setText(itemCategory.getTitle());
        holder.tvDateVideoCategory.setText(itemCategory.getDate_created());
        Glide.with(context).load(itemCategory.getAvatar()).into(holder.imgVideoCategory);
        holder.rlVideoCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iSendVideo.iOnClickListVideo(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemCategoryList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvTitleVideoCategory, tvDateVideoCategory;
        ImageView imgVideoCategory;
        RelativeLayout rlVideoCategory;
        VideoView videoView;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgVideoCategory = itemView.findViewById(R.id.imgvideocategory);
            tvTitleVideoCategory = itemView.findViewById(R.id.tvtitlevideocategory);
            tvDateVideoCategory = itemView.findViewById(R.id.tvdatevideocategory);
            rlVideoCategory = itemView.findViewById(R.id.rlvideocategory);
            videoView = itemView.findViewById(R.id.vdVideo);
        }
    }
}
