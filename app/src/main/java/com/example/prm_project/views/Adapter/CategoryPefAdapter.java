package com.example.prm_project.views.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_project.R;
import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.views.ViewHolder.CategoryVH;

import java.util.ArrayList;

public class CategoryPefAdapter extends RecyclerView.Adapter<CategoryVH> {

    private ArrayList<Category> categoryListItem;
    private Context context;
    private LayoutInflater inflater;
    public CategoryPefAdapter(ArrayList<Category> categoryListItem, Context context) {
        this.categoryListItem = categoryListItem;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.category_item, parent, false);
        return new CategoryVH(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
