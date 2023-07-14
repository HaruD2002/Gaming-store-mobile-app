package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.utils.PasswordHashing;
import com.example.prm_project.viewmodel.UserViewModel;

public class ChangePasswordActivity extends AppCompatActivity {
    private UserDAO userDAO;
    private PasswordHashing passwordHashing;
    private UserViewModel userViewModel;
    private int userId;
    private User user;

    private Button btn_ChangePassword;
    TextView userNameTextView;
    TextView curPasswordTextView;
    TextView newPasswordTextView;
    TextView renewPasswordTextView;
    TextView notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getViewModel();
        bindingView();
        bindingAction();
        displayUserInfo();

    }

    private int checkError() {
        if (curPasswordTextView.toString().isEmpty())
        {
            return 1;
        }else if (newPasswordTextView.toString().isEmpty()){
            return 2;
        }else if (renewPasswordTextView.toString().isEmpty()){
            return 3;
        }else if (!passwordHashing.verifyPassword(curPasswordTextView.toString(),user.getPassword())){
            return 4;
        }else if (passwordHashing.verifyPassword(newPasswordTextView.toString(),user.getPassword())){
            return 5;
        }else if (!newPasswordTextView.toString().equals(renewPasswordTextView.toString())){
            return 6;
        }
        return 0;
    }

    private void getViewModel() {
        userDAO = DAO.getInstance(getApplicationContext()).userDAO();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);
        user = userViewModel.getUserInformationByID(userId).getValue();
        userViewModel.init(userDAO);
    }

    private void bindingView() {
        userNameTextView = findViewById(R.id.username_textview);
        curPasswordTextView = findViewById(R.id.et_current_password);
        newPasswordTextView = findViewById(R.id.et_new_password);
        renewPasswordTextView = findViewById(R.id.et_confirm_new_password);
        notice = findViewById(R.id.txt_Notice);
    }

    private void bindingAction() {
        btn_ChangePassword = (Button) findViewById(R.id.btn_save);
        btn_ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int errorCode = checkError();
                switch (errorCode){
                    case 0:{
                        user.setPassword(passwordHashing.encoding(newPasswordTextView.toString()));
                        userViewModel.update(user);
                        showNotice("Password Change Success");
                        break;
                    }
                    case 1:{
                        showNotice("Current Password Can Not Empty");
                        break;
                    }case 2:{
                        showNotice("New Password Can Not Empty");
                        break;
                    }case 3:{
                        showNotice("Confirm New Password Can Not Empty");
                        break;
                    }case 4:{
                        showNotice("Current Password is not correct");
                        break;
                    }case 5:{
                        showNotice("The new password must be different from the current password");
                        break;
                    }case 6:{
                        showNotice("The new password and the Confirm password does not match");
                        break;
                    }
                }
            }
        });
    }

    private void showNotice(String text){
        notice.setText(text);
        notice.setVisibility(View.VISIBLE);
    }

    private void displayUserInfo() {
        userViewModel.getUserInformationByID(userId).observe(this, user -> {
            // Hiển thị thông tin của User lên giao diện
            String userNameShow = "";
            for (int i = 0; i < user.getUsername().length(); i++) {
                if (i < user.getUsername().length() / 3) {
                    userNameShow += user.getUsername().charAt(i);
                } else {
                    userNameShow += "*";
                }
            }
            userNameTextView.setText(userNameShow);
        });

    }
}