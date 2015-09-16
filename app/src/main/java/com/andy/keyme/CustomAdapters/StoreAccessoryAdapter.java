package com.andy.keyme.CustomAdapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.keyme.Model.Accessory;
import com.andy.keyme.R;

import java.util.ArrayList;

/**
 * Created by Andy on 9/10/2015.
 */
public class StoreAccessoryAdapter extends RecyclerView.Adapter<StoreAccessoryAdapter.ViewHolder>{
    private static final int TYPE_ACCESSORY = 0;

    int mAccessoryImages[] = {R.drawable.access_screwpoplight,R.drawable.accessory_leathermankeychainmultitool, R.drawable.keymecaribiner, R.drawable.nomadkeymicrousb, R.drawable.texturedkeymekeyhead};

    private ArrayList<Accessory> mAccessories;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;
        TextView tvAccessoryName;
        TextView tvAccessoryPrice;
        ImageView imageView;
        CardView cardView;
        int color;
        public ViewHolder(View itemView,int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created
            if(ViewType == TYPE_ACCESSORY) {
                cardView = (CardView) itemView.findViewById(R.id.cardview_store_accessory);
                tvAccessoryName = (TextView) itemView.findViewById(R.id.tv_store_accessory_name);
                tvAccessoryPrice = (TextView) itemView.findViewById(R.id.tv_store_accessory_price);
                imageView = (ImageView) itemView.findViewById(R.id.iv_store_accessory);
                Holderid = 0;                                               // setting holder id as 1 as the object being populated are of type item row
            }
        }
    }


    public StoreAccessoryAdapter(ArrayList<Accessory> accessories){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mAccessories = accessories;
    }

    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder
    @Override
    public StoreAccessoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ACCESSORY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_accessory,parent,false); //Inflating the layout
            ViewHolder vhAccessory = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhAccessory; // Returning the created object
        }
        return null;
    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(StoreAccessoryAdapter.ViewHolder holder, int position) {
        if(holder.Holderid == TYPE_ACCESSORY) {
            holder.tvAccessoryName.setText(mAccessories.get(position).name);
            holder.tvAccessoryPrice.setText(Double.toString(mAccessories.get(position).cost));
            holder.imageView.setImageResource(mAccessories.get(position).image);
        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mAccessories.size();
    }

    // With the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        return TYPE_ACCESSORY;
    }
}
