package com.example.myapplication.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.myapplication.R;
import com.example.myapplication.adapter.PhanLoaiAdapter;
import com.example.myapplication.dao.PhanLoai;
import com.example.myapplication.dao.PhanLoaiDAO;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.example.myapplication.Fragment.Fragment_phanloai.rv_phanloai;


public class Bottom_sheet  extends BottomSheetDialogFragment {
    EditText et_tenphanloai;
    Spinner sp_phanloai;
    Button btn_them;
    PhanLoaiDAO phanLoaiDAO;
    ArrayList<PhanLoai> ds_phanloai;
    private PhanLoaiAdapter phanLoaiAdapters;

    public  Bottom_sheet(){


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater .inflate(R.layout.bt_sheep_pl,container,false);
        et_tenphanloai=view.findViewById(R.id.edt_tenloai);
        sp_phanloai=view.findViewById(R.id.sp_phanloai);
        btn_them=view.findViewById(R.id.btn_thempl);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        sp_phanloai.setAdapter(adapter);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phanLoaiDAO = new PhanLoaiDAO(getContext());
                String ten_loai = et_tenphanloai.getText().toString();
                String phanloai = sp_phanloai.getSelectedItem().toString();



                PhanLoai pl = new PhanLoai(ten_loai,phanloai);
                phanLoaiDAO.them(pl);
                capnhap();
                Toast.makeText(getContext(),"THÊM THÀNH CÔNG",Toast.LENGTH_SHORT).show();
                dismiss();
    }

});

        return view;
    }
    public  void capnhap() {
        ds_phanloai= new ArrayList<>();
        ds_phanloai = phanLoaiDAO.getAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai, getContext());
        rv_phanloai.setAdapter(phanLoaiAdapters);
    }
}
