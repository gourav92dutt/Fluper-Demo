package com.riziliant.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Bitmap image, thumb;
    private static final int PHOTO_PICKER_REQUEST_CODE = 123;
    private EditText etPrdName, etPrdRegularPrice, etPrdSalePrice, etPrdDescription, etProductLikeCount;
    private RatingBar ratingBar;
    public static final String PRODUCT_DATA = "PRODUCT_DATA";
    public static final int create = 1;
    public static final int update = 2;
    private int pageTypeValue = create,selectedId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etPrdName = findViewById(R.id.ed_product_name);
        etPrdRegularPrice = findViewById(R.id.ed_product_regular_price);
        etPrdSalePrice = findViewById(R.id.ed_product_sale_price);
        etPrdDescription = findViewById(R.id.ed_product_description);
        etProductLikeCount = findViewById(R.id.ed_product_likes);
        ratingBar = findViewById(R.id.rat_bar);
        getBundleData();
        findViewById(R.id.txt_pick_Image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, PHOTO_PICKER_REQUEST_CODE);
            }
        });
        findViewById(R.id.txt_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid()) {
                    switch (pageTypeValue) {
                        case create:
                            ProductDatabase.getInstance(MainActivity.this).productDao().insertAll(new Product(etPrdName.getText().toString(), etPrdDescription.getText().toString(),
                                    etPrdRegularPrice.getText().toString(), etPrdSalePrice.getText().toString(), new StoresDictionary("" + ratingBar.getRating(),
                                    etProductLikeCount.getText().toString()), getProductColorsArrayList(), image, thumb));
                            Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_LONG).show();
                            break;

                        case update:
                            ProductDatabase.getInstance(MainActivity.this).productDao().update(etPrdName.getText().toString(),selectedId);
                            Toast.makeText(MainActivity.this, "Item updated", Toast.LENGTH_LONG).show();
                            break;
                    }

                    resetView();

                }

            }
        });
    }


    private void getBundleData() {
        if(getIntent().getIntExtra(PRODUCT_DATA,0)!=0)
        {
            pageTypeValue=update;

            Product product=  ProductDatabase.getInstance(MainActivity.this).productDao().findById(getIntent().getIntExtra(PRODUCT_DATA,0));
            selectedId=product.getId();
            etPrdName.setText(product.getProductName());
            etPrdRegularPrice.setText(product.getRegularPrice());
            etPrdSalePrice.setText(product.getSalePrice());
            etPrdDescription.setText(product.getProductDescription());
            etProductLikeCount.setText(product.getStoresDictionary().getLike());
            ratingBar.setRating(Float.parseFloat(product.getStoresDictionary().getRating()));
            image = product.getImage();
            thumb = product.getImageThumb();
            ((CheckBox) findViewById(R.id.cb_green)).setChecked(true);

        }
    }

    private void resetView() {
        etPrdName.setText("");
        etPrdRegularPrice.setText("");
        etPrdSalePrice.setText("");
        etPrdDescription.setText("");
        etProductLikeCount.setText("");
        ratingBar.setRating(0);
        image = null;
        thumb = null;

    }

    private boolean isValid() {
        getThumb();
        if (etPrdName.getText().toString().length() == 0) {
            etPrdName.requestFocus();
            etPrdName.setError("Fill");
            return false;
        } else if (etPrdDescription.getText().toString().length() == 0) {
            etPrdDescription.requestFocus();
            etPrdDescription.setError("Fill");
            return false;
        } else if (etPrdRegularPrice.getText().toString().length() == 0) {
            etPrdRegularPrice.requestFocus();
            etPrdRegularPrice.setError("Fill");
            return false;
        } else if (etPrdSalePrice.getText().toString().length() == 0) {
            etPrdSalePrice.requestFocus();
            etPrdSalePrice.setError("Fill");
            return false;
        } else if (etProductLikeCount.getText().toString().length() == 0) {
            etProductLikeCount.requestFocus();
            etProductLikeCount.setError("Fill");
            return false;
        } else if (ratingBar.getRating() == 0) {
            Toast.makeText(this, "Rate me", Toast.LENGTH_LONG).show();
            return false;
        } else if (image == null) {
            Toast.makeText(this, "select Image", Toast.LENGTH_LONG).show();
            return false;
        } else if (getProductColorsArrayList().size() == 0) {
            Toast.makeText(this, "Add Color", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private List<String> getProductColorsArrayList() {

        List<String> colors = new ArrayList<>();

        if (((CheckBox) findViewById(R.id.cb_green)).isChecked()) {
            colors.add((((CheckBox) findViewById(R.id.cb_green)).getText().toString()));

        }
        if (((CheckBox) findViewById(R.id.cb_red)).isChecked()) {
            colors.add((((CheckBox) findViewById(R.id.cb_red)).getText().toString()));

        }
        if (((CheckBox) findViewById(R.id.cb_yellow)).isChecked()) {
            colors.add((((CheckBox) findViewById(R.id.cb_yellow)).getText().toString()));

        }
        if (((CheckBox) findViewById(R.id.cb_pink)).isChecked()) {
            colors.add((((CheckBox) findViewById(R.id.cb_pink)).getText().toString()));

        }
        if (((CheckBox) findViewById(R.id.cb_black)).isChecked()) {
            colors.add((((CheckBox) findViewById(R.id.cb_black)).getText().toString()));

        }
        if (((CheckBox) findViewById(R.id.cb_blue)).isChecked()) {
            colors.add((((CheckBox) findViewById(R.id.cb_blue)).getText().toString()));

        }


        return colors;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            ((ImageView) findViewById(R.id.img_picked_image)).setImageURI(data.getData());
            image = ((BitmapDrawable) ((ImageView) findViewById(R.id.img_picked_image)).getDrawable()).getBitmap();

        }
    }

    public void getThumb() {
        try {

            final int THUMBNAIL_SIZE = 64;

            thumb = Bitmap.createScaledBitmap(image, THUMBNAIL_SIZE, THUMBNAIL_SIZE, false);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            thumb.compress(Bitmap.CompressFormat.JPEG, 100, baos);


        } catch (Exception ex) {

        }
    }
}
