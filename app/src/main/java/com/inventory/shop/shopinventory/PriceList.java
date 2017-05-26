package com.inventory.shop.shopinventory;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.inventory.shop.shopinventory.R.id.recyclerView;

public class PriceList extends AppCompatActivity {
    RecyclerView recyclerView;
    SQLiteDatabase db;
    String dbQueryCategory = "";
    public List<PriceListClass> priceListClassList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.priceListRv);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(llm);
        initializeData();
        initializeAdapter();
    }

    public void initializeData(){
        priceListClassList = new ArrayList<>();
        String pname = "", psprice = "", ppprice = "", pcatgry = "";
        Intent intent = getIntent();
        String ctgry = intent.getStringExtra("catgry");
        System.out.println(ctgry);
        Cursor crsr = db.rawQuery("SELECT * FROM productList WHERE category='"+ ctgry + "' order by productName ASC", null);
        crsr.moveToFirst();
        for(int i = 0; i < crsr.getCount(); i++){
            pname = crsr.getString(0);
            psprice = crsr.getString(1);
            ppprice = crsr.getString(2);
            pcatgry = crsr.getString(4);
            priceListClassList.add(new PriceListClass(pname, psprice, ppprice, pcatgry));
            crsr.moveToNext();
        }
    }

    public void initializeAdapter(){
        PriceListRecyclerViewAdapter padapter = new PriceListRecyclerViewAdapter(priceListClassList);
        recyclerView.setAdapter(padapter);
    }

}
