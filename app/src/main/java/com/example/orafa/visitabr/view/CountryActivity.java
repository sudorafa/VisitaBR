package com.example.orafa.visitabr.view;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.ClickStateListener;
import com.example.orafa.visitabr.model.Country;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryActivity extends AppCompatActivity implements ClickStateListener{

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mViewPager.setAdapter(new CityPager(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class CityPager extends FragmentPagerAdapter {

        public CityPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ListCountryFragment();
            } else if (position == 1) {
                return new ListCityWishFragment();
            } else if (position == 2) {
                return new ListCityWentFragment();
            } else if (position == 3) {
                return new ListCityWillFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getString(R.string.states);
            } else if (position == 1) {
                return getString(R.string.wish);
            } else if (position == 2) {
                return getString(R.string.went);
            } else if (position == 3) {
                return getString(R.string.will);
            }
            return null;
        }
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
