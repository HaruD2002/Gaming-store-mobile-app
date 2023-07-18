package com.example.prm_project.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.utils.PasswordHashing;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class UserRepository {
    private final UserDAO userDAO;
    private final PasswordHashing ph = new PasswordHashing();

    public UserRepository(Application application){
        DAO db = DAO.getInstance(application);
        userDAO = db.userDAO();
    }

    public LiveData<List<User>> getUserList() {
        return userDAO.getAllUser();
    }


    public Completable CreateUser(String username, String password,String email, String phoneNo, String createdDt){
        return userDAO.CreateUser(username, password,email, phoneNo, createdDt);
    }

    public LiveData<User> getSingleUser(String username, String password) {
        return userDAO.loginUser(username);
    }

    public LiveData<User> existUsername(String username){
        return userDAO.getUserName(username);
    }
}
