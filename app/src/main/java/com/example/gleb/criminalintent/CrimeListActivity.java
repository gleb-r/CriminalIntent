package com.example.gleb.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Gleb on 05.06.2017.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
