package com.example.prm_project.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.prm_project.data.dao.models.Item;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface ItemDAO {

    @Query("Select * from item where shop_id = :shopID")
    LiveData<List<Item>> getItemListFromShop(int shopID);

    @Query("Select * from Item i inner join Item_Category ic on i.ID = ic.item_id where ic.category_id = :category_id")
    LiveData<List<Item>> getListByCategory(int category_id);


//    LiveData<Item> updateItemDescription(int itemID, int shopID);

}
