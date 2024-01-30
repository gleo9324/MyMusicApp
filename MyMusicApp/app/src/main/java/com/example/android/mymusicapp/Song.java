package com.example.android.mymusicapp;

import java.io.Serializable;

public class Song implements Serializable {
    // Song name
    private String mSongName;

    // Song duration in hours
    private String mSongsHour;

    // Song duration in minutes
    private String mSongsMin;

    // Song duration in seconds
    private String mSongsSec;

    // Song author
    private String mSongAuthor;

    /*
     * Create a new Song object.
     *
     * @param vSongName is the name of the Song
     * @param vHours is the duration in hours
     * @param vMin is the duration in minutes
     * @param vSec is the duration in seconds
     * @param vAuthor is the author name
     * */
    public Song(String vSongName, String vHour, String vMin, String vSec, String vAuthor){
        mSongName = vSongName;
        mSongsHour = vHour;
        mSongsMin = vMin;
        mSongsSec = vSec;
        mSongAuthor = vAuthor;
    }

    /**
     * Get the name of the Song
     */
    public String getSongName() {
        return mSongName;
    }

    /**
     * Get the duration in hours of the Song
     */
    public String getSongHours() {
        return mSongsHour;
    }

    /**
     * Get the duration in minutes of the Song
     */
    public String getSongMinutes() {
        return mSongsMin;
    }

    /**
     * Get the duration in seconds of the Song
     */
    public String getSongSeconds() {
        return mSongsSec;
    }

    /**
     * Get the author of the Song
     */
    public String getSongAuthor() {
        return mSongAuthor;
    }
}
