package com.example.prm_project.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.prm_project.data.dao.models.CartItem;

import java.util.List;

@Dao
public interface CartItemDAO {

    @Query("SELECT * FROM Cart_Item as cartItem " +
            "INNER JOIN Cart as cart ON cartItem.Cart_ID = cart.ID " +
            "INNER JOIN User as u ON u.ID = cart.user_ID " +
            "where u.ID = :id")
    LiveData<List<CartItem>> FindItemInCartByUser(int id);
}
