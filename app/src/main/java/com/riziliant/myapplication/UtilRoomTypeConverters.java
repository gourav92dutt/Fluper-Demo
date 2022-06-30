package com.riziliant.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilRoomTypeConverters {
    @TypeConverter
    public static byte[] BitMapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @TypeConverter
    public static Bitmap ByteArrayToBitMap(byte[] encodedString) {
        try {
            return BitmapFactory.decodeByteArray(encodedString, 0, encodedString.length);


        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @TypeConverter
    public String StringToStoresDictionary(StoresDictionary storesDictionary) {
        Gson gson = new Gson();
        return gson.toJson(storesDictionary);

    }

    @TypeConverter
    public StoresDictionary storesDictionaryToString(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, StoresDictionary.class);

    }

    @TypeConverter
    public static List<String> storedStringToMyObjects(String data) {
        Gson gson = new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String myObjectsToStoredString(List<String> myObjects) {
        Gson gson = new Gson();
        return gson.toJson(myObjects);
    }
}
