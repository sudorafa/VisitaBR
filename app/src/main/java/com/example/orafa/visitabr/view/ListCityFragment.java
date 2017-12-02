package com.example.orafa.visitabr.view;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.adapter.CityAdapter;
import com.example.orafa.visitabr.model.CityPower;
import com.example.orafa.visitabr.model.Country;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCityFragment extends Fragment {

    private static final String EXTRA_COUNTRY = "param1";

    private Country mCountry;
    private List<CityPower> mCityPower;
    ArrayAdapter<CityPower> mAdapter;

    @BindView(R.id.list_view_city)
    ListView listViewCity;

    public ListCityFragment() {
        // Required empty public constructor
    }

    public static ListCityFragment newInstance(Country country) {
        ListCityFragment fragment = new ListCityFragment();
        Bundle args = new Bundle();
        Parcelable p = Parcels.wrap(country);
        args.putParcelable(EXTRA_COUNTRY, p);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Parcelable p = getArguments().getParcelable(EXTRA_COUNTRY);
            mCountry = Parcels.unwrap(p);
        }
        mCityPower = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_list_city, container, false);

        ButterKnife.bind(this, layout);

        CityPower cp;

        for(String s : mCountry.getCity()) {
            cp = new CityPower(s, mCountry);
            mCityPower.add(cp);
        }

        mAdapter = new CityAdapter(getContext(), mCityPower);

        listViewCity.setAdapter(mAdapter);

        return layout;
    }

}
