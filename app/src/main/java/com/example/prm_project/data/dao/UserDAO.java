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

    @Query("Insert into User(username, first_name, last_name," +
            "password, gender, phone_number, DOB, created_dt) " +
            "values(:username, :first_name, :last_name," +
            ":password, :gender, :phoneNumber, :DOB, :CreatedDt)")
    Completable CreateUser(String username, String first_name, String last_name,
                           String password, boolean gender, String phoneNumber,
                           String DOB, String CreatedDt);
}
