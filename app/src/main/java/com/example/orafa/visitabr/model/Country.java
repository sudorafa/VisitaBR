package com.example.orafa.visitabr.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by oRafa on 30/11/2017.
 */

@Parcel
public class Country {

    private long idCountry;
    private String initials;
    private String name;
    private String region;
    private String capital;
    private String img;
    private List<String> city;

    public Country(long idCountry, String initials, String name, String region, String capital, String img, List<String> city) {
        this.idCountry = idCountry;
        this.initials = initials;
        this.name = name;
        this.region = region;
        this.capital = capital;
        this.city = city;
        this.img = img;
    }

    public Country() {

    }

    public long getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(long idCountry) {
        this.idCountry = idCountry;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }
}