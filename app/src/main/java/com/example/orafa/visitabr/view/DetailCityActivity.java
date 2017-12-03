package com.example.orafa.visitabr.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.CityPower;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailCityActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "city";
    CityPower mCityPower;

    @BindView(R.id.text_view_test)
    TextView textViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        mCityPower = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_CITY));

        ButterKnife.bind(this);
        
        textViewTest.setText(mCityPower.getName());
    }
}
