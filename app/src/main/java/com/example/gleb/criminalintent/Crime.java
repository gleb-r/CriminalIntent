package com.example.gleb.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Training model containing unique ID
 * and title fields
 * Created by Gleb on 04.06.2017.
 */

public class Crime {


    private UUID mId;
    private String mTitle;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    private Date mDate;

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    private boolean mSolved;

    public Crime () {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
