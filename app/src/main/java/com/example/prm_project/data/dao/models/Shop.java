package com.example.prm_project.data.dao.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Shop", foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "ID",
        childColumns = "shop_owner", onDelete = ForeignKey.CASCADE)
})
public class Shop {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "shop_name")
    private String ShopName;
    @ColumnInfo(name = "created_dt", defaultValue = "CURRENT_TIMESTAMP")
    private String Created_dt;
    @ColumnInfo(name = "shop_owner", index = true)
    private int Shop_owner;
    @ColumnInfo(name = "online_status", defaultValue = "false")
    private boolean OnlineStatus;

    public Shop() {
    }

    public Shop(int ID, String shopName, String created_dt, int shop_owner, boolean onlineStatus) {
        this.ID = ID;
        ShopName = shopName;
        Created_dt = created_dt;
        Shop_owner = shop_owner;
        OnlineStatus = onlineStatus;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getCreated_dt() {
        return Created_dt;
    }

    public void setCreated_dt(String created_dt) {
        Created_dt = created_dt;
    }

    public int getShop_owner() {
        return Shop_owner;
    }

    public void setShop_owner(int shop_owner) {
        Shop_owner = shop_owner;
    }

    public boolean isOnlineStatus() {
        return OnlineStatus;
    }

    public void setOnlineStatus(boolean onlineStatus) {
        OnlineStatus = onlineStatus;
    }
}
