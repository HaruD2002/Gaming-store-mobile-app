package com.example.prm_project.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.ShopDAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.data.dao.models.Shop;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.utils.PasswordHashing;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class ShopRepository {
    private final ShopDAO shopDAO;

    public ShopRepository(Application application){
        DAO db = DAO.getInstance(application);
        shopDAO = db.shopDAO();
    }
    public Completable delete(Shop shop) {
        return shopDAO.delete(shop);
    }

    public LiveData<List<Shop>> getShopList() {
        return shopDAO.getAllShops();
    }
    public LiveData<List<Shop>> getShopListContainString(String string) {
        return shopDAO.getAllShopsContainString(string);
    }


    public Completable CreateShop(String shop_name, String created_dt,int shop_owner, boolean online_status){
        return shopDAO.CreateShop(shop_name, created_dt,shop_owner, online_status);
    }

    public LiveData<Shop> getShopInformationByID(int id){
        return  shopDAO.getShopById(id);
    }



    public Completable UpdateStatus(int id, int status) {return shopDAO.updateOnlineStatus(id, status);}

    public Completable updateShop(int id, String shop_name, int shop_owner) {
        return shopDAO.updateShop(id, shop_name, shop_owner);
    }

}
