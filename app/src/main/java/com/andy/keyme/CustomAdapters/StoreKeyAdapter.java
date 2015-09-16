package com.andy.keyme.CustomAdapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andy.keyme.R;

/**
 * Created by Andy on 9/10/2015.
 */
public class StoreKeyAdapter extends RecyclerView.Adapter<StoreKeyAdapter.ViewHolder> {
    private static final int TYPE_KEY= 0;
    private static final int TYPE_SCAN = 1;

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them
    private String mKeyNames[];
    private int mKeyImages[];
    private static int cardBackgroundColors[];

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;
        TextView textView;
        ImageView imageView;
        CardView cardView;
        int color;
        public ViewHolder(View itemView,int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created
            if(ViewType == TYPE_KEY) {
                cardView = (CardView) itemView.findViewById(R.id.cardview_store_key);
                textView = (TextView) itemView.findViewById(R.id.tv_store_key_name);
                imageView = (ImageView) itemView.findViewById(R.id.iv_store_key);
                Holderid = 0;                                               // setting holder id as 1 as the object being populated are of type item row
            } else if (ViewType == TYPE_SCAN ) {
                cardView = (CardView) itemView.findViewById(R.id.cardview_store_scan);
                textView = (TextView) itemView.findViewById(R.id.tv_store_scan_name);
                imageView = (ImageView) itemView.findViewById(R.id.iv_store_scan);
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            }
        }
    }

    public StoreKeyAdapter(String keyNames[],int keyImages[], int backgroundColors[]){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mKeyNames = keyNames;
        mKeyImages = keyImages;
        cardBackgroundColors = backgroundColors;
    }


    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder
    @Override
    public StoreKeyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_KEY) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_key,parent,false); //Inflating the layout
            ViewHolder vhKiosk = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhKiosk; // Returning the created object
        } else if (viewType == TYPE_SCAN) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_store_scan,parent,false); //Inflating the layout
            ViewHolder vhLogin = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhLogin; //returning the object created
        }
        return null;
    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(StoreKeyAdapter.ViewHolder holder, int position) {
        if(holder.Holderid == TYPE_KEY) {
            holder.textView.setText(mKeyNames[position]);
            holder.imageView.setImageResource(mKeyImages[position]);
            int color = holder.cardView.getContext().getResources().getColor(cardBackgroundColors[position]);
            holder.cardView.setCardBackgroundColor(color);
        } else {
            holder.textView.setText(mKeyNames[position]);
            holder.imageView.setImageResource(mKeyImages[position]);
            int color = holder.cardView.getContext().getResources().getColor(cardBackgroundColors[position]);
            holder.cardView.setCardBackgroundColor(color);
        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mKeyNames.length;
    }

    // With the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionKey(position)) {
            return TYPE_KEY;
        } else if (isPositionScan(position)) {
            return TYPE_SCAN;
        } else {
            return TYPE_KEY;
        }
    }

    private boolean isPositionKey(int position) {
        return position == 0;
    }
    private boolean isPositionScan(int position) {
        return position == 1;
    }
}
