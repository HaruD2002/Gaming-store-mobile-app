package com.example.prm_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.data.dao.models.ItemCategory;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface CategoryDAO {
    @Query("Select * from Category LIMIT 5")
    LiveData<List<Category>>getAllCategory();


    @Query("insert into Category(category_name, description)"+
            " values(:categoryName, :category_description)")
    Completable createCategory(String categoryName, String category_description);


//    LiveData<ItemCategory> updateItemCategory(int itemID, int CategoryID);
}
