package com.example.orafa.visitabr.model;

import java.util.List;

/**
 * Created by oRafa on 30/11/2017.
 */

public class Object {

    private List<Country> country;

    private List<GeoBr> listGeoBr;

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public List<GeoBr> getListGeoBr() {
        return listGeoBr;
    }

    public void setListGeoBr(List<GeoBr> listGeoBr) {
        this.listGeoBr = listGeoBr;
    }
}
