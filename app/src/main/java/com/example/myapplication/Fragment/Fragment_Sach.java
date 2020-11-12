package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Dialog.Bottom_sheet;
import com.example.myapplication.R;

import com.example.myapplication.adapter.SachAdapter;
import com.example.myapplication.dao.Book;
import com.example.myapplication.dao.BookDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class Fragment_Sach extends Fragment {
    FloatingActionButton fl_sach;

    public static SachAdapter sachAdapter;
    public  static  RecyclerView rv_sach;
    ArrayList<Book> ds_book;
    BookDAO bookDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach,container,false);
        fl_sach= view.findViewById(R.id.fl_sach);

        rv_sach=view.findViewById(R.id.rv_pl);
        rv_sach.setLayoutManager(new LinearLayoutManager(getContext()));
        ds_book= new ArrayList<>();
        bookDAO= new BookDAO(getContext());


        ds_book= bookDAO.getAll();
        sachAdapter= new SachAdapter(ds_book,getContext());
        rv_sach.setAdapter(sachAdapter);


        return view;

    }

}
