package com.inventory.shop.shopinventory;



public class PriceListClass{
    String productName = "", productSalePrice = "", productPurchasePrice = "", productCategory = "";
    PriceListClass(String pname, String psprice, String ppprice, String pcatgry){
        productName = pname;
        productSalePrice = psprice;
        productPurchasePrice = ppprice;
        productCategory = pcatgry;
    }
}
