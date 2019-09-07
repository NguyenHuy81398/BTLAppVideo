package com.example.btlappvideo.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.btlappvideo.Class.ItemCategory;
import com.example.btlappvideo.Adapter.ItemCategory_Adapter;
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
    List<ItemCategory> itemCategories;
    ItemCategory_Adapter itemCategory_adapter;
    ProgressBar pbLoadingItemCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_category);

        rvItemCategory = findViewById(R.id.rvItemCategory);
        pbLoadingItemCategory = findViewById(R.id.pbLoaddingItemCategory);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        intent.getStringExtra("id");
        intent.getStringExtra("title");

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
                    String provider_id = objectItemCategory.getString("provider_id");
                    String category_id = objectItemCategory.getString("category_id");
                    String title = objectItemCategory.getString("title");
                    String avatar = objectItemCategory.getString("avatar");
                    String file_mp4 = objectItemCategory.getString("file_mp4");
                    String date_created = objectItemCategory.getString("date_created");

                    itemCategories.add(new ItemCategory(id, provider_id, category_id, title, avatar, file_mp4, date_created));
                }
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseContext(), 2, RecyclerView.VERTICAL, false);
                itemCategory_adapter = new ItemCategory_Adapter(getBaseContext(), itemCategories);
                rvItemCategory.setAdapter(itemCategory_adapter);
                rvItemCategory.setLayoutManager(layoutManager);

                itemCategory_adapter.setiOnClickVideoCategory(new ItemCategory_Adapter.IOnClickVideoCategory() {
                    @Override
                    public void onClickVideoCategory(String id, String title, String file_mp4, ArrayList<ItemCategory> itemCategories) {
                        Intent intent = new Intent(getBaseContext(), PlayVideoCategoryActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("title", title);
                        intent.putExtra("file_mp4", file_mp4);
                        intent.putExtra("list_video", itemCategories);
                        startActivity(intent);
                    }

                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
