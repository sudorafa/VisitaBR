package com.example.orafa.visitabr.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.dao.DCountryUser;
import com.example.orafa.visitabr.model.CityPower;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailCityWentWillActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "city";
    CityPower mCityPower;
    DCountryUser mDCountryUser;

    @BindView(R.id.text_view_title)
    TextView textViewTitle;
    @BindView(R.id.edit_view_place)
    EditText editTextPlace;
    @BindView(R.id.spinner_cause)
    Spinner spinnerCause;
    @BindView(R.id.calendar_view_when_day)
    CalendarView calendarViewWhenDay;

    @BindView(R.id.float_button_save)
    FloatingActionButton floatButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detais_city_went_will);

        mCityPower = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_CITY));

        ButterKnife.bind(this);

        if (mCityPower.getReason().equals("went")) {
            textViewTitle.setText(R.string.title_went);
        } else if (mCityPower.getReason().equals("will")) {
            textViewTitle.setText(R.string.title_will);
        }

        if (mCityPower.getPlace() != null) {
            editTextPlace.setText(mCityPower.getPlace());
        }
    }

    @OnClick(R.id.float_button_save)
    public void saveClick() {
        mDCountryUser = new DCountryUser(this);
        String spinnerCauseValue = spinnerCause.getSelectedItem().toString();

        mCityPower.setPlace(editTextPlace.getText().toString());
        mCityPower.setCause(spinnerCauseValue);

        mDCountryUser.saveCountryAndUserReason(mCityPower);
        finish();
    }
}
