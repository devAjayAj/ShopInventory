package com.inventory.shop.shopinventory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    public List<CategoryClass> categoryClassList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        initializeData();
        initializeAdapter();
    }
    public void initializeData(){
        categoryClassList = new ArrayList<>();
        categoryClassList.add(new CategoryClass("1"));
        categoryClassList.add(new CategoryClass("2"));
        categoryClassList.add(new CategoryClass("3"));
        categoryClassList.add(new CategoryClass("4"));
        categoryClassList.add(new CategoryClass("5"));
        categoryClassList.add(new CategoryClass("1"));
        categoryClassList.add(new CategoryClass("2"));
        categoryClassList.add(new CategoryClass("3"));
        categoryClassList.add(new CategoryClass("4"));
        categoryClassList.add(new CategoryClass("5"));
        categoryClassList.add(new CategoryClass("1"));
        categoryClassList.add(new CategoryClass("2"));
        categoryClassList.add(new CategoryClass("3"));
        categoryClassList.add(new CategoryClass("4"));
        categoryClassList.add(new CategoryClass("5"));
    }
    public void initializeAdapter(){
        RecyclerVIewAdapter adapter = new RecyclerVIewAdapter(categoryClassList);
        recyclerView.setAdapter(adapter);
    }
}
