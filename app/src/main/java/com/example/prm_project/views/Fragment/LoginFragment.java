package com.example.prm_project.views.Fragment;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.viewmodel.UserViewModel;
import com.example.prm_project.views.MainActivity;

public class LoginFragment extends Fragment {
    private EditText login_username;
    private EditText login_password;
    private Button login_user_btn;
    private UserDAO userDAO;
    private UserViewModel userViewModel;

    private void bindingView(View view){
        login_username = view.findViewById(R.id.login_username);
        login_password = view.findViewById(R.id.login_password);
        login_user_btn = view.findViewById(R.id.login_user_btn);
    }
    private void bindingAction(){
        login_user_btn.setOnClickListener(this::loginUser);
    }

    private void getVM(){
        userDAO = DAO.getInstance(getActivity().getApplicationContext()).userDAO();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init(userDAO);
    }

    private void loginUser(View view) {
        String username = login_username.getText().toString();
        String password = login_password.getText().toString();
        User user = userViewModel.Login(username, password);
        if(user != null){
            Log.d("login", "login success");
            Toast.makeText(getActivity().getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
            Intent toHome = new Intent(getActivity(), MainActivity.class);
            startActivity(toHome);
        }
        else{
            Toast.makeText(getActivity().getApplicationContext(), "Login fail", Toast.LENGTH_SHORT).show();
            Log.d("login", "login fail");
        }
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