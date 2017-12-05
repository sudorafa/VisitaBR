package com.example.orafa.visitabr.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.orafa.visitabr.model.CityPower;
import com.example.orafa.visitabr.model.Country;

import java.util.ArrayList;
import java.util.List;

import static com.example.orafa.visitabr.dao.CountryUserHelper.*;

/**
 * Created by oRafa on 04/12/2017.
 */

public class DCountryUser {

    private CountryUserHelper helper;

    public static final int ID_USER = 1;
    Country country;

    public DCountryUser(Context context) {
        helper = new CountryUserHelper(context);
    }

    @NonNull
    private ContentValues getContentValues(CityPower cityPower, String table) {
        ContentValues cv = new ContentValues();
        if (table.equals(TABLE_COUNTRY)) {
            cv.put("nameCountry", cityPower.getCountry().getName());
            cv.put("initials", cityPower.getCountry().getInitials());
            cv.put("region", cityPower.getCountry().getRegion());
            cv.put("capital", cityPower.getCountry().getCapital());
        } else if (table.equals(TABLE_CITY)) {
            cv.put("nameCity", cityPower.getName());
            cv.put("idCountry", cityPower.getCountry().getIdCountry());
        } else if (table.equals(TABLE_USER_REASON)) {
            cv.put("idUser", ID_USER);
            cv.put("idCountry", cityPower.getCountry().getIdCountry());
            cv.put("idCity", cityPower.getIdCity());
            cv.put("reason", cityPower.getReason());
            cv.put("whenDay", cityPower.getWhenDay());
            cv.put("place", cityPower.getPlace());
            cv.put("cause", cityPower.getCause());
        }
        return cv;
    }

    public CityPower insertCountry(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv;
        country = new Country();
        country = cityPower.getCountry();

        cv = getContentValues(cityPower, TABLE_COUNTRY);
        long idCountry = db.insert(TABLE_COUNTRY, null, cv);

        country.setIdCountry(idCountry);
        cityPower.setCountry(country);

        db.close();
        return cityPower;
    }

    public CityPower insertCity(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv;

        cv = getContentValues(cityPower, TABLE_CITY);
        cityPower.setIdCity(db.insert(TABLE_CITY, null, cv));

        db.close();
        return cityPower;
    }

    public void insertUserReason(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv;

        cv = getContentValues(cityPower, TABLE_USER_REASON);
        db.insert(TABLE_USER_REASON, null, cv);

        db.close();
    }

    public int updateUserReason(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int lines = 0;
        if (cityPower.getReason().equals("wish")) {
            deleteReason(cityPower);
        } else {
            ContentValues cv = getContentValues(cityPower, TABLE_USER_REASON);
            lines = db.update(
                    TABLE_USER_REASON, cv,
                    " reason = ? and idCountry = ? and idCity = ? and idUser = ? ",
                    new String[]{cityPower.getReason(), String.valueOf(cityPower.getCountry().getIdCountry()), String.valueOf(cityPower.getIdCity()), String.valueOf(ID_USER)});
        }
        db.close();
        return lines;
    }

    public int deleteReason(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int lines = 0;
        lines = db.delete(
                TABLE_USER_REASON,
                " reason = ? and idCountry = ? and idCity = ? and idUser = ? ",
                new String[]{cityPower.getReason(), String.valueOf(cityPower.getCountry().getIdCountry()), String.valueOf(cityPower.getIdCity()), String.valueOf(ID_USER)});
        db.close();
        return lines;
    }

    public CityPower findCountry(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        country = new Country();
        country = cityPower.getCountry();
        long idCountry = 0;

        Cursor cursor = db.rawQuery(
                String.format("select idCountry from %s where nameCountry = ? ", TABLE_COUNTRY)
                , new String[]{cityPower.getCountry().getName()});
        if (cursor != null && cursor.moveToFirst()) {
            //cursor.moveToNext();
            idCountry = cursor.getInt(0);
            cursor.close();
        }

        country.setIdCountry(idCountry);
        cityPower.setCountry(country);

        return cityPower;
    }

