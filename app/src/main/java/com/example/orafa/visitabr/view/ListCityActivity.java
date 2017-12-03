package com.example.orafa.visitabr.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.Country;

import org.parceler.Parcels;


public class ListCityActivity extends AppCompatActivity {

    public static final String EXTRA_COUNTRY = "country";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_city);

        Country country = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_COUNTRY));

        ListCityFragment listCityFragment = ListCityFragment.newInstance(country);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.city, listCityFragment, "city")
                .commit();

    }
}
