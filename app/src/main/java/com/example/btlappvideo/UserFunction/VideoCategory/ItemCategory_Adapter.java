package com.example.btlappvideo.UserFunction.VideoCategory;

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
import com.example.btlappvideo.Adapter.SQLHelperVideo;
import com.example.btlappvideo.Model.HotVideo;
import com.example.btlappvideo.R;

import java.util.ArrayList;
import java.util.List;

public class ItemCategory_Adapter extends RecyclerView.Adapter<ItemCategory_Adapter.Viewhoder> {

    Context context;
    IOnClickVideoCategory iOnClickVideoCategory;
    List<HotVideo> itemCategories;
    SQLHelperVideo sqlHelperVideo;


    public interface IOnClickVideoCategory{
        void onClickVideoCategory(ArrayList<HotVideo> itemCategories, int position);
    }

    public void setiOnClickVideoCategory(IOnClickVideoCategory iOnClickVideoCategory) {
        this.iOnClickVideoCategory = iOnClickVideoCategory;
    }

    public ItemCategory_Adapter(Context context, List<HotVideo> itemCategories) {
        this.context = context;
        this.itemCategories = itemCategories;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_itemcategory, parent, false);
        Viewhoder viewhoder = new Viewhoder(view);

        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewhoder holder, final int position) {
        final HotVideo itemCategory = itemCategories.get(position);

        holder.tvtitle_itemcategory.setText(itemCategory.getTitle());
        holder.tvdatecreated_itemcategory.setText(itemCategory.getDate_published());
        Glide.with(context).load(itemCategory.getAvatar()).into(holder.img_itemcategory);

        sqlHelperVideo = new SQLHelperVideo(context);

        holder.rlitemcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickVideoCategory.onClickVideoCategory((ArrayList<HotVideo>) itemCategories, position);
                sqlHelperVideo.insertVideoHistory(itemCategories.get(position).getId(), itemCategories.get(position).getTitle(), itemCategories.get(position).getAvatar(), itemCategories.get(position).getFile_mp4(), itemCategories.get(position).getDate_published());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCategories.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView img_itemcategory;
        TextView tvtitle_itemcategory, tvdatecreated_itemcategory;
        RelativeLayout rlitemcategory;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            img_itemcategory = itemView.findViewById(R.id.img_itemcategory);
            tvtitle_itemcategory = itemView.findViewById(R.id.tvtitle_itemcategory);
            tvdatecreated_itemcategory = itemView.findViewById(R.id.tvdatecreated_itemcategory);
            rlitemcategory = itemView.findViewById(R.id.rlItemCategory);

        }
    }

}
