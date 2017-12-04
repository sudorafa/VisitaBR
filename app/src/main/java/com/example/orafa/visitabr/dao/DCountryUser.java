package com.example.orafa.visitabr.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.orafa.visitabr.model.CityPower;

import static com.example.orafa.visitabr.dao.CountryUserHelper.*;

/**
 * Created by oRafa on 04/12/2017.
 */

public class DCountryUser {

    private CountryUserHelper helper;

    public static final int ID_USER = 1;

    public DCountryUser(Context context) {
        helper = new CountryUserHelper(context);
    }

    @NonNull
    private ContentValues getContentValues(CityPower cityPower, String table, long idCountry) {
        ContentValues cv = new ContentValues();
        if (table.equals(TABLE_COUNTRY)) {
            cv.put("nameCountry", cityPower.getCountry().getName());
            cv.put("initials", cityPower.getCountry().getInitials());
            cv.put("region", cityPower.getCountry().getRegion());
            cv.put("capital", cityPower.getCountry().getCapital());
        } else if (table.equals(TABLE_CITY)) {
            cv.put("nameCity", cityPower.getName());
            cv.put("idCountry", idCountry);
        } else if (table.equals(TABLE_USER_REASON)) {
            cv.put("idUser", ID_USER);
            cv.put("idCountry", idCountry);
            cv.put("reason", cityPower.getReason());
            cv.put("whenDay", cityPower.getWhenDay());
            cv.put("place", cityPower.getPlace());
            cv.put("cause", cityPower.getCause());
        }
        return cv;
    }

    public long insertCountry(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv;

        cv = getContentValues(cityPower, TABLE_COUNTRY, 0);
        long idCountry = db.insert(TABLE_COUNTRY, null, cv);

        cv = getContentValues(cityPower, TABLE_CITY, idCountry);
        db.insert(TABLE_CITY, null, cv);

        db.close();
        return idCountry;
    }

    public void insertUserReason(CityPower cityPower, long idCountry) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv;

        cv = getContentValues(cityPower, TABLE_USER_REASON, idCountry);
        db.insert(TABLE_USER_REASON, null, cv);

        db.close();
    }

    public int updateUserReason(CityPower cityPower, long idCountry) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int lines = 0;
        if (cityPower.getReason().equals("wish")) {
            deleteReason(cityPower, idCountry);
        } else {
            ContentValues cv = getContentValues(cityPower, TABLE_USER_REASON, idCountry);
            lines = db.update(
                    TABLE_USER_REASON, cv,
                    " reason = ? and idCountry = ? and idUser = ? ",
                    new String[]{cityPower.getReason(), String.valueOf(idCountry), String.valueOf(ID_USER)});
        }
        db.close();
        return lines;
    }

    public int deleteReason(CityPower cityPower, long idCountry) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int lines = 0;
        lines = db.delete(
                TABLE_USER_REASON,
                " reason = ? and idCountry = ? and idUser = ? ",
                new String[]{cityPower.getReason(), String.valueOf(idCountry), String.valueOf(ID_USER)});
        db.close();
        return lines;
    }

    public long findCityCountry(CityPower cityPower) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long idCountry = 0;

        Cursor cursor = db.rawQuery(
                String.format("select con.idCountry from %s as con inner join %s as ct on con.idCountry = ct.idCountry  where con.nameCountry = ? and ct.nameCity = ? ", TABLE_COUNTRY, TABLE_CITY)
                , new String[]{cityPower.getCountry().getName(), cityPower.getName()});
        if (cursor != null && cursor.moveToFirst()) {
            //cursor.moveToNext();
            idCountry = cursor.getInt(0);
            cursor.close();
        }
        return idCountry;
    }

    public long findExistReason(CityPower cityPower, long idCountry) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long existReason = 0;

        if(cityPower.getReason()!=null){
            Cursor cursor = db.rawQuery(
                    String.format("select count(*) from %s where idCountry = ? and reason = ?", TABLE_USER_REASON)
                    , new String[]{String.valueOf(idCountry), cityPower.getReason()});
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
        long idCountry;

        idCountry = findCityCountry(cityPower);
        if (idCountry == 0) {
            idCountry = insertCountry(cityPower);
        }

        existReason = findExistReason(cityPower, idCountry);
        if (existReason == 0) {
            insertUserReason(cityPower, idCountry);
        } else {
            updateUserReason(cityPower, idCountry);
        }
    }

    public boolean isFav(CityPower cityPower) {
        boolean yes = false;
        long idCountry;
        long existReason;

        idCountry = findCityCountry(cityPower);
        existReason = findExistReason(cityPower, idCountry);
        if (existReason > 0) {
            yes = true;
        }
        return yes;
    }
}
