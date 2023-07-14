package com.example.prm_project.data.dao;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.prm_project.data.dao.models.Address;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface AddressDAO {
    @Insert
    Completable insert(Address address);

    @Update
    Completable update(Address address);

    @Delete
    Completable delete(Address address);

    @Query("select * from user_address where user_address.user_id = :id")
    LiveData<List<Address>> getAllAddressOfUser(int id);

    @Query("Select * from user_address where user_address.ID = :id")
    LiveData<Address> getAddressById(int id);
}
