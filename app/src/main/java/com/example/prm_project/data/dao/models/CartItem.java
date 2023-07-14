package com.example.prm_project.data.dao.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "Cart_Item", foreignKeys = {
        @ForeignKey(entity = Cart.class, parentColumns = "ID",
        childColumns = "cart_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Item.class, parentColumns = "ID",
        childColumns = "item_id", onDelete = ForeignKey.CASCADE)
        })
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "cart_id", index = true) @NotNull
    private int cardID;
    @ColumnInfo(name = "item_id", index = true) @NotNull
    private int itemID;
    @ColumnInfo(name = "status") @NotNull
    private boolean status;
    @ColumnInfo(name = "created_dt")
    private String created_dt;
    @ColumnInfo(name = "updated_dt")
    private String updated_dt;
    @ColumnInfo(name = "quantity")
    private String quantity;

    public CartItem() {
    }

    public CartItem(int ID, int cardID, int itemID, boolean status, String created_dt, String updated_dt, String quantity) {
        this.ID = ID;
        this.cardID = cardID;
        this.itemID = itemID;
        this.status = status;
        this.created_dt = created_dt;
        this.updated_dt = updated_dt;
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public String getUpdated_dt() {
        return updated_dt;
    }

    public void setUpdated_dt(String updated_dt) {
        this.updated_dt = updated_dt;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
