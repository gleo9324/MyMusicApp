package com.example.android.mymusicapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable {
    // Playlist name
    private String mPlaylistName;

    // Playlist number of songs
    private int mPlaylistSongs;

    // Playlist cover image ID
    private int mImageResourceId;

    // Plailist songs
    private ArrayList<Song> mSong;

    /*
     * Create a new Playlist object.
     *
     * @param vName is the name of the Playlist
     * @param vSongsNumber is the number of songs in the Playlist
     * @param image is drawable reference ID that corresponds to the Playlist cover
     * @param vSong is the list of song info of the playlist
     * */
    public Playlist(String vName, int vSongsNumber, int imageResourceId, ArrayList<Song> vSong){
        mPlaylistName = vName;
        mPlaylistSongs = vSongsNumber;
        mImageResourceId = imageResourceId;
        mSong = vSong;
    }

    /**
     * Get the name of the Playlist
     */
    public String getPlaylistName() {
        return mPlaylistName;
    }

    /**
     * Get the number of songs in the Playlist
     */
    public int getPlaylistSongs() {
        return mPlaylistSongs;
    }

    /**
     * Get the image resource ID of the cover
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Get the song list of the Playlist
     */
    public ArrayList<Song> getSongList() {
        return mSong;
    }
}
