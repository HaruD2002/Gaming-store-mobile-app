package com.example.prm_project.viewmodel;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    public void updateOnlineStatus(int id, int status) {
        userRepository.UpdateStatus(id, status).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                        },
                        throwable -> {
                            Log.e("Error", "something when wrong");
                        }
                );
    }


    public void CreateNewUser(String username, String password, String email, String phoneNo, TextView message){
        String encrypt_password = ph.encoding(password);
        String createdDt = new Date().toString();
            userRepository.CreateUser(username, encrypt_password, email, phoneNo, createdDt)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> {
                                Toast.makeText(getApplication().getApplicationContext(), "Create Successfully", Toast.LENGTH_LONG);
                            },
                            error -> {
                                if(error instanceof SQLiteConstraintException){
                                    message.setVisibility(View.VISIBLE);
                                }
                                else {
                                    Log.e("Error", "something else");

                                    Toast.makeText(getApplication().getApplicationContext(), "Something when wrong", Toast.LENGTH_LONG);
                                }
                            }
                    );
    }

    public void updateUser(int id, String first_name, String last_name, String mail, boolean gender, String phone_no, String dob) {
        userRepository.updateUser(id, first_name, last_name, mail, gender, phone_no, dob)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<User> getUserInformationByID(int id) {
        return userRepository.getUserInformationByID(id);
    }

    public void updatePassword(int id, String password) {
        String encrypted = ph.encoding(password);
        userRepository.changePassword(id, encrypted)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }


}
