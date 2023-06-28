package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prm_project.R;
import com.example.prm_project.views.Fragment.LoginFragment;
import com.example.prm_project.views.Fragment.RegisterFragment;

public class LoginAndRegister extends AppCompatActivity {
    private Button btn_register_u;
    private Button btn_login_u;
    private Fragment registerFragment;
    private Fragment loginFragment;
    public void bindingView(){
        btn_login_u = findViewById(R.id.login_btn_u);
        btn_register_u = findViewById(R.id.register_btn_u);
    }
    public void bindingAction(){
        btn_register_u.setOnClickListener(this::toRegisterFragment);
        btn_login_u.setOnClickListener(this::toLoginFragment);
    }

    private void toLoginFragment(View view) {
        if(loginFragment == null){
            loginFragment = new LoginFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_and_register_container, loginFragment)
                .addToBackStack(null)
                .commit();
    }

    private void toRegisterFragment(View view) {
        if(registerFragment == null){
            registerFragment = new RegisterFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_and_register_container, registerFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
        bindingView();
        bindingAction();
    }
}