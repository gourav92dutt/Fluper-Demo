package com.riziliant.myapplication;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Product   {
    public Product(String productName, String productDescription,
                   String regularPrice, String salePrice, StoresDictionary storesDictionary,
                   List<String> colors, Bitmap image, Bitmap imageThumb) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.regularPrice = regularPrice;
        this.salePrice = salePrice;
        this.storesDictionary = storesDictionary;
        this.colors = colors;
        this.image = image;
        this.imageThumb = imageThumb;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "product_description")
    private String productDescription;

    @ColumnInfo(name = "regular_price")
    private String regularPrice;

    @ColumnInfo(name = "sale_price")
    private String salePrice;

    private StoresDictionary storesDictionary;

    private List<String> colors;
    private Bitmap image;
    private Bitmap imageThumb;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }



    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(Bitmap imageThumb) {
        this.imageThumb = imageThumb;
    }

    public StoresDictionary getStoresDictionary() {
        return storesDictionary;
    }

    public void setStoresDictionary(StoresDictionary storesDictionary) {
        this.storesDictionary = storesDictionary;
    }
}
