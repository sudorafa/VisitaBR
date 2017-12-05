package com.example.orafa.visitabr.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.CityPower;

import org.parceler.Parcels;


public class DetailCityActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        CityPower cityPower = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_CITY));

        DetailCityFragment detailCityFragment = DetailCityFragment.newInstance(cityPower);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detail, detailCityFragment, "detail")
                .commit();
    }
}
