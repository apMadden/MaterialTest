package com.andy.keyme.StoreFragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.keyme.CustomAdapters.StoreAccessoryAdapter;
import com.andy.keyme.CustomAdapters.StoreKeyAdapter;
import com.andy.keyme.Model.Accessory;
import com.andy.keyme.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreAccessoriesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreAccessoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreAccessoriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_COLOR = "color";

    private ArrayList<Accessory> mAccessories;

    // TODO: Rename and change types of parameters
    private int mColor;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param color Parameter 1.
     * @return A new instance of fragment StoreAccessoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreAccessoriesFragment newInstance(int color) {
        StoreAccessoriesFragment fragment = new StoreAccessoriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    public StoreAccessoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateAccessories();
        if (getArguments() != null) {
            mColor = getArguments().getInt(ARG_COLOR);
        }
    }

    private void populateAccessories() {
        mAccessories = new ArrayList();
        Accessory screwPopLight = new Accessory();
        screwPopLight.name = "Screwpop Light";
        screwPopLight.cost = 7.99;
        screwPopLight.image = R.drawable.access_screwpoplight;
        Accessory leatherman = new Accessory();
        leatherman.name = "Leatherman Multi-Tool";
        leatherman.cost = 29.99;
        leatherman.image = R.drawable.accessory_leathermankeychainmultitool;
        Accessory caribiner = new Accessory();
        caribiner.name = "The KeyMe Caribiner";
        caribiner.cost = 3.99;
        caribiner.image = R.drawable.keymecaribiner;
        Accessory nomadkey = new Accessory();
        nomadkey.name = "Nomadkey Micro USB";
        nomadkey.cost = 20.0;
        nomadkey.image = R.drawable.nomadkeymicrousb;
        Accessory keyhead = new Accessory();
        keyhead.name = "KeyMe Keyhead";
        keyhead.cost = 2.0;
        keyhead.image = R.drawable.texturedkeymekeyhead;
        mAccessories.add(screwPopLight);
        mAccessories.add(leatherman);
        mAccessories.add(caribiner);
        mAccessories.add(nomadkey);
        mAccessories.add(keyhead);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_accessories, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_store_accessories);
        recyclerView.setHasFixedSize(true);
        StoreAccessoryAdapter mStoreAccessoryAdapter = new StoreAccessoryAdapter(mAccessories);
        recyclerView.setAdapter(mStoreAccessoryAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getBaseContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
