package com.riziliant.myapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Product.class}, version = 1)
@TypeConverters({UtilRoomTypeConverters.class})
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    public static final String DATABASE_NAME = "ProductDb";

    public static ProductDatabase instance;

    public static synchronized ProductDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ProductDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
        }

        return instance;
    }
}
