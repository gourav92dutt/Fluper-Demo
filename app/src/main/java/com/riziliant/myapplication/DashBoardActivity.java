package com.riziliant.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoardActivity extends AppCompatActivity {


    Button btnShowProduct, btnGetProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        btnGetProduct = findViewById(R.id.btn_get_product);
        btnShowProduct = findViewById(R.id.btn_show_product);

        btnShowProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, DisplayProductActivity.class);
                startActivity(intent);
            }
        });

        btnGetProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

}