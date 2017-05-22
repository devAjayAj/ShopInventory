package com.inventory.shop.shopinventory;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aj on 22/5/17.
 */

public class RecyclerVIewAdapter extends RecyclerView.Adapter<RecyclerVIewAdapter.CategoryClassViewHolder>{

    List<CategoryClass> categoryClassAdapterList;
    RecyclerVIewAdapter(List<CategoryClass> categoryClassAdapterList){
        this.categoryClassAdapterList = categoryClassAdapterList;
    }

    public static class CategoryClassViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView tv;
        public CategoryClassViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public CategoryClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        CategoryClassViewHolder cvh = new CategoryClassViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CategoryClassViewHolder holder, int position) {
        holder.tv.setText(categoryClassAdapterList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return categoryClassAdapterList.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView rcv){
        super.onAttachedToRecyclerView(rcv);
    }

}
