package com.example.prm_project.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.data.repository.UserRepository;
import com.example.prm_project.utils.PasswordHashing;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private final PasswordHashing ph = new PasswordHashing();
    private LiveData<List<User>> userList;
    private UserDAO userDAO;

    public void init(UserDAO userDAO) {
        userRepository = new UserRepository(userDAO);
        userList = getUserList();

    }
    public LiveData<List<User>> getUserList(){
        return userRepository.getUserList();
    }


    public boolean Login(String username, String password){
        String encrypt_password = ph.encoding(password);
        LiveData<User> user = userRepository.getSingleUser(username, encrypt_password);
        if(user != null){
            return true;
        }
        return false;
    }

    public void CreateNewUser(String username, String password, String email, String phoneNo){
        String encrypt_password = ph.encoding(password);
        String createdDt = new Date().toString();
            userRepository.CreateUser(username, password, email, phoneNo, createdDt)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {},
                            throwable -> {
                                Log.e("Error", "something when wrong" + throwable.getMessage());
                            }
                    );
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
