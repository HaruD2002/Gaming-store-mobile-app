 package com.example.prm_project.views.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.utils.UserValidator;
import com.example.prm_project.viewmodel.UserViewModel;


 public class RegisterFragment extends Fragment {
    private Button btn_register_commit_u;
    private EditText u_username;
    private EditText u_password;
    private EditText u_email;
    private EditText u_phone;
    private UserDAO userDAO;
    private UserViewModel userViewModel;
    private TextView register_exist_username_message;

     private void getVM(){
         userDAO = DAO.getInstance(getActivity().getApplicationContext()).userDAO();
         userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
     }

    public void bindingView(View view){
        btn_register_commit_u = view.findViewById(R.id.btn_sign_up_u_commit);
        u_username = view.findViewById(R.id.register_user_username_txt);
        u_password = view.findViewById(R.id.register_user_password_txt);
        u_email = view.findViewById(R.id.register_email_u);
        u_phone = view.findViewById(R.id.register_user_phone_no_txt);
        register_exist_username_message = view.findViewById(R.id.register_exist_username_message);
    }
    public void bindingAction(){
        btn_register_commit_u.setOnClickListener(this::CreateNewUser);
    }

    private void CreateNewUser(View view) {
        boolean check = true;
        Context context = getActivity().getApplicationContext();
        String username = u_username.getText().toString();
        String password = u_password.getText().toString();
        String email = u_email.getText().toString();
        String phoneNo = u_phone.getText().toString();
        UserValidator uv = new UserValidator();
        if(!uv.LengthValidator(context,username, 8)){
            u_username.setError("Username must longer than 8 character");
            check = false;
        }
        if(!uv.EmailValidator(context, email)){
            u_email.setError("Email in wrong format");
            check = false;
        }
        if(!uv.PasswordValidator(context, password)){
            u_password.setError("Password must contain 8 characters, and at least 1 number");
            check = false;
        }
        if(!uv.LengthValidator(context, phoneNo, 10)){
            u_phone.setError("User phone number must have at least 10 number");
            check = false;
        }
        if(check){
            userViewModel.CreateNewUser(username,password,email,phoneNo, register_exist_username_message);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_and_register_container, new LoginFragment())
                    .commit();
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}