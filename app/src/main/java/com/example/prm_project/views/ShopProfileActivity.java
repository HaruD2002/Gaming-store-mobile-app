package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.ShopDAO;
import com.example.prm_project.data.dao.models.Shop;
import com.example.prm_project.viewmodel.ShopViewModel;
import com.example.prm_project.viewmodel.UserViewModel;

public class ShopProfileActivity extends AppCompatActivity {

    ShopDAO shopDAO;
    ShopViewModel shopViewModel;
    Shop currentShop;

    EditText shop_name_editText;
    TextView datecreated_textview;
    ImageView onlineStatus_imageView;
    Button editButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_profile);
        getViewModel();
        bindingView();
        bindingAction();
        displayShopInfo();
    }

    private void getViewModel() {
        shopDAO = DAO.getInstance(getApplicationContext()).shopDAO();
        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("SHOP", Context.MODE_PRIVATE);
        int shop_id = sharedPreferences.getInt("SHOP_ID", 0);
        currentShop = shopViewModel.getShopInformationByID(shop_id).getValue();
        //TEST
        /*user = new User();
        user.setUsername("kingofthegods0208201");
        user.setFirst_name("Tran");
        user.setLast_name("Minh Quang");
        user.setPassword(passwordHashing.encoding("02082001Quang"));
        user.setPhone_number("0964955408");
        user.setDOB("02/08/2001");
        user.setMail("zedquang2001@gmail.com");
        user.setGender(true);
        user.setOnline_status(1);*/
    }
    private void bindingView() {
        // Ánh xạ các phần tử UI
        shop_name_editText.findViewById(R.id.shop_name_editText_shop_profile);
        datecreated_textview.findViewById(R.id.created_date_textView_shopProfile);
        onlineStatus_imageView.findViewById(R.id.online_status_imageView_shopProfile);
    }
    private void bindingAction() {
        editButton.findViewById(R.id.edit_button_shopProfile);
        editButton.setOnClickListener(this::EditButtonListener);

    }

    private void EditButtonListener(View view) {
        if (editButton.getText().equals("Save")) {
            currentShop.setShopName(shop_name_editText.getText().toString());
            shopViewModel.updateShop(currentShop.getID(),currentShop.getShopName(),currentShop.getShop_owner());
            shop_name_editText.setEnabled(false);
            editButton.setText("Edit");

        } else {
            shop_name_editText.setEnabled(true);
            editButton.setText("Save");
        }
    }

    private void displayShopInfo() {

            shop_name_editText.setText(currentShop.getShopName());
            datecreated_textview.setText("Created: "+currentShop.getCreated_dt());
            if(currentShop.isOnlineStatus()){
                onlineStatus_imageView.setVisibility(View.VISIBLE);
            }else {
                onlineStatus_imageView.setVisibility(View.INVISIBLE);
            }
    }
}