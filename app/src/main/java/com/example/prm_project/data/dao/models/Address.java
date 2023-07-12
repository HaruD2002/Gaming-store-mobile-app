package com.example.prm_project.data.dao.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "Address")
public class Address {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @NotNull
    private int user_id;
    private String address;
    private int address_type;

    public Address() {
    }

    public Address(int ID, @NotNull int user_id, String address, int address_type) {
        this.ID = ID;
        this.user_id = user_id;
        this.address = address;
        this.address_type = address_type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddress_type() {
        return address_type;
    }

    public void setAddress_type(int address_type) {
        this.address_type = address_type;
    }
}
