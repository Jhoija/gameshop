package com.example.gameshop;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<claseconstructor> mData;
    private LayoutInflater mInflater;
    private Context context;
    public ListAdapter(List<claseconstructor> itemList,Context context){
        this.mInflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemList;
    }
    @Override
    public int getItemCount(){
        return mData.size();
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.constructor,null);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder,final int position){
        holder.bindData(mData.get(position));
    }
    public void setItems(List<claseconstructor> items){
        mData = items;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameuser,namegame,saldo,precio;

        public ViewHolder(View itemView) {
            super(itemView);
            nameuser = itemView.findViewById(R.id.nameTextView);
            namegame = itemView.findViewById(R.id.cityTextView);
            saldo = itemView.findViewById(R.id.statusTextView);
            precio = itemView.findViewById(R.id.precioTextView);
        }
        void bindData(final claseconstructor item){
            nameuser.setText(item.getNameuser());
            namegame.setText(item.getNamegame());
            saldo.setText(item.getSaldo());
            precio.setText(item.getPrecio());
        }
    }
}