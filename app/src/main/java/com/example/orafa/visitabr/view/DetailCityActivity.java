package com.example.orafa.visitabr.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.dao.DCountryUser;
import com.example.orafa.visitabr.model.CityPower;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailCityActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "city";
    CityPower mCityPower;
    DCountryUser mDCountryUser;

    @BindView(R.id.text_view_city_title)
    TextView textViewCityTitle;
    @BindView(R.id.text_view_name)
    TextView textViewName;
    @BindView(R.id.text_view_label_wish)
    TextView textViewLabelWish;
    @BindView(R.id.text_view_state_region)
    TextView textViewRegion;

    @BindView(R.id.float_button_wish)
    FloatingActionButton floatButtonWish;
    @BindView(R.id.float_button_went)
    FloatingActionButton floatButtonWent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        mCityPower = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_CITY));

        ButterKnife.bind(this);

        textViewName.setText(mCityPower.getName());
        textViewCityTitle.setText(String.format("%s: %s - %s", getString(R.string.city), mCityPower.getName(), mCityPower.getCountry().getInitials()));
        textViewRegion.setText(String.format("%s - %s", mCityPower.getCountry().getName(), mCityPower.getCountry().getRegion()));

        toggleWish();

    }

    private void toggleWish() {
        mCityPower.setReason("wish");
        mDCountryUser = new DCountryUser(this);
        boolean wish = mDCountryUser.isFav(mCityPower);

        floatButtonWish.setBackgroundTintList(
                wish ? ColorStateList.valueOf(Color.RED) : ColorStateList.valueOf(Color.GREEN)
        );
        textViewLabelWish.setText(
                wish ? getString(R.string.isWish) : getString(R.string.addToWish)
        );

    }

    @OnClick(R.id.float_button_wish)
    public void wishClick() {
        mDCountryUser.saveCountryAndUserReason(mCityPower);
        toggleWish();
    }

    @OnClick(R.id.float_button_went)
    public void wentClick(){

    }
}
