package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.myapplication.database.DbHelper;

import java.util.ArrayList;


public class PhanLoaiDAO {
    SQLiteDatabase db1;
    DbHelper db;
    public PhanLoaiDAO(Context context){
        db= new DbHelper(context);

    }

  public  void them(PhanLoai pl){
        db1= db.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put("TenLoai",pl.getTenloai());
      values.put("TrangThai",pl.getTrangthai());

      db1.insert("LOAI_TC",null,values);

  }
  public ArrayList<PhanLoai>getAll(){
        ArrayList<PhanLoai> ds_pl= new ArrayList<>();
        db1= db.getWritableDatabase();
      Cursor cursor = db1.query("LOAI_TC",null,null,null,null,null,null);
      if(cursor.moveToFirst()){
          do {
              int id = cursor.getInt(0);
              String tenloai =cursor.getString(1);
              String trangthai = cursor.getString(2);


              ds_pl.add( new PhanLoai( id,tenloai,trangthai));
          } while (cursor.moveToNext());
      }
      return ds_pl;
  }
  public  boolean delete(int id){
        db1= db.getWritableDatabase();
        int row = db1.delete("LOAI_TC","MaLoai=?",new String[]{id+""});
        return (row>0);
  }
  public void update(PhanLoai pl){
        db1= db.getWritableDatabase();
        ContentValues values = new ContentValues();
      values.put("TenLoai",pl.getTenloai());
      values.put("TrangThai",pl.getTrangthai());


      db1.update("LOAI_TC",values,"MaLoai=?",new String[]{pl.getId_pl()+""});


  }
}
