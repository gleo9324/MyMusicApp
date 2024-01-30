package com.example.android.mymusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView list;
    SongAdapter adapter;
    SearchView editsearch;
    ArrayList<Song> allSongs = new ArrayList<Song>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.search_list_view);

        //Searches in the entire song lists and displays the entire list
        for (int i = 0;i <= getResources().getStringArray(R.array.songs_playlist_battiato).length - 1;i++){
            allSongs.add(new Song(getResources().getStringArray(R.array.songs_playlist_battiato)[i],getResources().getStringArray(R.array.hours_playlist_battiato)[i],
                    getResources().getStringArray(R.array.minutes_playlist_battiato)[i],getResources().getStringArray(R.array.seconds_playlist_battiato)[i],
                    getResources().getString(R.string.authors_battiato)));
        }

        for (int i = 0;i <= getResources().getStringArray(R.array.songs_playlist_battisti).length - 1;i++){
            allSongs.add(new Song(getResources().getStringArray(R.array.songs_playlist_battisti)[i],getResources().getStringArray(R.array.hours_playlist_battisti)[i],
                    getResources().getStringArray(R.array.minutes_playlist_battisti)[i],getResources().getStringArray(R.array.seconds_playlist_battisti)[i],
                    getResources().getString(R.string.authors_battisti)));
        }

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_vasco).length - 1;i++){
            allSongs.add(new Song(getResources().getStringArray(R.array.songs_playlist_vasco)[i],getResources().getStringArray(R.array.hours_playlist_vasco)[i],
                    getResources().getStringArray(R.array.minutes_playlist_vasco)[i],getResources().getStringArray(R.array.seconds_playlist_vasco)[i],
                    getResources().getString(R.string.authors_vasco)));
        }

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_rozalen).length - 1;i++){
            allSongs.add(new Song(getResources().getStringArray(R.array.songs_playlist_rozalen)[i],getResources().getStringArray(R.array.hours_playlist_rozalen)[i],
                    getResources().getStringArray(R.array.minutes_playlist_rozalen)[i],getResources().getStringArray(R.array.seconds_playlist_rozalen)[i],
                    getResources().getString(R.string.authors_rozalen)));
        }

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_beatles).length - 1;i++){
            allSongs.add(new Song(getResources().getStringArray(R.array.songs_playlist_beatles)[i],getResources().getStringArray(R.array.hours_playlist_beatles)[i],
                    getResources().getStringArray(R.array.minutes_playlist_beatles)[i],getResources().getStringArray(R.array.seconds_playlist_beatles)[i],
                    getResources().getString(R.string.authors_beatles)));
        }

        // Pass results to SongAdapter Class
        adapter = new SongAdapter(this, allSongs);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml and edits the list depending on the text input
        editsearch = (SearchView) findViewById(R.id.song_search_view);
        editsearch.setOnQueryTextListener(this);

        //Creates an intent that brings you to the NowPlayingActivity, passing data about the selected song
        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent playingtIntent = new Intent(SearchActivity.this, NowPlayingActivity.class);
                playingtIntent.putExtra("song", allSongs.get(position));
                playingtIntent.putExtra("position", position);
                startActivity(playingtIntent);
            }
        });

        //Finds the search TextView and sets an OnClickListener to pass from SearchActivity to the NowPlaying
        TextView nowPlaying = (TextView) findViewById(R.id.now_playing_text_view);

        // Set a click listener on that View
        nowPlaying.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the nowPlaying View is clicked on.
            @Override
            public void onClick(View view) {
                Intent nowPlayingIntent = new Intent(SearchActivity.this, NowPlayingActivity.class);
                startActivity(nowPlayingIntent);
            }
        });

        //Finds the search TextView and sets an OnClickListener to pass from SearchActivity to the MainActivity
        TextView home = (TextView) findViewById(R.id.home_text_view);

        // Set a click listener on that View
        home.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the home View is clicked on.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
