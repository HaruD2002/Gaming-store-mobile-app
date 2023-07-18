package com.example.prm_project.viewmodel;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.data.repository.UserRepository;
import com.example.prm_project.utils.PasswordHashing;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private final PasswordHashing ph = new PasswordHashing();
    private LiveData<List<User>> userList;
    private UserDAO userDAO;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userList = getUserList();
    }

    public LiveData<List<User>> getUserList(){
        return userRepository.getUserList();
    }


    public LiveData<User> Login(String username, String password){
        String encrypt_password = ph.encoding(password);
        LiveData<User> user = userRepository.getSingleUser(username, encrypt_password);
        if(user != null){
            return user;
        }
        return null;
    }

    public void CreateNewUser(String username, String password, String email, String phoneNo, TextView message){
        String encrypt_password = ph.encoding(password);
        String createdDt = new Date().toString();
            userRepository.CreateUser(username, encrypt_password, email, phoneNo, createdDt)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                            },
                            error -> {
                                if(error instanceof SQLiteConstraintException){
                                    message.setVisibility(View.VISIBLE);
                                }
                                else {
                                    Log.e("Error", "something else");
                                }
                            }
                    );
    }

}
