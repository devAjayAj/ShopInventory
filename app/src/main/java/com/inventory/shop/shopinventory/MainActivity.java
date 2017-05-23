package com.inventory.shop.shopinventory;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        initializeData();
        initializeAdapter();
    }

    public List<CategoryClass> categoryClassList;
    RecyclerView recyclerView;

    public void initializeData() {
        String categoryFromDB = "";
        categoryClassList = new ArrayList<>();
        Cursor cursr = db.rawQuery("SELECT * FROM category", null);
        cursr.moveToFirst();
        for(int i = 0; i < cursr.getCount(); i++){
            categoryFromDB = cursr.getString(0);
            categoryClassList.add(new CategoryClass(categoryFromDB));
            cursr.moveToNext();
        }
    }

    public void initializeAdapter() {
        RecyclerVIewAdapter adapter = new RecyclerVIewAdapter(categoryClassList);
        recyclerView.setAdapter(adapter);
    }
}
