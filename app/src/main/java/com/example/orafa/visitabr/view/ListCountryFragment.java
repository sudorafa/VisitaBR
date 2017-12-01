package com.example.orafa.visitabr.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.orafa.visitabr.R;
import com.example.orafa.visitabr.model.Country;
import com.example.orafa.visitabr.model.CountryAdapter;
import com.example.orafa.visitabr.model.Object;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCountryFragment extends Fragment {

    @BindView(R.id.list_view_country)
    ListView mListViewCountry;

    List<Country> mCountry;
    ArrayAdapter<Country> mAdapterCountry;

    StateTask mTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mCountry = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_list_country, container, false);
        ButterKnife.bind(this, layout);

        mAdapterCountry = new CountryAdapter(getContext(), mCountry);
        mListViewCountry.setAdapter(mAdapterCountry);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mCountry.size() == 0 && mTask == null) {
            mTask = new StateTask();
            mTask.execute();
        }
    }

    class StateTask extends AsyncTask<Void, Void, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url("https://raw.githubusercontent.com/sudorafa/MyJson/master/Country/brazil.json").build();

            try {
                Response response = client.newCall(request).execute();
                String jsonString = response.body().string();
                Log.d("testarJSON", jsonString);
                Gson gson = new Gson();
                Object object = gson.fromJson(jsonString, Object.class);
                return object;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object object) {
            super.onPostExecute(object);
            if (object != null) {
                mCountry.clear();
                mCountry.addAll(object.getCountry());
                mAdapterCountry.notifyDataSetChanged();
            }
        }
    }
}
