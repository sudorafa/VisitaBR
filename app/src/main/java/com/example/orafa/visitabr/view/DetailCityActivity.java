package com.example.orafa.visitabr.view;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Parcelable;
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
    @BindView(R.id.text_view_label_went)
    TextView textViewLabelWent;
    @BindView(R.id.text_view_label_will)
    TextView textViewLabelWill;
    @BindView(R.id.text_view_state_region)
    TextView textViewRegion;

    @BindView(R.id.float_button_wish)
    FloatingActionButton floatButtonWish;
    @BindView(R.id.float_button_went)
    FloatingActionButton floatButtonWent;
    @BindView(R.id.float_button_will)
    FloatingActionButton floatButtonWill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        mCityPower = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_CITY));

        ButterKnife.bind(this);

        textViewName.setText(mCityPower.getName());
        textViewCityTitle.setText(String.format("%s: %s - %s", getString(R.string.city), mCityPower.getName(), mCityPower.getCountry().getInitials()));
        textViewRegion.setText(String.format("%s - %s", mCityPower.getCountry().getName(), mCityPower.getCountry().getRegion()));

        toggleWWW();

    }

    private void toggleWWW() {
        mCityPower.setReason("wish");
        mDCountryUser = new DCountryUser(this);

        boolean wish = mDCountryUser.isFav(mCityPower);

        floatButtonWish.setBackgroundTintList(
                wish ? ColorStateList.valueOf(Color.RED) : ColorStateList.valueOf(Color.GREEN)
        );
        textViewLabelWish.setText(
                wish ? getString(R.string.isWish) : getString(R.string.addToWish)
        );

        //
        mCityPower.setReason("will");
        boolean will = mDCountryUser.isFav(mCityPower);

        floatButtonWill.setBackgroundTintList(
                will ? ColorStateList.valueOf(Color.RED) : ColorStateList.valueOf(Color.GREEN)
        );
        textViewLabelWill.setText(
                will ? getString(R.string.i_go) : getString(R.string.question_go)
        );

        //
        mCityPower.setReason("went");
        boolean went = mDCountryUser.isFav(mCityPower);

        floatButtonWent.setBackgroundTintList(
                went ? ColorStateList.valueOf(Color.RED) : ColorStateList.valueOf(Color.GREEN)
        );
        textViewLabelWent.setText(
                went ? getString(R.string.i_went) : getString(R.string.question_went)
        );
    }

    @OnClick(R.id.float_button_wish)
    public void wishClick() {
        mCityPower.setReason("wish");
        mDCountryUser.saveCountryAndUserReason(mCityPower);
        toggleWWW();
    }

    @OnClick(R.id.float_button_went)
    public void wentClick() {
        CityPower cityPower = mCityPower;
        cityPower.setReason("went");
        Intent intent = new Intent(this, DetailCityWentWillActivity.class);
        Parcelable parcelable = Parcels.wrap(cityPower);
        intent.putExtra(DetailCityWentWillActivity.EXTRA_CITY, parcelable);
        startActivity(intent);
    }

    @OnClick(R.id.float_button_will)
    public void willClick() {
        CityPower cityPower = mCityPower;
        cityPower.setReason("will");
        Intent intent = new Intent(this, DetailCityWentWillActivity.class);
        Parcelable parcelable = Parcels.wrap(cityPower);
        intent.putExtra(DetailCityWentWillActivity.EXTRA_CITY, parcelable);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toggleWWW();
    }
}
