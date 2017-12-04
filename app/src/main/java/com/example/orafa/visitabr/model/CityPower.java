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
    private String reason;
    private String when;
    private String where;
    private String cause;

    public CityPower(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public CityPower(String name, Country country, String reason, String when, String where, String cause) {
        this.name = name;
        this.country = country;
        this.reason = reason;
        this.when = when;
        this.where = where;
        this.cause = cause;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
