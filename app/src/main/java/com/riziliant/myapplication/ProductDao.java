package com.riziliant.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM Product")
    List<Product> getAll();

    @Query("SELECT * FROM Product WHERE id IN (:userIds)")
    List<Product> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Product WHERE id LIKE :id  LIMIT 1")
    Product findById(int id);

    @Insert
    void insertAll(Product users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(Product users);

    @Query("UPDATE Product SET product_name = :product WHERE id =:id")
    void update(String product, int id);

    @Delete
    void delete(Product user);
}
