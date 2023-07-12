package com.example.prm_project.data.repository;

import androidx.lifecycle.LiveData;

import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class UserRepository {
    private final UserDAO userDAO;
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
    public Completable update(User user) {return userDAO.update(user);}
    public LiveData<User> getUserInformationByID(int userID) { return  userDAO.getUserById(userID); }
}
