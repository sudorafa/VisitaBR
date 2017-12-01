package com.example.orafa.visitabr.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by oRafa on 30/11/2017.
 */

@Parcel
public class Country {

    private String initials;
    private String name;
    private String region;
    private String capital;
    private List<String> city;

    public Country(String initials, String name, String region, String capital, List<String> city) {
        this.initials = initials;
        this.name = name;
        this.region = region;
        this.capital = capital;
        this.city = city;
    }

    public Country() {

    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }
}