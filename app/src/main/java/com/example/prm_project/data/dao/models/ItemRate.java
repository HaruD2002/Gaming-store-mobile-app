package com.example.prm_project.data.dao.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "item_rating", foreignKeys = {
        @ForeignKey(entity = Item.class, parentColumns = "ID", childColumns = "Item_ID"),
        @ForeignKey(entity = User.class, parentColumns = "ID", childColumns = "User_ID")
})
public class ItemRate {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "Item_ID")@NotNull
    private int ItemID;

    @ColumnInfo(name = "User_ID")@NotNull
    private int UserID;

}
