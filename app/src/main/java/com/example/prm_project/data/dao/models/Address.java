package com.example.prm_project.data.dao.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_address", foreignKeys = {@ForeignKey(
        entity = User.class, parentColumns = "ID", childColumns = "user_id",
        onDelete = ForeignKey.CASCADE
)})
public class Address {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "user_id", index = true)
    private int userID;
    @ColumnInfo(name = "address")
    private String address;

    public Address() {
    }

    public Address(int ID, int userID, String address) {
        this.ID = ID;
        this.userID = userID;
        this.address = address;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
