package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.ShopDAO;
import com.example.prm_project.data.dao.models.Shop;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.viewmodel.ShopViewModel;
import com.example.prm_project.views.Adapter.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListShopActivity extends AppCompatActivity {

    ShopDAO shopDAO;
    private int userID;
    private ShopViewModel shopViewModel;
    private ListView listViewAddresses_listAddress;
    private Button btn_createShop_listshop;
    private User curUser;
    private List<Shop> lsAllShop;
    private ShopAdapter shopAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shop);
        getViewModel();
        bindingView();
        bidingAction();
        displayListShop();
    }

    private void getViewModel(){
        shopDAO = DAO.getInstance(getApplicationContext()).shopDAO();
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        lsAllShop = shopViewModel.getShopList().getValue();
        //Test
        lsAllShop = new ArrayList<>();
        Shop shop1 = new Shop();
        shop1.setID(1);
        shop1.setShopName("ACN Gaming");
        shop1.setCreated_dt("02/08/2022");
        shop1.setShop_owner(1);
        shop1.setOnlineStatus(false);
        Shop shop2 = new Shop();
        shop2.setID(2);
        shop2.setShopName("AAAAA Gaming");
        shop2.setCreated_dt("02/08/2021");
        shop2.setShop_owner(2);
        shop2.setOnlineStatus(true);
        Shop shop3 = new Shop();
        shop3.setID(3);
        shop3.setShopName("AAAVSCS Gaming");
        shop3.setCreated_dt("02/08/2023");
        shop3.setShop_owner(3);
        shop3.setOnlineStatus(false);
        lsAllShop.add(shop1);
        lsAllShop.add(shop2);
        lsAllShop.add(shop3);
    }
    private void bindingView() {
        listViewAddresses_listAddress = findViewById(R.id.listViewAddresses_listAddress);

    }
    private void bidingAction() {
        btn_createShop_listshop = findViewById(R.id.btn_createShop_listshop);
        btn_createShop_listshop.setOnClickListener(v -> {
            Shop newShop = new Shop();
            newShop.setShopName("NEW SHOP");
            shopViewModel.CreateNewShop(newShop.getShopName(),newShop.getShop_owner(),newShop.isOnlineStatus());
            Toast.makeText(this, "Create Success", Toast.LENGTH_SHORT).show();
            shopAdapter.notifyDataSetChanged();
        });
    }
    private void displayListShop() {
        shopAdapter = new ShopAdapter(this, R.layout.item_shop, lsAllShop,shopViewModel);
        listViewAddresses_listAddress.setAdapter(shopAdapter);
    }
}