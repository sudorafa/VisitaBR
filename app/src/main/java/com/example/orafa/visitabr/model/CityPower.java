package com.example.orafa.visitabr.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by oRafa on 02/12/2017.
 */

@Parcel
public class CityPower {
    private String name;
    private Country country;

    public CityPower(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public CityPower() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
