package com.example.prm_project.data.dao.models;


import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Item_image ", foreignKeys = {@ForeignKey(entity = Item.class,
 parentColumns = "ID", childColumns = "item_id", onDelete = ForeignKey.CASCADE)})
public class ItemImage {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "item_id", index = true)
    private int ImageID;
    @ColumnInfo(name = "image")
    private Bitmap image;

    public ItemImage() {
    }

    public ItemImage(int ID, int imageID, Bitmap image) {
        this.ID = ID;
        ImageID = imageID;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

