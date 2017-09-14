package com.example.gleb.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gleb.criminalintent.database.CrimeBaseHelper;
import com.example.gleb.criminalintent.database.CrimeCursorWrapper;
import com.example.gleb.criminalintent.database.CrimeDbSchema.CrimeTable;
import com.example.gleb.criminalintent.database.CrimeDbSchema.CrimeTable.Cols;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Синглетный класс существующийс только в одном экземпляре
 * Created by Gleb on 06.06.2017.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /**
     * Возвращает единственный экземпляр класса
     * или если не был создан - создает через закрытый конструктор
     * @param context
     * @return
     */
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private static ContentValues getContentValues (Crime crime) {
        ContentValues values = new ContentValues();
        values.put(Cols.UUID,crime.getId().toString());
        values.put(Cols.TITLE,crime.getTitle());
        values.put(Cols.DATE,crime.getDate().getTime());
        values.put(Cols.SOLVED,crime.isSolved()?1:0);

        return values;
    }




    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursorWrapper = queryCrimes(null,null);
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()) {
                crimes.add(cursorWrapper.getCrime());
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }
        return crimes;
    }

    public Crime getCrime (UUID id) {
        CrimeCursorWrapper cursorWrapper = queryCrimes
                (Cols.UUID + "=?", new String[]{id.toString()});
        try {
            if (cursorWrapper.getCount()==0) {
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getCrime();
        } finally {
            cursorWrapper.close();
        }



    }

    public void addCrime (Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME,null,values);
    }

    public void updateCrime (Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);
        mDatabase.update
                (CrimeTable.NAME,values, Cols.UUID + "=?", new String[]{uuidString});

    }

    public CrimeCursorWrapper queryCrimes (String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,  // all columns
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }
}
