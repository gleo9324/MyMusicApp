package com.example.android.mymusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaylistActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        //Gets the position to select the playlist passed and sets the Action Bar title as the playlist name
        int position = (int) getIntent().getIntExtra("position",0);
        Playlist currentPlaylist = (Playlist) getIntent().getSerializableExtra("playlist");
        getSupportActionBar().setTitle(currentPlaylist.getPlaylistName());
        int songNumber = currentPlaylist.getPlaylistSongs();

        //Creates an array of songs and adds the song elements
        ArrayList<Song> songs = currentPlaylist.getSongList();

        //Creates a ListView displaying the list of songs
        SongAdapter itemsAdapter = new SongAdapter(this, songs);
        ListView listView = (ListView) findViewById(R.id.song);
        listView.setAdapter(itemsAdapter);

        //Creates an intent that brings you to the NowPlayingActivity, passing data about the selected song
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playingtIntent = new Intent(PlaylistActivity.this, NowPlayingActivity.class);
                playingtIntent.putExtra("position", position);
                playingtIntent.putExtra("playlist", currentPlaylist);
                startActivity(playingtIntent);
            }
        });

        //Finds the now playing TextView and sets an OnClickListener to pass from PlaylistActivity to the NowPlayingActivity
        TextView nowPlaying = (TextView) findViewById(R.id.now_playing_text_view);

        nowPlaying.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the nowPlaying View is clicked on.
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(PlaylistActivity.this, NowPlayingActivity.class);
                startActivity(searchIntent);
            }
        });

        //Finds the search TextView and sets an OnClickListener to pass from PlaylistActivity to the SearchActivity
        TextView search = (TextView) findViewById(R.id.search_text_view);

        search.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the search View is clicked on.
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(PlaylistActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        //Finds the now playing TextView and sets an OnClickListener to pass from PlaylistActivity to the MainActivity
        TextView home = (TextView) findViewById(R.id.home_text_view);

        home.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the home View is clicked on.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(PlaylistActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

    }
}
