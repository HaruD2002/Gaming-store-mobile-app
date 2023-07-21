package com.example.prm_project.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.CategoryDAO;
import com.example.prm_project.data.dao.ItemDAO;
import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.data.dao.models.Item;
import com.example.prm_project.data.dao.models.ItemCategory;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class ItemRepository {
    private final CategoryDAO categoryDAO;
    private final ItemDAO itemDAO;

    public ItemRepository(Application application){
        DAO db = DAO.getInstance(application);
        categoryDAO = db.categoryDAO();
        itemDAO = db.itemDAO();
    }

    public LiveData<List<Category>> CategoryList(){
        return categoryDAO.getAllCategory();
//        return null;
    }

    public Completable createCategory(String name, String description){
        return categoryDAO.createCategory(name, description);
    }

    public LiveData<List<Item>> getAllItemByCategory(int category_id){
        return itemDAO.getListByCategory(category_id);
    }


}
