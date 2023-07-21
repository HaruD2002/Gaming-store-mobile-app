package com.example.prm_project.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.prm_project.utils.PasswordHashing;
import com.example.prm_project.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserProfileActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private int userId;
    private PasswordHashing passwordHashing = new PasswordHashing();

    private Button btn_EditProfile;
    private Button btn_ChangePassword;
    private Button btn_ChooseDate;
    Calendar dateOfBirth = null;

    TextView userNameTextView;
    TextInputEditText firstNameTextView;
    TextInputEditText lastNameTextView;
    TextView password;
    RadioButton male;
    RadioButton feMale;

    TextInputEditText phoneNumber;
    TextInputEditText email;
    TextView dob;
    Switch onlineStatus;
    UserDAO userDAO;
    private ImageButton backtoHome;
    private ImageButton logout_btn;

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
        email = findViewById(R.id.email_input_1);
        backtoHome = findViewById(R.id.to_home_btn);
        logout_btn = findViewById(R.id.logout_btn);
    }

    private void bindingAction() {
        btn_ChooseDate = (Button) findViewById(R.id.date_of_birth_button_1);
        btn_ChooseDate.setOnClickListener(view -> showDatePicker());
        btn_EditProfile = (Button) findViewById(R.id.btn_EditProfile_1);
        btn_EditProfile.setOnClickListener(this::EditProfileListener);
        btn_ChangePassword = (Button) findViewById(R.id.btn_ChangePassword_1);
        btn_ChangePassword.setOnClickListener(this::toChangePasswordActivity);
        backtoHome.setOnClickListener(this::backToHome);
        logout_btn.setOnClickListener(this::logout);
    }

    private void logout(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("USER_ID", -1);
        editor.apply();
        Intent toHome = new Intent(this, HomePage.class);
        finish();
        startActivity(toHome);
    }

    private void backToHome(View view) {
        Intent toHome = new Intent(this, HomePage.class);
        startActivity(toHome);
    }

    private void showDatePicker() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = null;
        if (dob.getText().toString() != null) {
            try {
                parsedDate = dateFormat.parse(dob.getText().toString());
                dateOfBirth = Calendar.getInstance();
                dateOfBirth.setTime(parsedDate);
            } catch (ParseException e) {
                parsedDate = new Date(01/01/2001);
                dateOfBirth = Calendar.getInstance();
                dateOfBirth.setTime(parsedDate);
            }
        }


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
        startActivity(intent);
    }

    private void EditProfileListener(View view) {
        if (btn_EditProfile.getText().equals("Save")) {
            Log.d("SIDHAOFOW","HERE");
            SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
            userId = sharedPreferences.getInt("USER_ID",-1);
            userViewModel.updateUser(userId, firstNameTextView.getText().toString(), lastNameTextView.getText().toString(), email.getText().toString(), male.isChecked() == true, phoneNumber.getText().toString(), dob.getText().toString());
            firstNameTextView.setEnabled(false);
            lastNameTextView.setEnabled(false);
            male.setEnabled(false);
            feMale.setEnabled(false);
            phoneNumber.setEnabled(false);
            dob.setEnabled(false);
            btn_ChooseDate.setVisibility(View.INVISIBLE);

            email.setEnabled(false);
            btn_EditProfile.setText("Edit");
        } else {
            firstNameTextView.setEnabled(true);
            lastNameTextView.setEnabled(true);
            male.setEnabled(true);
            feMale.setEnabled(true);
            phoneNumber.setEnabled(true);
            dob.setEnabled(true);
            btn_ChooseDate.setVisibility(View.VISIBLE);
            btn_ChooseDate.setEnabled(true);
            email.setEnabled(true);
            btn_EditProfile.setText("Save");
        }
    }

    private void displayUserInfo() {

        SharedPreferences sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("USER_ID", -1);
        userViewModel.getUserInformationByID(userId).observe(this, user -> {
            String userNameShow = "";
            for (int i = 0; i < user.getUsername().length(); i++) {
                if (i < user.getUsername().length() / 3) {
                    userNameShow += user.getUsername().charAt(i);
                } else {
                    userNameShow += "*";
                }
            }
            userNameTextView.setText(userNameShow);
            firstNameTextView.setText(user.getFirst_name() == null ? " " : user.getFirst_name());
            lastNameTextView.setText(user.getLast_name() == null ? " " : user.getLast_name());
        /*String passWordShow = "";
        for (int i = 0; i < 15; i++) {
            passWordShow += "*";
        }*/
            if (user.getPassword().length() <= 15) {
                password.setText(user.getPassword());
            } else {
                String passWordShow = "";
                for (int i = 0; i < 15; i++) {
                    passWordShow += "*";
                }
                password.setText(passWordShow);

            }
            if (user.isGender()) {
                male.setChecked(true);
            } else {
                feMale.setChecked(true);
            }
            email.setText(user.getMail());
            phoneNumber.setText(user.getPhone_number());
            dob.setText(user.getDOB() == null ? " " : user.getDOB());
            onlineStatus.setText(user.getOnline_status() == 1 ? "Online" : "Offline");
        });
    }

}

