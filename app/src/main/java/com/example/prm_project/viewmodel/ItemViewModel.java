package com.example.prm_project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.data.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private final ItemRepository itemRepository;
    private LiveData<List<Category>> allCategory;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
    }

    public LiveData<List<Category>> getAllCategory(){
        allCategory =  itemRepository.CategoryList();
        return allCategory;
    }

}
