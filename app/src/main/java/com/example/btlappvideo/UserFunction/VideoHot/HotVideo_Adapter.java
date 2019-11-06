package com.example.btlappvideo.UserFunction.VideoHot;

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
import com.example.btlappvideo.Adapter.SQLHelperVideo;
import com.example.btlappvideo.Model.HotVideo;
import com.example.btlappvideo.R;

import java.util.ArrayList;
import java.util.List;

public class HotVideo_Adapter extends RecyclerView.Adapter<HotVideo_Adapter.Viewhoder> {
    Context context;
    List<HotVideo> hotVideoList;
    IOnClickVideo onClickVideo;
    SQLHelperVideo sqlHelperVideo;

    public void setOnClickVideo(IOnClickVideo onClickVideo) {
        this.onClickVideo = onClickVideo;
    }

    public interface IOnClickVideo{
        void onClickVideo(ArrayList<HotVideo> hotVideos, int position);
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
    public void onBindViewHolder(@NonNull final Viewhoder holder, final int position) {
        final HotVideo hotVideo = hotVideoList.get(position);
        Glide.with(context).load(hotVideo.getAvatar()).into(holder.img_hotvideo);
        holder.tvtitle.setText(hotVideo.getTitle());
        holder.tvdate_published.setText(hotVideo.getDate_published());
        holder.ItemVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickVideo.onClickVideo((ArrayList<HotVideo>) hotVideoList, position);
                sqlHelperVideo.insertVideoHistory(hotVideoList.get(position).getId(), hotVideoList.get(position).getTitle(), hotVideoList.get(position).getAvatar(), hotVideoList.get(position).getFile_mp4(), hotVideoList.get(position).getDate_published());
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
