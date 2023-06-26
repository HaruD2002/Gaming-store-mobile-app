package com.example.prm_project.data.dao.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item", foreignKeys = {
    @ForeignKey(entity=Shop.class, parentColumns="ID",
    childColumns="shop_id", onDelete= ForeignKey.CASCADE)
})
public class Item  {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "item_name")
    private String ItemName;
    @ColumnInfo(name = "item_description")
    private String ItemDescription;
    @ColumnInfo(name = "shop_id", index = true)
    private int ShopID;
    @ColumnInfo(name = "item_price")
    private int PricePerItem; //in vnd
    @ColumnInfo(name = "quantity")
    private int Quantity;
    @ColumnInfo(name = "created_dt")
    private String Created_dt;

    public Item() {
    }

    public Item(int ID, String itemName, String itemDescription, int shopID, int pricePerItem, int quantity, String created_dt) {
        this.ID = ID;
        ItemName = itemName;
        ItemDescription = itemDescription;
        ShopID = shopID;
        PricePerItem = pricePerItem;
        Quantity = quantity;
        Created_dt = created_dt;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemDescription() {
        return ItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        ItemDescription = itemDescription;
    }

    public int getShopID() {
        return ShopID;
    }

    public void setShopID(int shopID) {
        ShopID = shopID;
    }

    public int getPricePerItem() {
        return PricePerItem;
    }

    public void setPricePerItem(int pricePerItem) {
        PricePerItem = pricePerItem;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getCreated_dt() {
        return Created_dt;
    }

    public void setCreated_dt(String created_dt) {
        Created_dt = created_dt;
    }
}