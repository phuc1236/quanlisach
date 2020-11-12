package com.example.myapplication.dao;



public class Book {
    private String id_pl;
    private  String tensach;
    private  String tacgia;
    private String  nxb;


    public Book(String ten_loai, String phanloai) {
    }

    public Book(String id_pl, String tensach, String tacgia, String nxb) {
        this.id_pl = id_pl;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.nxb = nxb;

    }

    public Book(String tensach, String tacgia, String nxb) {
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.nxb = nxb;

    }

    public String getId_pl() {
        return id_pl;
    }

    public void setId_pl(String id_pl) {
        this.id_pl = id_pl;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }


}
