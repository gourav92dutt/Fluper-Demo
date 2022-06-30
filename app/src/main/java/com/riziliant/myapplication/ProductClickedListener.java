package com.riziliant.myapplication;

public interface ProductClickedListener {

    void onClicked();
    void onItemDelete(Product product);
    void onItemUpdate(Product product);
}
