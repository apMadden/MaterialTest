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
import android.widget.FrameLayout;

import com.andy.keyme.CustomAdapters.KeyChainAdapter;
import com.andy.keyme.CustomAdapters.StoreKeyAdapter;
import com.andy.keyme.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StoreKeysFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StoreKeysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreKeysFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_COLOR = "color";

    // TODO: Rename and change types of parameters
    private int mColor;

    private OnFragmentInteractionListener mListener;
    String KEY_NAMES[] = {"Deka's house", "Home", "Work", "test", "test 9", "Front Door", "back door"};
    int KEY_IMAGES[] = {R.drawable.key_flat_store, R.drawable.key_flat_store, R.drawable.key_flat_store, R.drawable.key_flat_store, R.drawable.key_flat_store, R.drawable.key_flat_store, R.drawable.key_flat_store};
    int KEY_CARD_COLORS[] = {R.color.color_card_one, R.color.color_card_two, R.color.color_card_three, R.color.color_card_four, R.color.color_card_one, R.color.color_card_two, R.color.color_card_three};


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param color Parameter 1.
     * @return A new instance of fragment StoreKeysFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreKeysFragment newInstance(int color) {
        StoreKeysFragment fragment = new StoreKeysFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }
    public StoreKeysFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColor = getArguments().getInt(ARG_COLOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store_keys, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_store_keys);
        recyclerView.setHasFixedSize(true);
        StoreKeyAdapter mStoreKeyAdapter = new StoreKeyAdapter(KEY_NAMES, KEY_IMAGES, KEY_CARD_COLORS);
        recyclerView.setAdapter(mStoreKeyAdapter);

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
