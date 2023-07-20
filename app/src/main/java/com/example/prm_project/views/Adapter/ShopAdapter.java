package com.example.prm_project.views.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.prm_project.R;
import com.example.prm_project.data.dao.models.Shop;
import com.example.prm_project.viewmodel.ShopViewModel;
import com.example.prm_project.views.ChangePasswordActivity;
import com.example.prm_project.views.ShopProfileActivity;
import com.example.prm_project.views.UserProfileActivity;

import java.util.List;

public class ShopAdapter extends ArrayAdapter<Shop> {

    private List<Shop> shopList;
    private Context context;
    private ShopViewModel shopViewModel;
    Shop currentShop;

    public ShopAdapter(@NonNull Context context, int resource, @NonNull List<Shop> objects, ShopViewModel shopViewModel) {
        super(context, resource, objects);
        this.context = context;
        this.shopList = objects;
        this.shopViewModel = shopViewModel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false);
        }
        currentShop = shopList.get(position);

        TextView textViewShopName = convertView.findViewById(R.id.textView_shopName_listShop);
        Button buttonView = convertView.findViewById(R.id.buttonView_listShop);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete_listShop);

        textViewShopName.setText(currentShop.getShopName());

        buttonView.setOnClickListener(this::toChangeShopProfileActivity);


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút Delete được bấm
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Thực hiện hành động khi người dùng chọn Yes
                                shopViewModel.delete(currentShop);
                                Toast.makeText(context, "Delete Success" , Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Thực hiện hành động khi người dùng chọn No
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return convertView;
    }

    private void toChangeShopProfileActivity(View view) {
        Intent intent = new Intent(context, ShopProfileActivity.class);
        SharedPreferences sharedPreferences = context.getSharedPreferences("SHOP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("SHOP_ID", currentShop.getID());
        editor.apply();
        context.startActivity(intent);
    }
}
