package com.example.orafa.visitabr.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.CityPower;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oRafa on 01/12/2017.
 */

public class CityAdapter extends ArrayAdapter<CityPower>{


    public CityAdapter(@NonNull Context context, @NonNull List<CityPower> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CityPower cityPower = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_city, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ButterKnife.bind(this, convertView);

        viewHolder.textViewName.setText(String.format("%s - %s", cityPower.getName(), cityPower.getCountry().getInitials()));
        viewHolder.textViewRegion.setText(String.format("%s - %s", cityPower.getCountry().getName(), cityPower.getCountry().getRegion()));

        Picasso.with(getContext()).load(cityPower.getCountry().getImg()).into(viewHolder.imageViewCover);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.image_view_cover)
        ImageView imageViewCover;
        @BindView(R.id.text_view_Name)
        TextView textViewName;
        @BindView(R.id.text_view_region)
        TextView textViewRegion;

        public ViewHolder(View parent) {
            ButterKnife.bind(this, parent);
            parent.setTag(this);
        }
    }
}
