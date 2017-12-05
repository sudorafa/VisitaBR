package com.example.orafa.visitabr.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by oRafa on 04/12/2017.
 */

public class CountryUserHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "visitBR";
    private static final int VERSION_DB = 9;
    public static final String TABLE_COUNTRY = "country";
    public static final String TABLE_CITY = "city";
    public static final String TABLE_USER_REASON = "userCause";

    public CountryUserHelper(Context context) {
        super(context, NOME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTableCountry = "create table " + TABLE_COUNTRY + " (idCountry integer primary key AUTOINCREMENT, nameCountry TEXT, initials TEXT, region TEXT, capital TEXT);";
        db.execSQL(sqlTableCountry);

        String sqlTableCity = "create table " + TABLE_CITY + " (idCity integer primary key AUTOINCREMENT, nameCity TEXT, idCountry integer);";
        db.execSQL(sqlTableCity);

        String sqlTableUser = "create table " + TABLE_USER_REASON + " (idUser integer, idCountry integer, idCity integer, reason TEXT, whenDay DATETIME, place TEXT, cause TEXT);";
        db.execSQL(sqlTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlTableCountry = "drop table " + TABLE_COUNTRY;
        db.execSQL(sqlTableCountry);

        String sqlTableCity = "drop table " + TABLE_CITY;
        db.execSQL(sqlTableCity);

        String sqlTableUser = "drop table " + TABLE_USER_REASON;
        db.execSQL(sqlTableUser);

        onCreate(db);
    }
}
