package com.example.btlappvideo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.btlappvideo.Class.ItemCategory;
import com.example.btlappvideo.R;

import java.util.List;

public class VideoCategory_Apdapter extends RecyclerView.Adapter<VideoCategory_Apdapter.Viewhoder> {
    Context context;
    List<ItemCategory> itemCategoryList;

    public VideoCategory_Apdapter(Context context, List<ItemCategory> itemCategoryList) {
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
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final ItemCategory itemCategory = itemCategoryList.get(position);

        holder.tvTitleVideoCategory.setText(itemCategory.getTitle());
        holder.tvDateVideoCategory.setText(itemCategory.getDate_created());
        Glide.with(context).load(itemCategory.getAvatar()).into(holder.imgVideoCategory);
    }

    @Override
    public int getItemCount() {
        return itemCategoryList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvTitleVideoCategory, tvDateVideoCategory;
        ImageView imgVideoCategory;
        RelativeLayout rlVideoCategory;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            imgVideoCategory = itemView.findViewById(R.id.imgvideocategory);
            tvTitleVideoCategory = itemView.findViewById(R.id.tvtitlevideocategory);
            tvDateVideoCategory = itemView.findViewById(R.id.tvdatevideocategory);
            rlVideoCategory = itemView.findViewById(R.id.rlvideocategory);

        }
    }
}
