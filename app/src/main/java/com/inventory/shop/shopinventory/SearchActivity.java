package com.inventory.shop.shopinventory;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SQLiteDatabase db;
    String name;
    public List<PriceListClass> priceListClassList;
    Boolean nameIsDuplicate;
    Set<String> prodName = new LinkedHashSet<>();
    Set<String> prodPurP = new LinkedHashSet<>();
    Set<String> prodSaleP = new LinkedHashSet<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        db = dbHelper.getWritableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.priceListRv);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(llm);
        initializeData();
        initializeAdapter();
    }

    public void initializeData() {
        priceListClassList = new ArrayList<>();
        final String[] pname = {""};
        final String[] psprice = {""};
        final String[] ppprice = {""};
        final String[] pcatgry = {""};
//        Intent intent = getIntent();
//        String ctgry = intent.getStringExtra("catgry");
//        System.out.println(ctgry);
        name = "";
        final EditText input = (EditText) findViewById(R.id.searchInput);
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                name = String.valueOf(input.getText()).trim();
                if (!name.isEmpty()) {
                    String query = "SELECT * FROM productList WHERE productName LIKE '%" + name + "%' order by productName ASC";
                    Cursor crsr = db.rawQuery(query, null);
                    crsr.moveToFirst();
                    priceListClassList = new ArrayList<>();
                    for (int i = 0; i < crsr.getCount(); i++) {
                        pname[0] = crsr.getString(0);
                        psprice[0] = crsr.getString(1);
                        ppprice[0] = crsr.getString(2);
                        pcatgry[0] = crsr.getString(4);
                        priceListClassList.add(new PriceListClass(pname[0], psprice[0], ppprice[0], pcatgry[0]));
                        crsr.moveToNext();
                    }
                    if (name.length() > 1) {
                        query = "SELECT * FROM productList WHERE productName LIKE '%" + name.charAt(0) + "%'";
                        for (int i = 1; i < (name.length()); i++) {
                            query = query + " or productName LIKE '%" + name.charAt(i) + "%'";
                        }
                        crsr = db.rawQuery(query, null);
                        crsr.moveToFirst();
                        for (int i = 0; i < crsr.getCount(); i++) {
                            pname[0] = crsr.getString(0);
                            nameIsDuplicate = false;
                            for (int j = 0; j < priceListClassList.size(); j++) {
                                if (pname[0].equals(priceListClassList.get(j).productName)) {
                                    nameIsDuplicate = true;
                                    break;
                                }
                            }
                            if (!nameIsDuplicate) {
                                psprice[0] = crsr.getString(1);
                                ppprice[0] = crsr.getString(2);
                                pcatgry[0] = crsr.getString(4);
                                priceListClassList.add(new PriceListClass(pname[0], psprice[0], ppprice[0], pcatgry[0]));
                            }
                            crsr.moveToNext();
                        }
                    }
                    initializeAdapter();
                } else {
                    priceListClassList = new ArrayList<>();
                    initializeAdapter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void initializeAdapter() {
        PriceListRecyclerViewAdapter padapter = new PriceListRecyclerViewAdapter(priceListClassList);
        recyclerView.setAdapter(padapter);
    }
}
