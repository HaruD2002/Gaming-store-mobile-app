package com.example.prm_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.data.repository.UserRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> userList;
    private UserDAO userDAO;

    public void init(UserDAO userDAO) {
        userRepository = new UserRepository(userDAO);
        userList = getUserList();

    }
    public LiveData<List<User>> getUserList(){
        return userRepository.getUserList();
    }

    public void insert(User user){
        userRepository.insert(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void update(User user) {
        userRepository.update(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<User> getUserInformationByID(int id) {
        return userRepository.getUserInformationByID(id);
    }


}
