package com.example.prm_project.views.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.utils.PasswordHashing;
import com.example.prm_project.viewmodel.UserViewModel;
import com.example.prm_project.views.MainActivity;

import org.w3c.dom.Text;

public class LoginFragment extends Fragment {
    private EditText login_username;
    private EditText login_password;
    private TextView login_fail_message;
    private Button login_user_btn;
    private UserDAO userDAO;
    private UserViewModel userViewModel;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private int OFFLINE = 0;
    private int ONLINE = 1;

    private void bindingView(View view) {
        login_username = view.findViewById(R.id.login_username);
        login_password = view.findViewById(R.id.login_password);
        login_user_btn = view.findViewById(R.id.login_user_btn);
        login_fail_message = view.findViewById(R.id.login_fail_message);
    }

    private void bindingAction() {
        login_user_btn.setOnClickListener(this::loginUser);
    }

    private void getVM() {
        userDAO = DAO.getInstance(getActivity().getApplicationContext()).userDAO();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    private void loginUser(View view) {
        String username = login_username.getText().toString();
        String password = login_password.getText().toString();
        PasswordHashing ph = new PasswordHashing();
        String encrypt = ph.encoding(password);

        userViewModel.Login(username,password).observe(this, user -> {
            if (user != null) {
                if(ph.verifyPassword(password, user.getPassword())) {
                    sharedPreferences = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
                    userViewModel.updateOnlineStatus(user.getID(), ONLINE);
                    editor = sharedPreferences.edit();
                    editor.putInt("USER_ID", user.getID());
                    editor.putString("USER_USERNAME", user.getUsername());
                    editor.apply();
                    Intent toHome = new Intent(getActivity(), MainActivity.class);
                    startActivity(toHome);
                } else{
                    user = null;
                    login_fail_message.setVisibility(View.VISIBLE);
                }
            } else {
                login_fail_message.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVM();
        bindingView(view);
        bindingAction();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}