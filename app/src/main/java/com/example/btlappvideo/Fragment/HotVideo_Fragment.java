package com.example.btlappvideo.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.btlappvideo.Adapter.BannerHotVideo_Adapter;
import com.example.btlappvideo.Class.HotVideo;
import com.example.btlappvideo.Adapter.HotVideo_Adapter;
import com.example.btlappvideo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HotVideo_Fragment extends Fragment {

    private static final String TAG = "HotVideo_Fragment";
    String url = "https://demo5639557.mockable.io/getVideoHot";
    List<HotVideo> hotVideos;
    HotVideo_Adapter hotVideo_adapter;
    RecyclerView recyclerView;
    Data data;
    BannerHotVideo_Adapter bannerHotVideo_adapter;
    Handler handler;
    Runnable runnable;
    ViewPager vpBanner;
    int i;
    ProgressBar progressBar;

    public interface Data{
        void senData(String title, String file_mp4);
    }

    public static HotVideo_Fragment newInstance() {
        
        Bundle args = new Bundle();
        
        HotVideo_Fragment fragment = new HotVideo_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hotvideo_fragment, container, false);
        recyclerView = view.findViewById(R.id.rvHotVideo);
        vpBanner = view.findViewById(R.id.vpBanner);
        progressBar = view.findViewById(R.id.pbLoadding);

        new DoGetHotVideo(url).execute();

        return view;
    }

    class DoGetHotVideo extends AsyncTask<Void, Void, Void> {

        String urlnew;
        String result = "";

        public DoGetHotVideo(String urlnew) {
            this.urlnew = urlnew;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL url = new URL(urlnew);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteCharacter;

                while ((byteCharacter = is.read()) != -1){
                    result += (char) byteCharacter;
                }
                Log.d(TAG, "doInBackground: "+result);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String json = result;

            progressBar.setVisibility(View.GONE);
            try {
                hotVideos = new ArrayList<>();
                JSONArray hotvideoJSonArray = new JSONArray(json);

                for (int i=0; i< hotvideoJSonArray.length(); i++){
                    JSONObject objectHotVideo = hotvideoJSonArray.getJSONObject(i);

                    String id = objectHotVideo.getString("id");
                    String provider_id = objectHotVideo.getString("provider_id");
                    String category_id = objectHotVideo.getString("category_id");
                    String title = objectHotVideo.getString("title");
                    String avatar = objectHotVideo.getString("avatar");
                    String file_mp4 = objectHotVideo.getString("file_mp4");
                    int file_mp4_size = objectHotVideo.getInt("file_mp4_size");
                    String date_created = objectHotVideo.getString("date_created");
                    String date_modified = objectHotVideo.getString("date_modified");
                    String date_published = objectHotVideo.getString("date_published");
                    String youtube_url = objectHotVideo.getString("youtube_url");

                    hotVideos.add(new HotVideo(id, provider_id, category_id, title, avatar, file_mp4, file_mp4_size, date_created, date_modified, date_published,youtube_url));
                }
                //hotVideos.add(new HotVideo("1", "2", "2", "2", "https://dzbbmecpa0hd2.cloudfront.net/video/avatar/2019/04/23/15/1556007321_abd6e57f5f84fff5.jpg", "1", 2, "", "", "",""));
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
                hotVideo_adapter = new HotVideo_Adapter(getActivity(), hotVideos);
                recyclerView.setAdapter(hotVideo_adapter);
                recyclerView.setLayoutManager(layoutManager);

                bannerHotVideo_adapter = new BannerHotVideo_Adapter(getActivity(), hotVideos);
                vpBanner.setAdapter(bannerHotVideo_adapter);

                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        i = vpBanner.getCurrentItem();
                        i++;
                        if(i >= vpBanner.getAdapter().getCount()){
                            i = 0;
                        }
                        vpBanner.setCurrentItem(i, true);
                        handler.postDelayed(runnable, 3500);
                    }
                };
                handler.postDelayed(runnable, 3500);

                hotVideo_adapter.setOnClickVideo(new HotVideo_Adapter.IOnClickVideo() {
                    @Override
                    public void onClickVideo(String title, String file_mp4) {
                        data.senData(title, file_mp4);
                        Toast.makeText(getContext(), title, Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Data){
            data = (Data) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        data = null;
    }

}
