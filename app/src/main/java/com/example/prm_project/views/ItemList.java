package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.prm_project.R;

public class ItemList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        SharedPreferences sp = getSharedPreferences("category_id", Context.MODE_PRIVATE);
        Log.d("position", String.valueOf(sp.getInt("category_id", -1)));
    }

}