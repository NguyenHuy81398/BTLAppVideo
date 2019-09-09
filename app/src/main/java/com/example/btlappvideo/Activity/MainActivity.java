package com.example.btlappvideo.Activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.btlappvideo.Fragment.Category_Fragment;
import com.example.btlappvideo.Fragment.HotVideo_Fragment;
import com.example.btlappvideo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , HotVideo_Fragment.Data, Category_Fragment.SendCategory {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView nav_menu = findViewById(R.id.nav_menu);
        ImageView img_checkinternet = findViewById(R.id.img_checkinternet);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if(isConnected() == false){
            img_checkinternet.setImageResource(R.drawable.ic_nowifi);
        }else{
            getFragment(HotVideo_Fragment.newInstance());
            setTitle("Hot Video");
        }
        nav_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mnHotVideo:
                        getFragment(HotVideo_Fragment.newInstance());
                        setTitle("Hot Video");
                        break;
                    case R.id.mnCategory:
                        getFragment(Category_Fragment.newInstance());
                        setTitle("Category");
                        break;
                }

                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getFragment(Fragment fragment) {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "getFragment: " + e.getMessage());
        }
    }

    @Override
    public void senData(String title, String file_mp4) {
        Intent intent = new Intent(getBaseContext(), PlayVideoActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("file_mp4", file_mp4);
        startActivity(intent);
    }

    @Override
    public void sendDataCategory(String id, String title) {
        Intent intent_itemcategory = new Intent(getBaseContext(), ItemCategoryActivity.class);
        intent_itemcategory.putExtra("id", id);
        intent_itemcategory.putExtra("title", title);
        startActivity(intent_itemcategory);
    }

    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
