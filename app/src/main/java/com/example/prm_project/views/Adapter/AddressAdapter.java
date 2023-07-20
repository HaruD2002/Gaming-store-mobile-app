package com.example.prm_project.views.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.prm_project.R;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.viewmodel.AddressViewModel;

import java.util.List;

public class AddressAdapter extends ArrayAdapter<Address> {

    private List<Address> addressList;
    private Context context;
    private AddressViewModel addressViewModel;

    public AddressAdapter(@NonNull Context context, int resource, @NonNull List<Address> objects, AddressViewModel addressViewModel) {
        super(context, resource, objects);
        this.context = context;
        this.addressList = objects;
        this.addressViewModel = addressViewModel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
        }
        Address currentAddress = addressList.get(position);

        TextView textViewAddressId = convertView.findViewById(R.id.textView_shopName_listShop);
        TextView textViewAddressContent = convertView.findViewById(R.id.textViewAddressContent_listAddress);
        Button buttonEdit = convertView.findViewById(R.id.buttonEdit_listAddress);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete_listAddress);

        textViewAddressId.setText("ID: " + currentAddress.getID());
        textViewAddressContent.setText("Address: " + currentAddress.getAddress());

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nút Edit được bấm
                if (buttonEdit.getText().equals("Save")) {
                    currentAddress.setAddress(textViewAddressContent.getText().toString());
                    //Log.d("BUGGGG",currentAddress.getAddress());
                    textViewAddressContent.setEnabled(false);
                    addressViewModel.update(currentAddress);
                    Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show();
                    buttonEdit.setText("Edit");
                    notifyDataSetChanged();

                } else {
                    textViewAddressContent.setEnabled(true);
                    buttonEdit.setText("Save");

                }
            }
        });

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
                                addressViewModel.delete(currentAddress);
                                Toast.makeText(context, "Delete Success", Toast.LENGTH_SHORT).show();
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
                alert.show();            }
        });

        return convertView;
    }
}
