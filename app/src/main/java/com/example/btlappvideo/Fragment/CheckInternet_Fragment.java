package com.example.btlappvideo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.btlappvideo.R;

public class CheckInternet_Fragment extends Fragment {

    public static CheckInternet_Fragment newInstance() {

        Bundle args = new Bundle();

        CheckInternet_Fragment fragment = new CheckInternet_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkinternet_fragment, container, false);
        return view;
    }
}
