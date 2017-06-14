package com.example.gleb.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gleb.criminalintent.database.CrimeDbSchema.CrimeTable.Cols;

import static com.example.gleb.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by Gleb on 14.06.2017.
 */

public class CrimeBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper (Context context) {
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CrimeTable.NAME +
                "("+"_id integer primary key autoincrement, "+
                Cols.UUID + ", "+
                Cols.TITLE + ", "+
                Cols.DATE + ", "+
                Cols.SOLVED + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
