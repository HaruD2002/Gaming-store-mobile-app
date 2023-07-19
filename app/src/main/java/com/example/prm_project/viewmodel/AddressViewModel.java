package com.example.prm_project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm_project.data.dao.AddressDAO;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.data.dao.models.User;
import com.example.prm_project.data.repository.AddressRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddressViewModel extends AndroidViewModel {

    private AddressRepository addressRepository;
    private LiveData<List<Address>> addressList;
    private AddressDAO addressDAO;

    public AddressViewModel(Application application) {
        super(application);
        addressRepository = new AddressRepository(addressDAO);
    }

    public void insert(Address address){
        addressRepository.insert(address).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void update(Address address) {
        addressRepository.update(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public void delete(Address address) {
        addressRepository.delete(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    public LiveData<List<Address>> getAddressListOfUser(int userID){
        return addressRepository.getAllAddressOfUser(userID);
    }

    public LiveData<Address> getAddressById(int id) {
        return addressRepository.getAddressById(id);
    }

}
