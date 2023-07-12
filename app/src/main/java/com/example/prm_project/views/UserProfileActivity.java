package com.example.prm_project.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.viewmodel.UserViewModel;

public class UserProfileActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private int userId;
    private User user;
    private Button btn_EditProfile;
    private Button btn_ChangePassword;

    TextView userNameTextView;
    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView password;
    TextView gender;
    TextView phoneNumber;
    TextView dob;
    TextView onlineStatus;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getViewModel();
        bindingView();
        bindingAction();
        displayUserInfo();
    }

    private void getViewModel() {
        userDAO = DAO.getInstance(getApplicationContext()).userDAO();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userId = getIntent().getIntExtra("USER_ID", -1);
        user = userViewModel.getUserInformationByID(userId).getValue();
        userViewModel.init(userDAO);
    }

    private void bindingView() {
        // Ánh xạ các phần tử UI
        userNameTextView = findViewById(R.id.username_textview);
        firstNameTextView = findViewById(R.id.first_name_textview);
        lastNameTextView = findViewById(R.id.last_name_textview);
        password = findViewById(R.id.password_textview);
        gender = findViewById(R.id.gender_textview);
        phoneNumber = findViewById(R.id.phone_number_textview);
        dob = findViewById(R.id.birthdate_textview);
        onlineStatus = findViewById(R.id.online_status_textview);

    }

    private void bindingAction() {
        btn_EditProfile = (Button) findViewById(R.id.edit_profile_button);
        btn_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_EditProfile.getText().equals(getString(R.string.save_changes_button_text))) {


                    //Save information and sent to database
                    user.setFirst_name(firstNameTextView.toString());
                    user.setLast_name(lastNameTextView.toString());
                    user.setGender(gender.toString().equals("Male") ? true : false);
                    user.setPhone_number(phoneNumber.toString());
                    user.setDOB(dob.toString());
                    userViewModel.update(user);

                    //Turn off editmode
                    firstNameTextView.setEnabled(false);
                    lastNameTextView.setEnabled(false);
                    gender.setEnabled(false);
                    phoneNumber.setEnabled(false);
                    dob.setEnabled(false);
                    btn_EditProfile.setText(R.string.edit_profile_button_text);

                } else {
                    firstNameTextView.setEnabled(true);
                    lastNameTextView.setEnabled(true);
                    gender.setEnabled(true);
                    phoneNumber.setEnabled(true);
                    dob.setEnabled(true);
                    btn_EditProfile.setText(R.string.save_changes_button_text);

                }
            }
        });
        btn_ChangePassword = (Button) findViewById(R.id.change_password_button);
        btn_ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, ChangePasswordActivity.class);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
            }
        });
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
            firstNameTextView.setText(user.getFirst_name());
            lastNameTextView.setText(user.getLast_name());
            String passWordShow = "";
            for (int i = 0; i < user.getPassword().length(); i++) {
                passWordShow += "*";
            }
            password.setText(passWordShow);
            gender.setText(user.isGender() == true ? "Male" : "Female");
            phoneNumber.setText(user.getPhone_number());
            dob.setText(user.getDOB());
            onlineStatus.setText(user.getOnline_status() == 1 ? "Online" : "Offline");
        });
    }
}
