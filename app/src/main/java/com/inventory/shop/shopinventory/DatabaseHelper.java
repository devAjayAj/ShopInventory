package com.inventory.shop.shopinventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String createQueryProductList = "CREATE TABLE productList(productName TEXT primary key, productSalePrice TEXT, productPurchasePrice TEXT, productImage TEXT, productAlternativeNames TEXT, category TEXT);";
    private static final String createCategoryQuery = "CREATE TABLE category(category TEXT primary key)";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inventory.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
            db.execSQL(createQueryProductList);
            db.execSQL(createCategoryQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
