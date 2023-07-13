package com.example.prm_project.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_project.R;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.viewmodel.AddressViewModel;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private Context context;
    private List<Address> addressList;
    private AddressViewModel addressViewModel;

    public AddressAdapter(Context context, List<Address> addressList, AddressViewModel addressViewModel) {
        this.context = context;
        this.addressList = addressList;
        this.addressViewModel = addressViewModel;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_address, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.number.setText(address.getID());
        holder.address.setText(address.getAddress());
        holder.addressType.setText(address.getAddress_type());
        holder.editButton.setOnClickListener(v -> {
            if (holder.editButton.getText().equals("Save")) {

                //Save information and sent to database
                address.setAddress(holder.address.toString());
                address.setAddress_type(Integer.parseInt(holder.addressType.toString()));
                addressViewModel.update(address);

                //Turn off editmode
                holder.address.setEnabled(false);
                holder.addressType.setEnabled(false);

                holder.editButton.setText("Edit");

            }else {
                holder.address.setEnabled(true);
                holder.addressType.setEnabled(true);
                holder.editButton.setText("Save");
            }
            // Xử lý sự kiện khi button Edit được click
        });
        holder.deleteButton.setOnClickListener(v -> {
            addressViewModel.delete(address);

            // Xử lý sự kiện khi button Delete được click
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {

        public TextView number;
        public EditText address;
        public EditText addressType;
        public Button editButton;
        public Button deleteButton;

        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            address = itemView.findViewById(R.id.address);
            addressType = itemView.findViewById(R.id.address_type);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}
