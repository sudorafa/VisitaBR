package com.example.orafa.visitabr.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orafa.visitabr.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCityWillFragment extends Fragment {


    public ListCityWillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_city_will, container, false);
    }

}
