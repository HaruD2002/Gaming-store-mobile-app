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
    private PasswordHashing passwordHashing =new PasswordHashing();
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
        if (curPasswordTextView.getText().toString().isEmpty())
        {
            return 1;
        }else if (newPasswordTextView.getText().toString().isEmpty()){
            return 2;
        }else if (renewPasswordTextView.getText().toString().isEmpty()){
            return 3;
        }else if (!passwordHashing.verifyPassword(curPasswordTextView.getText().toString(),user.getPassword())){
            return 4;
        }else if (passwordHashing.verifyPassword(newPasswordTextView.getText().toString(),user.getPassword())){
            return 5;
        }else if (!newPasswordTextView.getText().toString().equals(renewPasswordTextView.getText().toString())){
            return 6;
        }
        return 0;
    }

    private void getViewModel() {
        userDAO = DAO.getInstance(getApplicationContext()).userDAO();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_ID", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", 1);
        user = userViewModel.getUserInformationByID(userId).getValue();
        //Test
        /*user = new User();
        user.setUsername("kingofthegods02082001");
        user.setPassword(passwordHashing.encoding("02082001Quang"));*/
    }

    private void bindingView() {
        userNameTextView = findViewById(R.id.username_textview_2);
        curPasswordTextView = findViewById(R.id.et_current_password_2);
        newPasswordTextView = findViewById(R.id.et_new_password_2);
        renewPasswordTextView = findViewById(R.id.et_confirm_new_password_2);
        notice = findViewById(R.id.txt_Notice_2);
    }

    private void bindingAction() {
        btn_ChangePassword = (Button) findViewById(R.id.btn_save);
        btn_ChangePassword.setOnClickListener(this::changePasswordEvent);
    }

    private void changePasswordEvent(View view) {
        int errorCode = checkError();
        switch (errorCode){
            case 0:{
                user.setPassword(passwordHashing.encoding(newPasswordTextView.getText().toString()));
                //userViewModel.updatePassword(user.getID(), user.getPassword());
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

    private void showNotice(String text){
        notice.setText(text);
        notice.setVisibility(View.VISIBLE);
    }

    private void displayUserInfo() {

            String userNameShow = "";
            for (int i = 0; i < user.getUsername().length(); i++) {
                if (i < user.getUsername().length() / 3) {
                    userNameShow += user.getUsername().charAt(i);
                } else {
                    userNameShow += "*";
                }
            }
            userNameTextView.setText(userNameShow);
    }
}