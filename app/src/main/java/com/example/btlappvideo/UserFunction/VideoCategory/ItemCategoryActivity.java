package com.example.btlappvideo.UserFunction.VideoCategory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.btlappvideo.Adapter.SQLHelperVideo;
import com.example.btlappvideo.UserFunction.VideoPlayer.PlayVideoActivity;
import com.example.btlappvideo.Model.HotVideo;
import com.example.btlappvideo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryActivity extends AppCompatActivity {
    String url_itemcategory = "https://demo5639557.mockable.io/getItemCategory";
    RecyclerView rvItemCategory;
    private static final String TAG = "ItemCategoryActivity";
    List<HotVideo> itemCategories;
    ItemCategory_Adapter itemCategory_adapter;
    ProgressBar pbLoadingItemCategory;
    ImageView imgThumb;
    TextView tvTitleItemCategory;
    SQLHelperVideo sqlHelperVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);

        rvItemCategory = findViewById(R.id.rvItemCategory);
        pbLoadingItemCategory = findViewById(R.id.pbLoaddingItemCategory);
        imgThumb = findViewById(R.id.imgThumb);
        tvTitleItemCategory = findViewById(R.id.tvtitle_itemcategory);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#B40404'>" + title + "</font>"));
        Glide.with(this).load(intent.getStringExtra("thumb")).into(imgThumb);
        tvTitleItemCategory.setText(title);

        new DoGetItemCategory(url_itemcategory).execute();

    }

    class DoGetItemCategory extends AsyncTask<Void, Void, Void> {

        String urlnew;
        String result = "";

        public DoGetItemCategory(String urlnew) {
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

            pbLoadingItemCategory.setVisibility(View.GONE);
            try {
                itemCategories = new ArrayList<>();
                JSONArray itemCategoryJSonArray = new JSONArray(json);

                for (int i=0; i< itemCategoryJSonArray.length(); i++){
                    JSONObject objectItemCategory = itemCategoryJSonArray.getJSONObject(i);

                    String id = objectItemCategory.getString("id");

                    String title = objectItemCategory.getString("title");
                    String avatar = objectItemCategory.getString("avatar");
                    String file_mp4 = objectItemCategory.getString("file_mp4");

                    String date_published = objectItemCategory.getString("date_published");


                    itemCategories.add(new HotVideo(id, title, avatar, file_mp4, date_published));
                }
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 2, RecyclerView.VERTICAL, false);
                itemCategory_adapter = new ItemCategory_Adapter(getBaseContext(), itemCategories);
                rvItemCategory.setAdapter(itemCategory_adapter);
                rvItemCategory.setLayoutManager(layoutManager);

                sqlHelperVideo = new SQLHelperVideo(getBaseContext());
                itemCategory_adapter.setiOnClickVideoCategory(new ItemCategory_Adapter.IOnClickVideoCategory() {
                    @Override
                    public void onClickVideoCategory(ArrayList<HotVideo> itemCategories, int position) {
                        sqlHelperVideo.insertVideoHistory(itemCategories.get(position).getId(), itemCategories.get(position).getTitle(), itemCategories.get(position).getAvatar(), itemCategories.get(position).file_mp4, itemCategories.get(position).getDate_published());
                        Intent intent = new Intent(getBaseContext(), PlayVideoActivity.class);
                        intent.putExtra("list_video", itemCategories);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
