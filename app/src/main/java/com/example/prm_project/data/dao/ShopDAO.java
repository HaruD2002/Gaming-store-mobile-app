package com.example.prm_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.prm_project.data.dao.models.Shop;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface ShopDAO {
    @Insert
    Completable insert(Shop shop);

    @Update
    Completable update(Shop shop);

    @Delete
    Completable delete(Shop shop);

    @Query("select * from Shop")
    LiveData<List<Shop>> getAllShops();
    @Query("SELECT * FROM Shop WHERE shop_name LIKE :searchQuery")
    LiveData<List<Shop>> getAllShopsContainString(String searchQuery);
    @Query("Select * from Shop where Shop.ID = :id")
    LiveData<Shop> getShopById(int id);

    @Query("Insert into Shop(shop_name ,created_dt, shop_owner, online_status) " +
            "values(:shop_name,:created_dt,:shop_owner ,:online_status)")
    Completable CreateShop(String shop_name, String created_dt,int shop_owner, boolean online_status);

    @Query("Update Shop set online_status = :status where ID=:id")
    Completable updateOnlineStatus(int id, int status);

    @Query("Update Shop set shop_name=:shop_name, shop_owner=:shop_owner where ID = :id ")
    Completable updateShop(int id, String shop_name, int shop_owner);
}
