package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.BookAdapter;
import com.example.myapplication.model.Sach;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Fragment_Thongke extends Fragment {
    public  static RecyclerView rcv;
    BookAdapter bookAdapter;
    ArrayList<Sach> list;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke,container,false);

        return view;
    }
}
