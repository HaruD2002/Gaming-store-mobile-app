package com.example.prm_project.views.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_project.R;
import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.views.ItemList;
import com.example.prm_project.views.ViewHolder.CategoryVH;

import java.util.ArrayList;
import java.util.List;

public class CategoryPefAdapter extends RecyclerView.Adapter<CategoryVH> {

    private List<Category> categoryListItem;
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> l;

    public CategoryPefAdapter(List<Category> categoryListItem, Context context) {
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
        Category c = categoryListItem.get(position);
        holder.setCategory(c);
        l.add(c.getID());
        holder.itemView.setOnClickListener(this::toItemWithCategory);
    }

    private void toItemWithCategory(View view) {

        SharedPreferences sp = context.getSharedPreferences("category_id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.apply();
        Intent toList = new Intent(view.getContext(), ItemList.class);
        view.getContext().startActivity(toList);
    }


    @Override
    public int getItemCount() {
        return categoryListItem.size();
    }
}
