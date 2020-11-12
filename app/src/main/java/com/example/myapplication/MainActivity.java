package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.myapplication.Fragment.Fragment_Doimk;
import com.example.myapplication.Fragment.Fragment_Hoadon;
import com.example.myapplication.Fragment.Fragment_Sach;


import com.example.myapplication.Fragment.Fragment_Thongke;
import com.example.myapplication.Fragment.Fragment_phanloai;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    String strUserName, strPassword;

     private  DrawerLayout dr_ly;
    Toolbar tb;
    NavigationView nv;
    ActionBarDrawerToggle drawerToggle;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(itemSelectedListener);

        NavigationView navigationView = findViewById(R.id.nv_view);
        navigationView.setItemIconTintList(null);


        if (checkLoginShap()<0){
            Intent i = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
        }


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary2));

        navigationView.setItemIconTintList(null);

        dr_ly = findViewById(R.id.dr_ly);
        tb = findViewById(R.id.tg_bar);
        nv = findViewById(R.id.nv_view);

        dr_ly.addDrawerListener(drawerToggle);
        drawerToggle = setDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            nv.setCheckedItem(R.id.hoadon);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_phanloai()).commit();


        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragments = null;
                Class fragmentClass = null;
                switch (item.getItemId()) {
                    case R.id.sach:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_Sach()).commit();
                        break;

                    case R.id.hoadon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_Hoadon()).commit();
                        break;
                    case R.id.thongke:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_Thongke()).commit();
                        break;
                    case R.id.theloai:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_phanloai()).commit();
                        break;


                }
                try {
                    fragments = (Fragment) fragmentClass.newInstance();

                } catch (Exception ex) {
                    ex.printStackTrace();

                }
                item.setCheckable(true);
                setTitle(item.getTitle());
                dr_ly.closeDrawers();
                return true;
            }
        });
    }
    private  BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                           switch ( item.getItemId()){
                               case R.id.navigation_user:
                                   fragment = new Fragment_Doimk();
                                   break;
                               case R.id.navigation_home:
                                   fragment = new Fragment_Sach();
                                   break;


                           }
                           getSupportFragmentManager(). beginTransaction().replace(R.id.fr_ly,fragment).commit();
                           return true;

                }
            };


    public ActionBarDrawerToggle setDrawerToggle() {
        return new ActionBarDrawerToggle(MainActivity.this, dr_ly, tb, R.string.Open, R.string.Close);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return false;
        }
        Intent intent;
        switch(item.getItemId()) {

            case R.id.changePass:
                intent = new Intent(MainActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
                return(true);
            case R.id.logOut:
                SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //xoa tinh trang luu tru truoc do
                edit.clear();
                edit.commit();
                intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public int checkLoginShap(){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        boolean chk = pref.getBoolean("REMEMBER",false);
        if (chk){
            strUserName = pref.getString("USERNAME","");
            strPassword = pref.getString("PASSWORD","");
            return 1;
        }
        return -1;
    }
}