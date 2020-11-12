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
import com.example.myapplication.dao.PhanLoai;
import com.example.myapplication.dao.PhanLoaiDAO;

import java.util.ArrayList;

import static com.example.myapplication.Fragment.Fragment_phanloai.rv_phanloai;


public class PhanLoaiAdapter extends RecyclerView.Adapter<PhanLoaiAdapter.MyViewHolder> {
     private  ArrayList<PhanLoai> ds_phanloai;
     PhanLoaiDAO phanLoaiDAO;
     
     private Context  context;
     PhanLoaiAdapter phanLoaiAdapters;


    public PhanLoaiAdapter(ArrayList<PhanLoai> ds_phanloai, Context context) {
        this.ds_phanloai = ds_phanloai;
        this.context = context;
        phanLoaiDAO =new PhanLoaiDAO(context);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_ten;
        private ImageView img_xoaphanloai;
        private ImageView img_themphanloai;

        public TextView tv_trangthai;
        public MyViewHolder(View v) {
            super(v);
            tv_ten=v.findViewById(R.id.tv_tenloai);
            tv_trangthai=v.findViewById(R.id.tv_trangthai);
            img_xoaphanloai=v.findViewById(R.id.img_xoaphanloai);
            img_themphanloai=v.findViewById(R.id.img_themphanloai);


        }
    }
    @Override
    public PhanLoaiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phanloai, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_ten.setText(ds_phanloai.get(position).getTenloai());
        holder.tv_trangthai.setText(ds_phanloai.get(position).getTrangthai());




        holder.img_xoaphanloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setCancelable(false);
                dialog.setTitle("Dialog on Android");
                dialog.setMessage("Bạn có chắc muốn xóa ?" + ds_phanloai .get(position).getTenloai() );
                dialog.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        phanLoaiDAO= new PhanLoaiDAO(context);
                        if(phanLoaiDAO.delete(ds_phanloai.get(position).getId_pl())) {
                            Toast.makeText(context, "Xóa thành công \n " + ds_phanloai.get(position).getTenloai(), Toast.LENGTH_SHORT).show();
                            capnhap();
                        }else{
                            Toast.makeText(context, "Xóa That bai!!!!! \n " + ds_phanloai.get(position).getTenloai(), Toast.LENGTH_SHORT).show();
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
                args.putString("id",ds_phanloai.get(position).getId_pl()+"");
                args.putString("tenloai",ds_phanloai.get(position).getTenloai()+"");
                args.putString("trangthai",ds_phanloai.get(position).getTrangthai()+"");





            }
        });

    }
    @Override
    public int getItemCount() {

        return ds_phanloai.size();
    }
    public void capnhap(){
        ds_phanloai = new ArrayList<>();
        ds_phanloai= phanLoaiDAO.getAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai,context);
        rv_phanloai.setAdapter(phanLoaiAdapters);
    }

}