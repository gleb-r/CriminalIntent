package com.example.gleb.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.gleb.criminalintent.Crime;
import com.example.gleb.criminalintent.database.CrimeDbSchema.CrimeTable.Cols;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Gleb on 14.09.2017.
 */

public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper (Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime () {
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String title = getString(getColumnIndex(Cols.TITLE));
        long date = getLong(getColumnIndex(Cols.DATE));
        int isSolved = getInt(getColumnIndex(Cols.SOLVED));
        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved!=0);

        return crime;
    }
}
