package com.example.prm_project.utils;

import android.content.Context;
import android.widget.EditText;

import com.example.prm_project.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserValidator {

    private boolean EmailValidator(Context context, EditText email){
        String email_regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(email_regex);
        Matcher match = pattern.matcher(email.getText().toString());
        if(!match.find()){
            email.setError(context.getResources().getString(R.string.email_validator));
            return false;
        }
        return true;
    }

    private Boolean PasswordValidator(Context context, EditText password){
        String password_regex = "^(?=.*\\d)(?=.*[a-z]{2,})(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(password_regex);
        Matcher match = pattern.matcher(password.getText().toString());
        if(!match.find()){
            password.setError(context.getResources().getString(R.string.password_validator));
            return false;
        }
        return true;
    }
}

