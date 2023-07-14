package com.example.prm_project.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.prm_project.data.converter.BitmapConverter;
import com.example.prm_project.data.dao.CartDAO;
import com.example.prm_project.data.dao.CartItemDAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.data.dao.models.Cart;
import com.example.prm_project.data.dao.models.CartItem;
import com.example.prm_project.data.dao.models.Category;
import com.example.prm_project.data.dao.models.Item;
import com.example.prm_project.data.dao.models.ItemCategory;
import com.example.prm_project.data.dao.models.ItemImage;
import com.example.prm_project.data.dao.models.Shop;
import com.example.prm_project.data.dao.models.User;


@Database(entities = {
        User.class, Cart.class, CartItem.class, Shop.class, Item.class,
        Address.class, Category.class, ItemCategory.class, ItemImage.class
}, version = 1, exportSchema = true)
@TypeConverters(BitmapConverter.class)
public abstract class DAO extends RoomDatabase {
    public static final String DB_NAME = "GamingStore";
    private static DAO INSTANCE;

    public DAO() {}

    public static synchronized DAO getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, DAO.class, DB_NAME).addCallback(
                    new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    public abstract UserDAO userDAO();
    public abstract CartItemDAO cartItemDAO();
    public abstract CartDAO cartDAO();
}
