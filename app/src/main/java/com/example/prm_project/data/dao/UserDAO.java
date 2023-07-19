package com.example.prm_project.data.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.prm_project.data.dao.models.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface UserDAO {
    @Insert
    Completable insert(User user);

    @Update
    Completable update(User user);

    @Delete
    Completable delete(User user);

    @Query("select * from User")
    LiveData<List<User>> getAllUser();

    @Query("Select * from User where User.ID = :id")
    LiveData<User> getUserById(int id);

    @Query("Select * from User where username=:username")
    LiveData<User> loginUser(String username);

    @Query("Insert into User(username ,password, phone_number, mail, created_dt) " +
            "values(:username,:password,:mail ,:phoneNumber, :CreatedDt)")
    Completable CreateUser(String username, String password,String mail, String phoneNumber, String CreatedDt);

    @Query("select * from User where username=:username LIMIT 1")
    LiveData<User> getUserName(String username);

    @Query("Update User set online_status = :status where ID=:id")
    Completable updateOnlineStatus(int id, int status);

    @Query("Update User set first_name=:first_name, last_name =:last_name, mail=:mail, gender=:gender, phone_number=:phone_no, DOB=:dob where ID = :id ")
    Completable updateUser(int id, String first_name, String last_name, String mail, boolean gender, String phone_no, String dob);

    @Query("Update User set password = :password where ID=:id")
    Completable updatePassword(int id, String password);
}
