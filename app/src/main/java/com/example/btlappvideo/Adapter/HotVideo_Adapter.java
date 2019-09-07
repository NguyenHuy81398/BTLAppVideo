package com.example.btlappvideo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlappvideo.Class.HotVideo;
import com.example.btlappvideo.R;

import java.util.List;

public class HotVideo_Adapter extends RecyclerView.Adapter<HotVideo_Adapter.Viewhoder> {
    Context context;
    List<HotVideo> hotVideoList;
    IOnClickVideo onClickVideo;

    public void setOnClickVideo(IOnClickVideo onClickVideo) {
        this.onClickVideo = onClickVideo;
    }

    public interface IOnClickVideo{
        void onClickVideo(String title, String file_mp4);
    }

    public HotVideo_Adapter(Context context, List<HotVideo> hotVideoList) {
        this.context = context;
        this.hotVideoList = hotVideoList;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_hotvideo, parent, false);
        Viewhoder viewhoder = new Viewhoder(view);
        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewhoder holder, int position) {
        final HotVideo hotVideo = hotVideoList.get(position);
        Glide.with(context).load(hotVideo.getAvatar()).into(holder.img_hotvideo);
        holder.tvtitle.setText(hotVideo.getTitle());
        holder.tvdate_published.setText(hotVideo.getDate_published());

        holder.ItemVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickVideo.onClickVideo(hotVideo.getTitle(), hotVideo.getFile_mp4());

            }
        });
    }

    @Override
    public int getItemCount() {
        return hotVideoList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView img_hotvideo;
        TextView tvtitle, tvdate_published;
        LinearLayout ItemVideo;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            img_hotvideo = itemView.findViewById(R.id.img_hotvideo);
            tvtitle = itemView.findViewById(R.id.tvtitle);
            tvdate_published = itemView.findViewById(R.id.tvdate_published);
            ItemVideo = itemView.findViewById(R.id.ItemVideo);
        }
    }
}
