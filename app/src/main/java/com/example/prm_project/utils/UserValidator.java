package com.example.prm_project.utils;

import android.content.Context;
import android.widget.EditText;

import com.example.prm_project.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserValidator {

    public boolean LengthValidator(Context context, String text, int length){
        if(text.length() < length){
            return false;
        }
        return true;
    }

    public boolean EmailValidator(Context context, String email){
        String email_regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(email_regex);
        Matcher match = pattern.matcher(email);
        if(!match.find()){
            return false;
        }
        return true;
    }

    public boolean PasswordValidator(Context context, String password){
        String password_regex = "^(?=.*\\d)(?=.*[a-z]{2,})(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(password_regex);
        Matcher match = pattern.matcher(password);
        if(!match.find()){
            return false;
        }
        return true;
    }
}

