package com.example.prm_project.data.repository;

import androidx.lifecycle.LiveData;

import com.example.prm_project.data.dao.AddressDAO;
import com.example.prm_project.data.dao.models.Address;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class AddressRepository {
    private final AddressDAO addressDAO;

    public AddressRepository(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public LiveData<List<Address>> getAllAddressOfUser(int userID) {
        return addressDAO.getAllAddressOfUser(userID);
    }

    public Completable insert(Address address) {
        return addressDAO.insert(address);
    }

    public Completable delete(Address address) {
        return addressDAO.delete(address);
    }

    public Completable update(Address address) {
        return addressDAO.update(address);
    }

    public LiveData<Address> getAddressById(int addressID) {
        return addressDAO.getAddressById(addressID);
    }

}
