package com.example.myapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.Book;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.dao.PhanLoai;
import com.example.myapplication.dao.PhanLoaiDAO;

import java.util.ArrayList;

import static com.example.myapplication.Fragment.Fragment_Sach.rv_sach;


public class SachAdapter extends RecyclerView.Adapter<SachAdapter.MyViewHolder> {
     private  ArrayList<Book> ds_book;
     BookDAO bookDAO;

     private Context  context;
     SachAdapter sachAdapter;


    public SachAdapter(ArrayList<Book> ds_book, Context context) {
        this.ds_book = ds_book;
        this.context = context;
        bookDAO =new BookDAO(context);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView t_tensach;
        public TextView t_tacgia;
        public TextView t_nxb;
        private ImageView img_xoaphanloai;
        private ImageView img_themphanloai;


        public MyViewHolder(View v) {
            super(v);
            t_tensach=v.findViewById(R.id.text_tensach);
            t_tacgia=v.findViewById(R.id.text_tacgia);
            t_nxb=v.findViewById(R.id.text_NXB);
            img_xoaphanloai=v.findViewById(R.id.img_xoaphanloai);
            img_themphanloai=v.findViewById(R.id.img_themphanloai);


        }
    }
    @Override
    public SachAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.t_tensach.setText(ds_book.get(position).getTensach());
        holder.t_tacgia.setText(ds_book.get(position).getTacgia());
        holder.t_nxb.setText(ds_book.get(position).getNxb());


        holder.img_xoaphanloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setCancelable(false);
                dialog.setTitle("Dialog on Android");
                dialog.setMessage("Bạn có chắc muốn xóa ?" + ds_book .get(position).getTensach() );
                dialog.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        bookDAO= new BookDAO(context);
                        if(bookDAO.delete(ds_book.get(position).getId_pl())) {
                            Toast.makeText(context, "Xóa thành công \n " + ds_book.get(position).getTensach(), Toast.LENGTH_SHORT).show();
                            capnhap();
                        }else{
                            Toast.makeText(context, "Xóa That bai!!!!! \n " + ds_book.get(position).getTensach(), Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                        .setNegativeButton("Hủy ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });
        holder.img_themphanloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("id",ds_book.get(position).getId_pl()+"");
                args.putString("tensach",ds_book.get(position).getTensach()+"");
                args.putString("tacgia",ds_book.get(position).getTacgia()+"");
                args.putString("nxb",ds_book.get(position).getNxb()+"");



            }
        });

    }
    @Override
    public int getItemCount() {

        return ds_book.size();
    }
    public void capnhap(){
        ds_book = new ArrayList<>();
        ds_book= bookDAO.getAll();
        sachAdapter = new SachAdapter(ds_book,context);
        rv_sach.setAdapter(sachAdapter);
    }

}