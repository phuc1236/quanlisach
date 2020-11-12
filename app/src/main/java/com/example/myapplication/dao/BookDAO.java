package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.database.DbHelper;

import java.util.ArrayList;


public class BookDAO {
    SQLiteDatabase db1;
    DbHelper db;
    public BookDAO(Context context){
        db= new DbHelper(context);

    }

  public  void them(Book pl){
        db1= db.getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put("TenSach",pl.getTensach());
      values.put("Tacgia",pl.getTacgia());
      values.put("Nxb",pl.getNxb());
      db1.insert("Sach",null,values);

  }
  public ArrayList<Book>getAll(){
        ArrayList<Book> ds_book= new ArrayList<>();
        db1= db.getWritableDatabase();
      Cursor cursor = db1.query("Sach",null,null,null,null,null,null);
      if(cursor.moveToFirst()){
          do {
              String id = cursor.getString(0);
              String tensach =cursor.getString(1);
              String tacgia = cursor.getString(2);
              String nxb = cursor.getString(3);
              ds_book.add( new Book( id,tensach,tacgia,nxb));
          } while (cursor.moveToNext());
      }
      return ds_book;
  }
  public  boolean delete(String id){
        db1= db.getWritableDatabase();
        int row = db1.delete("Sach","MaSach=?",new String[]{id+""});
        return (row>0);
  }
  public void update(Book pl){
        db1= db.getWritableDatabase();
        ContentValues values = new ContentValues();
      values.put("TenSach",pl.getTensach());
      values.put("Tacgia",pl.getTacgia());
      values.put("Nxb",pl.getNxb());
      db1.update("Sach",values,"MaSach=?",new String[]{pl.getId_pl()+""});


  }
}
