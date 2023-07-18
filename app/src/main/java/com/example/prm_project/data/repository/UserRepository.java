package com.example.prm_project.data.repository;

import androidx.lifecycle.LiveData;

import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.utils.PasswordHashing;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class UserRepository {
    private final UserDAO userDAO;
    private final PasswordHashing ph = new PasswordHashing();

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public LiveData<List<User>> getUserList() {
        return userDAO.getAllUser();
    }

    public Completable insert(User user) {
        return userDAO.insert(user);
    }

    public Completable delete(User user) {
        return userDAO.delete(user);
    }

    public Completable update(User user) {
        return userDAO.update(user);
    }

    public Completable CreateUser(String username, String password,String email, String phoneNo, String createdDt){
        return userDAO.CreateUser(username, password,email, phoneNo, createdDt);
    }

    public LiveData<User> getSingleUser(String username, String password) {
        return userDAO.loginUser(username, password);
    }

    public LiveData<User> getUserInformationByID(int id){
        return  userDAO.getUserById(id);
    }
}
