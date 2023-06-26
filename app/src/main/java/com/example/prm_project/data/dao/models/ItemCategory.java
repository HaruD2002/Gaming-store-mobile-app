package com.example.prm_project.data.dao.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item_Category", foreignKeys = {
        @ForeignKey(entity = Category.class, parentColumns = "ID",
        childColumns = "category_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Item.class, parentColumns = "ID",
        childColumns = "item_id", onDelete = ForeignKey.CASCADE)
})
public class ItemCategory {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "item_id", index = true)
    private String itemID;
    @ColumnInfo(name = "category_id", index = true)
    private String categoryID;

    public ItemCategory() {
    }

    public ItemCategory(int ID, String itemID, String categoryID) {
        this.ID = ID;
        this.itemID = itemID;
        this.categoryID = categoryID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}
