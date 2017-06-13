package com.example.gleb.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Синглетный класс существующийс только в одном экземпляре
 * Created by Gleb on 06.06.2017.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;

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




    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();

    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime (UUID id) {
        for (Crime crime:mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }

    public void addCrime (Crime c) {
        mCrimes.add(c);
    }
}
