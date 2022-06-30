package com.riziliant.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

public class DisplayProductActivity extends AppCompatActivity {

    private ProductClickedListener productClickedListener;
    private ProductListAdapter productListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);
        List<Product> products = ProductDatabase.getInstance(DisplayProductActivity.this).productDao().getAll();
        RecyclerView productRecyclerview = findViewById(R.id.rv_product_list);

        productClickedListener = new ProductClickedListener() {
            @Override
            public void onClicked() {

            }

            @Override
            public void onItemDelete(Product product) {
                ProductDatabase.getInstance(DisplayProductActivity.this).productDao().delete(product);
                 productListAdapter.updateData(ProductDatabase.getInstance(DisplayProductActivity.this).productDao().getAll());
            }

            @Override
            public void onItemUpdate(Product product) {
                Intent intent =new Intent(DisplayProductActivity.this,MainActivity.class);
                intent.putExtra(MainActivity.PRODUCT_DATA,  product.getId());
                startActivity(intent);
            }
        };

        productListAdapter = new ProductListAdapter(products, productClickedListener);
        productRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerview.setAdapter(productListAdapter);

    }


}