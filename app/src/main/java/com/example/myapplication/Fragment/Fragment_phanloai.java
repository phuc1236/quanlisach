package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Dialog.Bottom_sheet;
import com.example.myapplication.R;
import com.example.myapplication.adapter.PhanLoaiAdapter;
import com.example.myapplication.dao.PhanLoai;
import com.example.myapplication.dao.PhanLoaiDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_phanloai extends Fragment {
    FloatingActionButton fl_phanloai;
    public static PhanLoaiAdapter phanLoaiAdapters;
    public  static  RecyclerView rv_phanloai;
    ArrayList<PhanLoai> ds_phanloai;
    PhanLoaiDAO phanLoaiDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phanloai,container,false);
        fl_phanloai= view.findViewById(R.id.fl_pl);


        rv_phanloai=view.findViewById(R.id.rv_pl);
        rv_phanloai.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_phanloai= new ArrayList<>();
        phanLoaiDAO= new PhanLoaiDAO(getContext());
        fl_phanloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_sheet bottom_sheet = new Bottom_sheet();
                bottom_sheet.show(getFragmentManager(),"tag");
            }
        });

        ds_phanloai= phanLoaiDAO.getAll();
        phanLoaiAdapters= new PhanLoaiAdapter(ds_phanloai,getContext());
        rv_phanloai.setAdapter(phanLoaiAdapters);

        return view;
    }

}
