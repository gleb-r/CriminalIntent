package com.example.gleb.criminalintent;

import java.util.UUID;

/**
 * Training model containing unique ID
 * and title fields
 * Created by Gleb on 04.06.2017.
 */

public class Crime {


    private UUID mId;



    private String mTitle;

    public Crime () {
        mId = UUID.randomUUID();
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
