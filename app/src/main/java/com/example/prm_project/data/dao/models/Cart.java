package com.example.prm_project.data.dao.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart",
        foreignKeys = @ForeignKey(
                entity = User.class, parentColumns = "ID",
                childColumns = "user_id", onDelete = ForeignKey.CASCADE))
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "user_id", index = true)
    private int userID;

    public Cart() {
    }

    public Cart(int ID, int userID) {
        this.ID = ID;
        this.userID = userID;
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
}
