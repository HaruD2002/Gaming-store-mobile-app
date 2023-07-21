package com.example.prm_project.views.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_project.R;
import com.example.prm_project.data.dao.models.Category;

import java.util.List;

public class CategoryVH extends RecyclerView.ViewHolder {
    private Context context;
    private TextView category_name_txt;
    private ConstraintLayout cl;
    private List<Category> CategoryList;

    private void bindingView(View view) {
        category_name_txt = view.findViewById(R.id.category_name_txt);
    }

    private void bindingAction(){
        category_name_txt.setOnClickListener(this::toItemWithCategory);
    }

    private void toItemWithCategory(View view) {


    }

    public CategoryVH(View view, Context context) {
        super(view);
        this.context = context;
        bindingView(view);
        bindingAction();
    }

    public void setCategory(Category i){
        CategoryList.add(i);
        category_name_txt.setText(i.getCategoryName());
    }
}
