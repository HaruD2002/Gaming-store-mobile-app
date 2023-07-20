package com.example.prm_project.viewmodel;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.prm_project.data.dao.ShopDAO;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.data.dao.models.Shop;
import com.example.prm_project.data.repository.ShopRepository;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShopViewModel extends AndroidViewModel {
    private ShopRepository shopRepository;
    private LiveData<List<Shop>> shopList;
    private ShopDAO shopDAO;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        shopRepository = new ShopRepository(application);
        shopList = getShopList();
    }

    public LiveData<List<Shop>> getShopList() {
        return shopRepository.getShopList();
    }


    public void CreateNewShop(String shop_name, int shop_owner, boolean online_status) {
        String createdDt = new Date().toString();
        shopRepository.CreateShop(shop_name, createdDt, shop_owner, online_status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updateShop(int id, String shop_name, int shop_owner) {
        shopRepository.updateShop(id, shop_name, shop_owner)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<Shop> getShopInformationByID(int id) {
        return shopRepository.getShopInformationByID(id);
    }

    public void delete(Shop shop) {
        shopRepository.delete(shop)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
