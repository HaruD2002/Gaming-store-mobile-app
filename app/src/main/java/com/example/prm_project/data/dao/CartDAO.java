package com.example.prm_project.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface CartDAO {
    @Insert
    Completable createCardForUser(int id);

    @Delete
    Completable deleteCardForUser(int id);
}
