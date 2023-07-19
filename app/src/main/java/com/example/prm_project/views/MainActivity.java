package com.example.prm_project.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.prm_project.R;
import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.viewmodel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private UserDAO userDAO;

    private void bindingView() {
    }

    private void bindingAction() {
    }

    private void getViewModel() {
        userDAO = DAO.getInstance(getApplicationContext()).userDAO();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.init(userDAO);
    }

    private void observeView() {
        ListUser(new ListUserObserver());
    }

    private void ListUser(Observer<List<User>> observer){
        userViewModel.getUserList().observe(this, observer);
    }

    private class ListUserObserver implements Observer<List<User>> {
        @Override
        public void onChanged(List<User> users) {
            for(User u: users){
                Log.d("list", u.getUsername() + "" + u.getPassword());
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();
        getViewModel();
        observeView();
    }

}