    public CityPower findCity(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                String.format("select idCity from %s where nameCity = ? ", TABLE_CITY)
                , new String[]{cityPower.getName()});
        if (cursor != null && cursor.moveToFirst()) {
            //cursor.moveToNext();
            cityPower.setIdCity(cursor.getInt(0));
            cursor.close();
        }

        return cityPower;
    }

    public long findExistReason(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long existReason = 0;

        if (cityPower.getReason() != null) {
            Cursor cursor = db.rawQuery(
                    String.format("select count(*) from %s where idCountry = ? and idCity = ? and reason = ? and idUser = ?", TABLE_USER_REASON)
                    , new String[]{String.valueOf(cityPower.getCountry().getIdCountry()), String.valueOf(cityPower.getIdCity()), cityPower.getReason(), String.valueOf(ID_USER)});
            if (cursor != null && cursor.moveToFirst()) {
                //cursor.moveToNext();
                existReason = cursor.getLong(0);
                cursor.close();
            }
        }
        return existReason;
    }

    public void saveCountryAndUserReason(CityPower cityPower) {

        long existReason;

        cityPower = findCountry(cityPower);
        if (cityPower.getCountry().getIdCountry() == 0) {
            cityPower = insertCountry(cityPower);
        }

        cityPower = findCity(cityPower);
        if (cityPower.getIdCity() == 0) {
            cityPower = insertCity(cityPower);
        }

        existReason = findExistReason(cityPower);
        if (existReason == 0) {
            insertUserReason(cityPower);
        } else {
            updateUserReason(cityPower);
        }
    }

    public boolean isFav(CityPower cityPower) {
        boolean yes = false;
        long existReason;

        cityPower = findCity(cityPower);
        cityPower = findCountry(cityPower);
        existReason = findExistReason(cityPower);
        if (existReason > 0) {
            yes = true;
        }
        return yes;
    }

    public List<CityPower> findCities(String reason) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql;
        String[] arguments = null;
        List<CityPower> cityPowers = new ArrayList<CityPower>();

        sql = "select distinct con.nameCountry, con.initials, con.region, con.capital, ct.idCity, ct.nameCity, ur.reason, ur.whenDay, ur.place, ur.cause from "
                + TABLE_COUNTRY + " as con ";
        sql += "inner join " + TABLE_CITY + " as ct on con.idCountry = ct.idCountry ";
        sql += "inner join " + TABLE_USER_REASON + " as ur on con.idCountry = ct.idCountry and ur.idCity = ct.idCity ";
        sql += " where ur.idUser = ? ";
        arguments = new String[]{String.valueOf(ID_USER)};
        if (reason != null) {
            sql += " and ur.reason = ? ";
            arguments = new String[]{String.valueOf(ID_USER), reason};
        }
        sql += " order by con.nameCountry ";

        Cursor cursor = db.rawQuery(sql, arguments);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Country country = new Country();
                CityPower cityPower = new CityPower();

                country.setName(cursor.getString(cursor.getColumnIndex("nameCountry")));
                country.setInitials(cursor.getString(cursor.getColumnIndex("initials")));
                country.setRegion(cursor.getString(cursor.getColumnIndex("region")));
                country.setCapital(cursor.getString(cursor.getColumnIndex("capital")));

                cityPower.setCountry(country);
                cityPower.setName(cursor.getString(cursor.getColumnIndex("nameCity")));
                cityPower.setReason(cursor.getString(cursor.getColumnIndex("reason")));
                cityPower.setWhenDay(cursor.getString(cursor.getColumnIndex("whenDay")));
                cityPower.setPlace(cursor.getString(cursor.getColumnIndex("place")));
                cityPower.setCause(cursor.getString(cursor.getColumnIndex("cause")));

                cityPowers.add(cityPower);
            }
            cursor.close();
        }
        db.close();
        return cityPowers;
    }
}
