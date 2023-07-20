package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prm_project.R;

public class HomePage extends AppCompatActivity {
    private Button toLoginScreen;
    private Button toHomeBtn;
    private Button toAllProduct;
    private Button toStorebtn;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;

    private void bindingView(){
        toLoginScreen = findViewById(R.id.toLoginScreen);
        toHomeBtn = findViewById(R.id.toHomeBtn);
        toAllProduct = findViewById(R.id.toNewsbtn);
        toStorebtn = findViewById(R.id.toStorebtn);
    }
    private void bindingAction(){
        toLoginScreen.setOnClickListener(this::toLoginScreen);
        toHomeBtn.setOnClickListener(this::toLoginScreen);
        toAllProduct.setOnClickListener(this::toAllProduct);
        toStorebtn.setOnClickListener(this::toStorebtn);
    }

    private void toStorebtn(View view) {
        Intent toStore = new Intent(this, StoreProfileActivity.class);
        startActivity(toStore);
    }

    private void toAllProduct(View view) {

        Intent toNews = new Intent(this, CatagorySelection.class);
        startActivity(toNews);
    }

    private void toLoginScreen(View view) {
        sp = getSharedPreferences("USER", Context.MODE_PRIVATE);
        int haveid = sp.getInt("USER_ID", -1);
        if(haveid == -1){
            Intent toLogin = new Intent(this, LoginAndRegister.class);
            startActivity(toLogin);
        }else {
            Intent toProfile = new Intent(this, UserProfileActivity.class);
            startActivity(toProfile);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bindingView();
        bindingAction();
    }
}