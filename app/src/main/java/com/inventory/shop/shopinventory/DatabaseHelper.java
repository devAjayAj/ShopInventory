package com.inventory.shop.shopinventory;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String createQueryProductList = "CREATE TABLE productList(productName TEXT, productSalePrice TEXT, productPurchasePrice TEXT, productAlternativeNames TEXT, category TEXT);";
    private static final String createCategoryQuery = "CREATE TABLE category(category TEXT primary key)";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inventory.db";
    Context c;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        c = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createQueryProductList);
        db.execSQL(createCategoryQuery);
        try {
            AssetManager am = c.getAssets();
            InputStream is = am.open("categoryList.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document doc = dbBuilder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("categoryOfProduct");
            for(int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    String ctgry = "'" + element.getAttribute("ctgry") + "'";
                    db.execSQL("INSERT into category(category) values("+ctgry+")");
                }
            }
            is = am.open("data.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dbBuilder = dbFactory.newDocumentBuilder();
            doc = dbBuilder.parse(is);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName("item");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String pname = "'" + element.getAttribute("name") + "',";
                    String psprice = "'" + element.getElementsByTagName("salePrice").item(0).getTextContent() + "',";
                    String ppprice = "'" + element.getElementsByTagName("purchasePrice").item(0).getTextContent() + "',";
                    String ctgry = "'" + element.getElementsByTagName("category").item(0).getTextContent() + "'";
                    db.execSQL("INSERT into productList(productName, productSalePrice, productPurchasePrice, category) values(" + pname + psprice + ppprice + ctgry + ")");
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
