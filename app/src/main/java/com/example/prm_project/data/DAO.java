package com.example.prm_project.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.prm_project.data.dao.AddressDAO;
import com.example.prm_project.data.dao.UserDAO;
import com.example.prm_project.data.dao.models.Address;
import com.example.prm_project.data.dao.models.User;


@Database(entities = {User.class, Address.class}, version = 1, exportSchema = false)
public abstract class DAO extends RoomDatabase {
    public static final String DB_NAME = "GamingStore";
    public abstract UserDAO userDAO();
    public abstract AddressDAO addressDAO();
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

    public static final int DB_VERSION = 1;
}
