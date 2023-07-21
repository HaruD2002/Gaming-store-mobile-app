package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.AddressDAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.viewmodel.AddressViewModel;
import com.example.prm_project.viewmodel.UserViewModel;
import com.example.prm_project.views.Adapter.AddressAdapter;

import java.util.List;

public class ListAddressActivity extends AppCompatActivity {

    AddressDAO addressDAO;
    private AddressViewModel addressViewModel;
    private TextView usernameTextView;
    private TextView fullnameTextView;
    private TextView phoneTextView;
    private Button add_button;
    private UserDAO userDAO;
    ListView listViewAddresses;
    AddressAdapter addressAdapter;
    private UserViewModel userViewModel;

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
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        //Test
        /*curUser = new User();
        curUser.setUsername("Kingofthegods020800");
        curUser.setFirst_name("Tran");
        curUser.setLast_name("MiNHQUANG");
        curUser.setPhone_number("0361551251");
        lsAddressOfUser = new ArrayList<>();
        lsAddressOfUser.add(new Address(1, 1, "Address 1 Content"));
        lsAddressOfUser.add(new Address(2,1, "Address 2 Content"));*/
    }

    private void bindingView() {
        usernameTextView = findViewById(R.id.username_textview_listAddress);
        fullnameTextView = findViewById(R.id.fullname_textview_listAddress);
        phoneTextView = findViewById(R.id.phone_textview_listAddress);
        listViewAddresses = findViewById(R.id.listViewAddresses_listAddress);
        //addressNumber = findViewById(R.id.textView_numberAddress_listAddress);
        //address = findViewById(R.id.textViewAddressContent_listAddress);
    }

    private void bidingAction() {
        add_button = findViewById(R.id.new_address_button);
        add_button.setOnClickListener(v -> {
            // Thêm một đối tượng Address mới vào danh sách và cập nhật adapter
            Address newAdress = new Address();
            newAdress.setAddress("New Address");
            SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", Context.MODE_PRIVATE);
            int userId = sharedPreferences.getInt("USER_ID", -1);
            newAdress.setUserID(userId);
            addressViewModel.insert(newAdress);
            Toast.makeText(this, "Add Success", Toast.LENGTH_SHORT).show();
            addressAdapter.notifyDataSetChanged();
        });
    }

    private void displayListAddress() {
        addressViewModel = new ViewModelProvider(this).get(AddressViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);
        userViewModel.getUserInformationByID(userId).observe(this, curUser -> {
            usernameTextView.setText(curUser.getUsername());
            fullnameTextView.setText(curUser.getFirst_name() +" "+ curUser.getLast_name());
            phoneTextView.setText(curUser.getPhone_number());
        });
        addressViewModel.getAddressListOfUser(userId).observe(this, addresses -> {
            addressAdapter = new AddressAdapter(this, R.layout.item_address, addresses,addressViewModel);
            listViewAddresses.setAdapter(addressAdapter);
        });

    }

}

