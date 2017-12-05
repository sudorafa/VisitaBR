package com.example.orafa.visitabr.model;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by oRafa on 02/12/2017.
 */

@Parcel
public class CityPower {
    private long idCity;
    private String name;
    private Country country;
    private String reason;
    private String whenDay;
    private String place;
    private String cause;

    public CityPower(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public CityPower(long idCity, String name, Country country, String reason, String whenDay, String place, String cause) {
        this.idCity = idCity;
        this.name = name;
        this.country = country;
        this.reason = reason;
        this.whenDay = whenDay;
        this.place = place;
        this.cause = cause;
    }

    public CityPower() {

    }

    public long getIdCity() {
        return idCity;
    }

    public void setIdCity(long idCity) {
        this.idCity = idCity;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWhenDay() {
        return whenDay;
    }

    public void setWhenDay(String whenDay) {
        this.whenDay = whenDay;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
