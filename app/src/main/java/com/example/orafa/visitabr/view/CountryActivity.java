package com.example.orafa.visitabr.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.ClickStateListener;
import com.example.orafa.visitabr.model.Country;

import org.parceler.Parcels;

public class CountryActivity extends AppCompatActivity implements ClickStateListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
    }

    @Override
    public void stateClicked(Country country) {
        if (getResources().getBoolean(R.bool.tablet)) {
            ListCityFragment listCityFragment = ListCityFragment.newInstance(country);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.city, listCityFragment, "city")
                    .commit();
        } else if (getResources().getBoolean(R.bool.land)) {
            ListCityFragment listCityFragment = ListCityFragment.newInstance(country);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.city, listCityFragment, "city")
                    .commit();
        } else {
            Intent intent = new Intent(this, ListCityActivity.class);
            Parcelable parcelable = Parcels.wrap(country);
            intent.putExtra(ListCityActivity.EXTRA_COUNTRY, parcelable);
            startActivity(intent);
        }
    }
}
