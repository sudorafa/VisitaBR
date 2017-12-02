package com.example.orafa.visitabr.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.Country;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListCityActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    public static final String EXTRA_COUNTRY = "country";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_city);

        ButterKnife.bind(this);

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
                Country country = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_COUNTRY));
                return ListCityFragment.newInstance(country);
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
                return getString(R.string.cities);
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
}
