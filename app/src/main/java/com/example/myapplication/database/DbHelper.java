package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context,"QLChiTieu",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE LOAI_TC(MaLoai integer primary key autoincrement, " +
                "TenLoai text ,TrangThai text )";
        db.execSQL(sql);

        sql = " INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES ('Chính trị – pháp luật','CT - PT')";
        db.execSQL(sql);
        sql = " INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES ('Khoa học công nghệ – Kinh tế ','CNTT')";
        db.execSQL(sql);

        sql = " INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES ('Văn hóa xã hội – Lịch sử ','LS')";
        db.execSQL(sql);

        sql = " INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES ('Truyện, tiểu thuyết ','TT')";
        db.execSQL(sql);
        sql = " INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES ('Tâm lý, tâm linh, tôn giáo ','TL - TG')";
        db.execSQL(sql);

        sql = "CREATE TABLE  Sach(MaSach integer primary key autoincrement ," +
                "TenSach text , Tacgia text ,Nxb text  )";
        db.execSQL(sql);
        sql = " INSERT INTO Sach(TenSach,Tacgia,Nxb) VALUES ('Tắt đèn','Ngô tất tố','1937 ')";
        db.execSQL(sql);
        sql = " INSERT INTO Sach(TenSach,Tacgia,Nxb) VALUES ('Tôi tài giỏi, bạn cũng thế',' Adam Khoo','1999 ')";
        db.execSQL(sql);
        sql = " INSERT INTO Sach(TenSach,Tacgia,Nxb) VALUES ('Đắc nhân tâm',' Dale Carnegie','2000 ')";
        db.execSQL(sql);
        sql = " INSERT INTO Sach(TenSach,Tacgia,Nxb) VALUES ('Cứ đi rồi sẽ đến',' Minh DeltaViet','1863 ')";
        db.execSQL(sql);
        sql = " INSERT INTO Sach(TenSach,Tacgia,Nxb) VALUES ('The Road - Cha và con',' Cormac McCarthy','1988 ')";
        db.execSQL(sql);
        sql = " INSERT INTO Sach(TenSach,Tacgia,Nxb) VALUES ('Việt Bắc',' Tố Hữu',' 1954 ')";
        db.execSQL(sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL("Drop table  if exists LOAI_TC");
        db.execSQL("Drop table  if exists Sach");

    }
}
