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
import com.example.btlappvideo.Model.Category;
import com.example.btlappvideo.R;

import java.util.List;


public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.Viewhoder> {
    Context context;
    List<Category> categories;
    IOnClickItemCategory iOnClickItemCategory;

    public void setiOnClickItemCategory(IOnClickItemCategory iOnClickItemCategory) {
        this.iOnClickItemCategory = iOnClickItemCategory;
    }

    public interface IOnClickItemCategory{
        void onClickItemCategory(String id, String title, String thumb);
    }

    public Category_Adapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_category, parent, false);
        Viewhoder viewhoder = new Viewhoder(view);

        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final Category category = categories.get(position);
        Glide.with(context).load(category.getThumb()).into(holder.img_category);
        holder.tvtitle_category.setText(category.getTitle());

        holder.itemCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickItemCategory.onClickItemCategory(category.getId(), category.getTitle(), category.getThumb());

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        ImageView img_category;
        TextView tvtitle_category;
        RelativeLayout itemCategory;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            img_category = itemView.findViewById(R.id.img_category);
            tvtitle_category = itemView.findViewById(R.id.tvtitle_category);
            itemCategory = itemView.findViewById(R.id.itemCategory);

        }
    }
}
