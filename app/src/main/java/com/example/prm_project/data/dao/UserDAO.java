package com.example.prm_project.data.dao;

import androidx.lifecycle.LiveData;
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

    @Query("Select * from User where User.username=:username and User.password=:password")
    LiveData<User> loginUser(String username, String password);

    @Query("Insert into User(username ,password, phone_number, mail, created_dt) " +
            "values(:username,:password,:mail ,:phoneNumber, :CreatedDt)")
    Completable CreateUser(String username, String password,String mail, String phoneNumber, String CreatedDt);
}
