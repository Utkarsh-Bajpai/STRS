package com.example.android.strs.Tab_Layout;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.example.android.strs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllTransportsAvailable extends Fragment {


    public AllTransportsAvailable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_transports_available, container, false);
    }

}
