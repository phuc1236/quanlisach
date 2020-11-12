package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.myapplication.dao.NguoiDungDAO;


public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin;
    CheckBox chkRememberPass;
    String strUser, strPass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(LoginActivity.this,R.color.colorPrimary2));
        }


        edUserName = (EditText) findViewById(R.id.edUserName);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        chkRememberPass = (CheckBox) findViewById(R.id.chkRememberPass);
        nguoiDungDAO = new NguoiDungDAO(LoginActivity.this);
    }
    public void checkLogin(View v){
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        }else {
            if (nguoiDungDAO.checkLogin(strUser,strPass)>0){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                finish();
            }


            if (strUser.equalsIgnoreCase("admin")&&strPass.equalsIgnoreCase("123456")){
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status){
            //xoa tinh trang luu tru truoc do
            edit.clear();
        }else {
            //luu du lieu
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        //luu lai toan bo
        edit.commit();
    }
}