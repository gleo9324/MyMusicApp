package com.example.android.mymusicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets the Array of songs
        ArrayList<Song> songsBattiato = new ArrayList<Song>();

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_battiato).length - 1;i++){
            songsBattiato.add(new Song(getResources().getStringArray(R.array.songs_playlist_battiato)[i],getResources().getStringArray(R.array.hours_playlist_battiato)[i],
                    getResources().getStringArray(R.array.minutes_playlist_battiato)[i],getResources().getStringArray(R.array.seconds_playlist_battiato)[i],
                    getResources().getString(R.string.authors_battiato)));
        }

        //Sets the Array of songs
        ArrayList<Song> songsBattisti = new ArrayList<Song>();

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_battisti).length - 1;i++){
            songsBattisti.add(new Song(getResources().getStringArray(R.array.songs_playlist_battisti)[i],getResources().getStringArray(R.array.hours_playlist_battisti)[i],
                    getResources().getStringArray(R.array.minutes_playlist_battisti)[i],getResources().getStringArray(R.array.seconds_playlist_battisti)[i],
                    getResources().getString(R.string.authors_battisti)));
        }

        //Sets the Array of songs
        ArrayList<Song> songsVasco = new ArrayList<Song>();

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_vasco).length - 1;i++){
            songsVasco.add(new Song(getResources().getStringArray(R.array.songs_playlist_vasco)[i],getResources().getStringArray(R.array.hours_playlist_vasco)[i],
                    getResources().getStringArray(R.array.minutes_playlist_vasco)[i],getResources().getStringArray(R.array.seconds_playlist_vasco)[i],
                    getResources().getString(R.string.authors_vasco)));
        }

        //Sets the Array of songs
        ArrayList<Song> songsRozalen = new ArrayList<Song>();

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_rozalen).length - 1;i++){
            songsRozalen.add(new Song(getResources().getStringArray(R.array.songs_playlist_rozalen)[i],getResources().getStringArray(R.array.hours_playlist_rozalen)[i],
                    getResources().getStringArray(R.array.minutes_playlist_rozalen)[i],getResources().getStringArray(R.array.seconds_playlist_rozalen)[i],
                    getResources().getString(R.string.authors_rozalen)));
        }

        //Sets the Array of songs
        ArrayList<Song> songsBeatles = new ArrayList<Song>();

        for (int i=0;i <= getResources().getStringArray(R.array.songs_playlist_beatles).length - 1;i++){
            songsBeatles.add(new Song(getResources().getStringArray(R.array.songs_playlist_beatles)[i],getResources().getStringArray(R.array.hours_playlist_beatles)[i],
                    getResources().getStringArray(R.array.minutes_playlist_beatles)[i],getResources().getStringArray(R.array.seconds_playlist_beatles)[i],
                    getResources().getString(R.string.authors_beatles)));
        }

        //Create and array of playlists by author
        ArrayList<Playlist> authorPlaylist = new ArrayList<Playlist>();

        //Add the playlist name, the number of songs in the playlist, and the cover
        authorPlaylist.add(new Playlist("Playlist Battiato",getResources().getStringArray(R.array.songs_playlist_battiato).length,R.drawable.battiato_playlist,songsBattiato));
        authorPlaylist.add(new Playlist("Playlist Vasco",getResources().getStringArray(R.array.songs_playlist_vasco).length,R.drawable.vasco_playlist,songsVasco));
        authorPlaylist.add(new Playlist("Playlist Battisti", getResources().getStringArray(R.array.songs_playlist_battisti).length,R.drawable.battisti_playlist,songsBattisti));
        authorPlaylist.add(new Playlist("Playlist Rozalen",getResources().getStringArray(R.array.songs_playlist_rozalen).length,R.drawable.rozalen_playlist,songsRozalen));
        authorPlaylist.add(new Playlist("Playlist Beatles",getResources().getStringArray(R.array.songs_playlist_beatles).length,R.drawable.beatles_playlist,songsBeatles));

        //Creates the GridView with the playlists by author
        PlaylistAdapter itemsAdapter = new PlaylistAdapter(this, authorPlaylist);
        GridView gridView = (GridView) findViewById(R.id.playlist);
        gridView.setAdapter(itemsAdapter);

        //If you click on an element of the GridView, you open the corresponding playlist.
        //It passes from the MainActivity to the PlaylistActivity
        //The position in the authorPlaylist is passed with the intent.
        gridView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent playlistIntent = new Intent(MainActivity.this, PlaylistActivity.class);
                playlistIntent.putExtra("playlist", authorPlaylist.get(position));
                playlistIntent.putExtra("position", position);
                startActivity(playlistIntent);
            }
        });

        //Create and array of playlists by genre
        ArrayList<Playlist> genrePlaylist = new ArrayList<Playlist>();

        //Sets the Array of songs for genre playlists
        ArrayList<Song> songsItalian = new ArrayList<Song>();
        songsItalian.addAll(songsBattisti);
        songsItalian.addAll(songsBattiato);

        ArrayList<Song> songsSpanish = new ArrayList<Song>();
        songsSpanish.addAll(songsRozalen);

        ArrayList<Song> songsEnglish = new ArrayList<Song>();
        songsEnglish.addAll(songsBeatles);

        ArrayList<Song> songsMediterranean = new ArrayList<Song>();
        songsMediterranean.addAll(songsBattisti);
        songsMediterranean.addAll(songsBattiato);
        songsMediterranean.addAll(songsRozalen);

        ArrayList<Song> allSongs = new ArrayList<Song>();
        allSongs.addAll(songsMediterranean);
        allSongs.addAll(songsEnglish);



        //Add the playlist name, the number of songs in the playlist, and the cover
        genrePlaylist.add(new Playlist("Playlist Italian",getResources().getStringArray(R.array.songs_playlist_battiato).length+
                getResources().getStringArray(R.array.songs_playlist_battisti).length,R.drawable.italian_playlist,songsItalian));
        genrePlaylist.add(new Playlist("Playlist Spanish",getResources().getStringArray(R.array.songs_playlist_rozalen).length,R.drawable.spanish_playlist,songsSpanish));
        genrePlaylist.add(new Playlist("Playlist English", getResources().getStringArray(R.array.songs_playlist_beatles).length,R.drawable.english_playlist,songsEnglish));
        genrePlaylist.add(new Playlist("Playlist Mediterranean",getResources().getStringArray(R.array.songs_playlist_battisti).length+
                getResources().getStringArray(R.array.songs_playlist_battiato).length+getResources().getStringArray(R.array.songs_playlist_rozalen).length,R.drawable.basic_playlist,songsMediterranean));
        genrePlaylist.add(new Playlist("All songs",getResources().getStringArray(R.array.songs_playlist_battisti).length+
                getResources().getStringArray(R.array.songs_playlist_battiato).length+
                getResources().getStringArray(R.array.songs_playlist_rozalen).length+getResources().getStringArray(R.array.songs_playlist_beatles).length,
                R.drawable.basic_playlist,allSongs));


        //Creates the RecyclerView with an horizontal layout with the playlists by genre
        //If you click on an element of the RecyclerView, you open the corresponding playlist.
        //It passes from the MainActivity to the PlaylistActivity
        //The position in the genrePlaylist is passed with the intent.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        GenreAdapterR itemsAdapter2 = new GenreAdapterR(this, genrePlaylist, new GenreAdapterR.onItemClickListener() {
            @Override
            public void onItemClick(Playlist playlist, int position) {
                Intent playlistIntent = new Intent(MainActivity.this, PlaylistActivity.class);
                playlistIntent.putExtra("playlist", genrePlaylist.get(position));
                playlistIntent.putExtra("position", position);
                startActivity(playlistIntent);
            }
        });

        RecyclerView listView = (RecyclerView) findViewById(R.id.genres);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(itemsAdapter2);

        //Finds the now playing TextView and sets an OnClickListener to pass from MainActivity to the NowPlayingActivity
        TextView nowPlaying = (TextView) findViewById(R.id.now_playing_text_view);

        // Set a click listener on that View
        nowPlaying.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the nowPlaying View is clicked on.
            @Override
            public void onClick(View view) {
                Intent nowPlayingIntent = new Intent(MainActivity.this, NowPlayingActivity.class);
                startActivity(nowPlayingIntent);
            }
        });

        //Finds the search TextView and sets an OnClickListener to pass from MainActivity to the SearchActivity
        TextView search = (TextView) findViewById(R.id.search_text_view);

        // Set a click listener on that View
        search.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the search View is clicked on.
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });
    }
}