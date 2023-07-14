package com.example.prm_project.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.AddressDAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.viewmodel.AddressViewModel;

import java.util.List;

public class ListAddressActivity extends AppCompatActivity {

    AddressDAO addressDAO;
    private int userId;

    private AddressViewModel addressViewModel;

    private TextView addressNumber;
    private EditText address;
    private EditText address_type;

    private ScrollView listView;
    private Button edit_button;
    private Button delete_button;
    private Button add_button;
    private UserDAO userDAO;
    private List<Address> lsAddressOfUser;
    private RecyclerView recyclerView;
    AddressAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_address);
        getViewModel();
        bindingView();
        bidingAction();
        displayListAddress();
    }

    private void getViewModel() {
        addressDAO = DAO.getInstance(getApplicationContext()).addressDAO();
        userDAO = DAO.getInstance(getApplicationContext()).userDAO();
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);
        lsAddressOfUser = addressViewModel.getAddressListOfUser(userId).getValue();
    }

    private void bindingView() {
        recyclerView = findViewById(R.id.address_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AddressAdapter(this, lsAddressOfUser, addressViewModel);
        recyclerView.setAdapter(adapter);
    }

    private void bidingAction() {
        Button add_button = findViewById(R.id.new_address_button);
        add_button.setOnClickListener(v -> {
            // Thêm một đối tượng Address mới vào danh sách và cập nhật adapter
            Address address = new Address();
            lsAddressOfUser.add(address);
            addressViewModel.insert(address);
            adapter.notifyDataSetChanged();
        });

    }

    private void displayListAddress() {

    }

}

