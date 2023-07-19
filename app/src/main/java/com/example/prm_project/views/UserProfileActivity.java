package com.example.prm_project.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserProfileActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private int userId;
    private User user;
    private Button btn_EditProfile;
    private Button btn_ChangePassword;
    private Button btn_ChooseDate;
    private Button btn_listAddress;
    Calendar dateOfBirth = null;

    TextView userNameTextView;
    TextInputEditText firstNameTextView;
    TextInputEditText lastNameTextView;
    TextView password;
    RadioButton male;
    RadioButton feMale;

    TextInputEditText phoneNumber;
    TextView dob;
    Switch onlineStatus;
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
        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);
        user = userViewModel.getUserInformationByID(userId).getValue();
    }

    private void bindingView() {
        // Ánh xạ các phần tử UI
        userNameTextView = findViewById(R.id.username_textview_1);
        firstNameTextView = findViewById(R.id.first_name_input_1);
        lastNameTextView = findViewById(R.id.last_name_input_1);
        password = findViewById(R.id.password_textview_1);
        phoneNumber = findViewById(R.id.phone_number_input_1);
        dob = findViewById(R.id.date_of_birth_text_view_1);
        onlineStatus = findViewById(R.id.online_status_switch_1);
        male = findViewById(R.id.male_radio_button_1);
        feMale = findViewById(R.id.female_radio_button_1);

    }

    private void bindingAction() {
        btn_ChooseDate = (Button) findViewById(R.id.date_of_birth_button_1);
        btn_ChooseDate.setOnClickListener(view -> showDatePicker());
        btn_EditProfile = (Button) findViewById(R.id.btn_EditProfile_1);
        btn_EditProfile.setOnClickListener(this::EditProfileListener);
        btn_ChangePassword = (Button) findViewById(R.id.btn_ChangePassword_1);
        btn_ChangePassword.setOnClickListener(this:: toChangePasswordActivity);
        btn_listAddress = (Button) findViewById(R.id.btn_listAddress_1);
        btn_listAddress.setOnClickListener(this:: toListAdressActivity);
    }

    private void toListAdressActivity(View view) {
        Intent intent = new Intent(UserProfileActivity.this, ListAddressActivity.class);
        intent.putExtra("USER_ID",1);
        startActivity(intent);
    }
    private void showDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            dateOfBirth.set(year, monthOfYear, dayOfMonth);
            updateDateOfBirthTextView();
        };
        int year = dateOfBirth.get(Calendar.YEAR);
        int month = dateOfBirth.get(Calendar.MONTH);
        int day = dateOfBirth.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void updateDateOfBirthTextView() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateOfBirthString = simpleDateFormat.format(dateOfBirth.getTime());
        dob.setText(dateOfBirthString);
    }

    private void toChangePasswordActivity(View view) {
        Intent intent = new Intent(UserProfileActivity.this, ChangePasswordActivity.class);
        intent.putExtra("USER_ID",1);
        startActivity(intent);
    }

    private void EditProfileListener(View view) {
        if (btn_EditProfile.getText().equals("Save")) {
            userViewModel.updateUser(1, firstNameTextView.toString(), lastNameTextView.toString().toString(), "", male.isChecked(), " ",dob.toString());
            firstNameTextView.setEnabled(false);
            lastNameTextView.setEnabled(false);
            male.setEnabled(false);
            feMale.setEnabled(false);
            phoneNumber.setEnabled(false);
            dob.setEnabled(false);
            btn_EditProfile.setText("Edit");
        } else {
            firstNameTextView.setEnabled(true);
            lastNameTextView.setEnabled(true);
            male.setEnabled(true);
            feMale.setEnabled(true);
            phoneNumber.setEnabled(true);
            dob.setEnabled(true);
            btn_EditProfile.setText("Save");
        }
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
            if (user.isGender()) {
                male.setChecked(true);
            } else {
                feMale.setChecked(true);
            }
            phoneNumber.setText(user.getPhone_number());
            dob.setText(user.getDOB());
            onlineStatus.setText(user.getOnline_status() == 1 ? "Online" : "Offline");
        });
    }
}
