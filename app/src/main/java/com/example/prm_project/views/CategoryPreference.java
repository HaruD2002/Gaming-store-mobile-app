package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.CategoryDAO;
import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.viewmodel.ItemViewModel;
import com.example.prm_project.views.Adapter.CategoryPefAdapter;
import java.util.ArrayList;

public class CategoryPreference extends AppCompatActivity {

    private RecyclerView categoryList;
    private CategoryDAO categoryDAO;
    private ItemViewModel categoryViewModel;

    private ArrayList<Category> categoryListItem;

    private void getVM() {
//        categoryDAO= DAO.getInstance(getApplicationContext()).categoryDAO();
        categoryViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
    }
//    private void fetchData() {
//        categoryViewModel.getAllCategory().observe(this, categories -> {
//            for(Category c : categories){
//                Category item = new Category();
//                item.setCategoryName(c.getCategoryName());
//                item.setCategoryName(c.getDescription());
//                categoryListItem.add(item);
//            }
//        });
//    }

    private void bindingView(){
        categoryList = findViewById(R.id.category_list_view);

    }

    private void bindingReferenceData(){
        categoryList.setLayoutManager(new GridLayoutManager(this, 2));
        categoryList.setAdapter(new CategoryPefAdapter(categoryListItem, this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_preference);
        getVM();
//        fetchData();
        bindingView();
        bindingReferenceData();
    }
}