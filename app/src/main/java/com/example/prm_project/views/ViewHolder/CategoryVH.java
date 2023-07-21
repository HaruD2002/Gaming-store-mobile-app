package com.example.prm_project.views.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_project.R;
import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.views.CategoryPreference;
import com.example.prm_project.views.ItemList;

import java.util.List;

public class CategoryVH extends RecyclerView.ViewHolder {
    private Context context;
    private TextView category_name_txt;
    private ConstraintLayout cl;
    private List<Integer> CategoryList;

    private void bindingView(View view) {
        category_name_txt = view.findViewById(R.id.category_name_txt);
    }

    public CategoryVH(View view, Context context) {
        super(view);
        this.context = context;
        bindingView(view);
    }

    public void setCategory(Category i){
        category_name_txt.setText(i.getCategoryName());
    }
}
