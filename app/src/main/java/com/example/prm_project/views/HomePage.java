package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.prm_project.R;
import com.example.prm_project.views.Fragment.HomeFragment;
import com.example.prm_project.views.Fragment.SearchFragment;

public class HomePage extends AppCompatActivity {
    private ImageButton toUserBtn;
    private ImageButton toHomeBtn;
    private ImageButton toGameBtn;
    private ImageButton toSearchScreen;
    private SharedPreferences sp;
    private Fragment homeFragment;
    private Fragment searchFragment;
    private int isLogin = -1;

    private void bindingView(){
        toHomeBtn = findViewById(R.id.toHomeBtn);
        toGameBtn = findViewById(R.id.toGameBtn);
        toSearchScreen = findViewById(R.id.toSearchScreen);
        toUserBtn = findViewById(R.id.toUserbtn);
    }
    private void bindingAction(){
        toUserBtn.setOnClickListener(this::toUserScreen);
        toHomeBtn.setOnClickListener(this:: toHomeScreen);
        toGameBtn.setOnClickListener(this::toAllProduct);
        toSearchScreen.setOnClickListener(this::toSearchScreen);
    }

    private void toSearchScreen(View view) {
        if(searchFragment == null) {
            searchFragment = new SearchFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_container, searchFragment)
                .commit();
    }

    private void toHomeScreen(View view) {
        if(homeFragment == null){
            homeFragment = new HomeFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_container, homeFragment)
                .addToBackStack(null)
                .commit();
    }

    private void toAllProduct(View view) {
        Intent toNews = new Intent(this, CategoryPreference.class);
        startActivity(toNews);
    }

    private void toUserScreen(View view) {
        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putInt("USER_ID", -1);
//        editor.apply();
        isLogin = sp.getInt("USER_ID", -1);
//        Log.d("isLogin", isLogin+"");
        if(isLogin == -1){
            Intent toLogin = new Intent(this, LoginAndRegister.class);
            finish();
            startActivity(toLogin);
        }else {
            Intent toProfile = new Intent(this, UserProfileActivity.class);
            finish();
            startActivity(toProfile);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bindingView();
        bindingAction();
    }
}