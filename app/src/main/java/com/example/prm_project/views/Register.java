package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.viewmodel.UserViewModel;

public class Register extends AppCompatActivity {

    private UserDAO userDAO;
    private UserViewModel userViewModel;
    private EditText username;
    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText password;
    private EditText phone_no;
    private EditText DOB;
    private Button Register;
    private User user;

    private void getViewModel() {
        userDAO = DAO.getInstance(getApplicationContext()).userDAO();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init(userDAO);
    }

    private void bindingView() {
    }

    private void bindingAction() {
        Register.setOnClickListener(this::RegisterNewUser);
    }

    private void RegisterNewUser(View view) {
        try{
            createUser();
            Log.d("Success", "User register successful");
        }catch (Exception e){
            Log.d("Not success", "User not register successfully");
        }
    }

    private void createUser() {
        userViewModel.CreateUser(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getViewModel();
        bindingAction();
        bindingView();
        createUser();
    }
}