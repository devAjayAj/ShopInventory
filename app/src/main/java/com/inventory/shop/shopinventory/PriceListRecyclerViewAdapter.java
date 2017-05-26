package com.inventory.shop.shopinventory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aj on 23/5/17.
 */

public class PriceListRecyclerViewAdapter extends RecyclerView.Adapter<PriceListRecyclerViewAdapter.PriceListClassViewHolder>{

    List<PriceListClass> priceListClassAdapterList;
    PriceListRecyclerViewAdapter(List<PriceListClass> priceListClassList){
        priceListClassAdapterList = priceListClassList;
    }

    public static class PriceListClassViewHolder extends RecyclerView.ViewHolder {
        CardView priceListCv;
        TextView productNameTv, productSalepriceTV, productPurchasePriceTv;

        public PriceListClassViewHolder(View itemView) {
            super(itemView);
            priceListCv = (CardView)itemView.findViewById(R.id.priceListCv);
            productNameTv = (TextView)itemView.findViewById(R.id.productNameTv);
            productPurchasePriceTv = (TextView)itemView.findViewById((R.id.productPurchasePriceTv));
            productSalepriceTV = (TextView)itemView.findViewById(R.id.productSalePriceTv);
//            productCategoryTv = (TextView)itemView.findViewById(R.id.productCategoryTv);
        }
    }
    @Override
    public PriceListRecyclerViewAdapter.PriceListClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_list_item, parent, false);
        PriceListClassViewHolder pvh = new PriceListClassViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PriceListRecyclerViewAdapter.PriceListClassViewHolder holder, int position) {
        holder.productNameTv.setText(priceListClassAdapterList.get(position).productName);
        holder.productPurchasePriceTv.setText("P:" + priceListClassAdapterList.get(position).productPurchasePrice);
        holder.productSalepriceTV.setText("Rs:" + priceListClassAdapterList.get(position).productSalePrice);
//        holder.productCategoryTv.setText(priceListClassAdapterList.get(position).productCategory);
    }

    @Override
    public int getItemCount() {
        return priceListClassAdapterList.size();
    }
}
