package com.example.prm_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_project.DataClass;
import com.example.prm_project.MyAdapter;
import com.example.prm_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    //List<com.example.storedatapractice.DataClass> dataList = new ArrayList<>();
    //com.example.storedatarealtime.MyAdapter adapter;
    SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        //adapter = new com.example.storedatarealtime.MyAdapter(MainActivity.this, dataList);
        //recyclerView.setAdapter(adapter);

        Calendar FirebaseDatabase = null;
        //databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dataList.clear();
//                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
//                    com.example.storedatapractice.DataClass dataClass = itemSnapshot.getValue(com.example.storedatapractice.DataClass.class);
//
//                    dataClass.setKey(itemSnapshot.getKey());
//
//                    dataList.add(dataClass);
//                }
//                //adapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //searchList(newText);
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.storedatarealtime.UploadActivity.class);
                startActivity(intent);
            }
        });

    }
//    public void searchList(String text){
//        ArrayList<com.example.storedatapractice.DataClass> searchList = new ArrayList<>();
//        for (com.example.storedatapractice.DataClass dataClass: dataList){
//            if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
//                searchList.add(dataClass);
//            }
//        }
//        adapter.searchDataList(searchList);
    }
