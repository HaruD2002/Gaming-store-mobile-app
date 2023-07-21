package com.example.prm_project.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.prm_project.data.dao.models.Item;

import java.util.List;

@Dao
public interface ItemDAO {

    @Query("Select * from item where shop_id = :shopID")
    LiveData<List<Item>> getItemListFromShop(int shopID);


//    LiveData<Item> updateItemDescription(int itemID, int shopID);

}
