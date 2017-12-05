package com.example.orafa.visitabr.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.adapter.CityAdapter;
import com.example.orafa.visitabr.dao.DCountryUser;
import com.example.orafa.visitabr.model.CityPower;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCityWillFragment extends Fragment {

    private List<CityPower> mCityPower;
    ArrayAdapter<CityPower> mAdapter;

    @BindView(R.id.list_view_city_will)
    ListView listViewCityWill;


    public ListCityWillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_list_city_will, container, false);

        ButterKnife.bind(this, layout);

        return layout;

    }

    @Override
    public void onResume() {
        super.onResume();
        runList();
    }

    private void runList() {
        DCountryUser dCountryUser = new DCountryUser(getContext());
        mCityPower = dCountryUser.findCities("will");

        mAdapter = new CityAdapter(getContext(), mCityPower);
        listViewCityWill.setAdapter(mAdapter);
    }

    @OnItemClick(R.id.list_view_city_will)
    public void itemSelectedCountry(int position) {
        CityPower cityPower = mCityPower.get(position);
        Intent intent = new Intent(getContext(), DetailCityActivity.class);
        Parcelable parcelable = Parcels.wrap(cityPower);
        intent.putExtra(DetailCityActivity.EXTRA_CITY, parcelable);
        startActivity(intent);
    }

}
