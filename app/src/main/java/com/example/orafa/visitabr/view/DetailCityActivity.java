package com.example.orafa.visitabr.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.CityPower;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailCityActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "city";
    CityPower mCityPower;

    @BindView(R.id.text_view_name)
    TextView textViewName;

    @BindView(R.id.float_button_wish)
    FloatingActionButton floatButtonWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        mCityPower = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_CITY));

        ButterKnife.bind(this);

        textViewName.setText(mCityPower.getName());
        toggleWish();

    }

    private void toggleWish(){
        boolean wish = isFav("rafaa");
        floatButtonWish.setBackgroundTintList(
                wish ? ColorStateList.valueOf(Color.RED) : ColorStateList.valueOf(Color.GREEN)
        );
    }

    @OnClick(R.id.float_button_wish)
    public void wishClick(){
        String rafa = "rafa";
        if(isFav(rafa)){
            //remove
        }else{
            //add
        }
    }

    //fazer +/- isso no DAO
    public boolean isFav(String rafa){
        String rafaAqui = "rafa";
        boolean yes = false;

        if(rafaAqui.equals(rafa)){
            yes = true;
        }

        return yes;
    }

}
