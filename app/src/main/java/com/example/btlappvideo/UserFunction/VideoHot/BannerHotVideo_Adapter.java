package com.example.btlappvideo.UserFunction.VideoHot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.btlappvideo.Model.HotVideo;
import com.example.btlappvideo.R;

import java.util.List;

public class BannerHotVideo_Adapter extends PagerAdapter {
    Context context;
    List<HotVideo> banners;

    public BannerHotVideo_Adapter(Context context, List<HotVideo> banners) {
        this.context = context;
        this.banners = banners;
    }

    @Override
    public int getCount() {
        return banners.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {


        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_banner, null);

        ImageView imgBanner = view.findViewById(R.id.imgBanner);
        TextView tvtitlebanner = view.findViewById(R.id.tvtitlebanner);

        Glide.with(context).load(banners.get(position).getAvatar()).into(imgBanner);
        tvtitlebanner.setText(banners.get(position).getTitle());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
