package com.example.prm_project.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.prm_project.data.DAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class UserRepository {
    private final UserDAO userDAO;

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

    public LiveData<User> getUserInformationByID(int id){
        return  userDAO.getUserById(id);
    }

    public Completable UpdateStatus(int id, int status) {return userDAO.updateOnlineStatus(id, status);}

    public Completable updateUser(int id, String first_name, String last_name, String mail, boolean gender, String phone_no, String DOB) {
        return userDAO.updateUser(id, first_name, last_name, mail, gender, phone_no, DOB);
    }

    public Completable changePassword(int id, String password) {
        return userDAO.updatePassword(id, password);
    }
}
