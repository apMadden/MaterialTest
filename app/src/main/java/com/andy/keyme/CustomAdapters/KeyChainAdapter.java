package com.andy.keyme.CustomAdapters;

import com.andy.keyme.R;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Andy on 9/5/2015.
 */
public class KeyChainAdapter extends RecyclerView.Adapter<KeyChainAdapter.ViewHolder> {
    private static final int TYPE_KIOSK= 0;
    private static final int TYPE_LOGIN = 1;
    private static final int TYPE_KEY = 2;

    private String mKeyNames[]; // String Array to store the passed titles Value from MainActivity.java
    private int mKeyImages[];       // Int Array to store the passed icons resource value from MainActivity.java
    private static int cardBackgroundColors[];
    private static int colorIterator = 0;

    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them
    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;
        TextView textView;
        ImageView imageView;
        CardView cardView;
        Button loginButton;
        public ViewHolder(View itemView,int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created
            if(ViewType == TYPE_KIOSK) {
                Holderid = 0;                                               // setting holder id as 1 as the object being populated are of type item row
            } else if (ViewType == TYPE_LOGIN ) {
                loginButton = (Button) itemView.findViewById(R.id.button_login);
                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            }  else {
                cardView = (CardView) itemView.findViewById(R.id.cardview_keychain_key);
                textView = (TextView) itemView.findViewById(R.id.tv_key_name);
                imageView = (ImageView) itemView.findViewById(R.id.iv_key);
                Holderid = 2;                                               // setting holder id as 1 as the object being populated are of type item row
            }
        }
    }

    public KeyChainAdapter(String keyNames[],int keyImages[], int backgroundColors[]){ // MyAdapter Constructor with titles and icons parameter
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
    public KeyChainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_KIOSK) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_find_kiosk,parent,false); //Inflating the layout
            ViewHolder vhKiosk = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhKiosk; // Returning the created object
        } else if (viewType == TYPE_LOGIN) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_login,parent,false); //Inflating the layout
            ViewHolder vhLogin = new ViewHolder(v,viewType); //Creating ViewHolder and passing the object of type view
            return vhLogin; //returning the object created
        } else if (viewType == TYPE_KEY){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_key, parent, false);
            ViewHolder vhKey = new ViewHolder(v, viewType);
            return vhKey;
        }
        return null;
    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(KeyChainAdapter.ViewHolder holder, int position) {
        if(holder.Holderid ==TYPE_KIOSK) {

        } else if(holder.Holderid == TYPE_LOGIN ) {

        } else {
            holder.textView.setText(mKeyNames[position-2]);
            holder.imageView.setImageResource(mKeyImages[position - 2]);
            int color = holder.cardView.getContext().getResources().getColor(cardBackgroundColors[position-2]);
            holder.cardView.setCardBackgroundColor(color);
        }
    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return mKeyNames.length+2; // the number of items in the list will be +1 the titles including the header view.
    }

    // With the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionKiosk(position)) {
            return TYPE_KIOSK;
        } else if (isPositionLogin(position)) {
            return TYPE_LOGIN;
        } else {
            return TYPE_KEY;
        }
    }

    private boolean isPositionKiosk(int position) {
        return position == 0;
    }
    private boolean isPositionLogin(int position) {
        return position == 1;
    }
}
