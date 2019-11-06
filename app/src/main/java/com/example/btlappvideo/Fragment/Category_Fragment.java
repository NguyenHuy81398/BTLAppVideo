package com.example.btlappvideo.Fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btlappvideo.UserFunction.VideoCategory.Category_Adapter;
import com.example.btlappvideo.Model.Category;
import com.example.btlappvideo.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Category_Fragment extends Fragment {
    Category_Adapter category_adapter;
    List<Category> categories;
    ProgressBar progressBarCategory;
    String url = "https://demo5639557.mockable.io/getCategory";
    private static final String TAG = "Category_Fragment";
    RecyclerView rvCategory;
    SendCategory sendCategory;

    public interface SendCategory{
        void sendDataCategory(String id, String title, String thumb);
    }

    public static Category_Fragment newInstance() {

        Bundle args = new Bundle();

        Category_Fragment fragment = new Category_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        rvCategory = view.findViewById(R.id.container_category);
        progressBarCategory = view.findViewById(R.id.pbLoaddingCatrgory);

        new DoGetCategory(url).execute();
        return view;
    }

    class DoGetCategory extends AsyncTask<Void, Void, Void> {

        String urlnew;
        String result = "";

        public DoGetCategory(String urlnew) {
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

            progressBarCategory.setVisibility(View.GONE);
            try {
                categories = new ArrayList<>();
                JSONArray categoryJSonArray = new JSONArray(json);

                for (int i=0; i< categoryJSonArray.length(); i++){
                    JSONObject objectCategory = categoryJSonArray.getJSONObject(i);

                    String id = objectCategory.getString("id");
                    String title = objectCategory.getString("title");
                    String thumb = objectCategory.getString("thumb");

                    categories.add(new Category(id, title, thumb));
                }
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                category_adapter = new Category_Adapter(getActivity(), categories);
                rvCategory.setAdapter(category_adapter);
                rvCategory.setLayoutManager(layoutManager);

                category_adapter.setiOnClickItemCategory(new Category_Adapter.IOnClickItemCategory() {
                    @Override
                    public void onClickItemCategory(String id, String title, String thumb) {
                        sendCategory.sendDataCategory(id, title, thumb);
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
        if(context instanceof SendCategory){
            sendCategory = (SendCategory) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendCategory = null;
    }
}


