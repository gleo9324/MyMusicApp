package com.example.android.mymusicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NowPlayingActivity extends AppCompatActivity {
    int position = 0;
    Song currentSong;
    boolean playBool = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);

        // Setting play, back and forward buttons images

        ImageView playButton = (ImageView) findViewById(R.id.play_image_view);
        ImageView backButton = (ImageView) findViewById(R.id.back_image_view);
        ImageView forwardButton = (ImageView) findViewById(R.id.forward_image_view);

        playButton.setImageResource(R.drawable.play);
        backButton.setImageResource(R.drawable.back);
        forwardButton.setImageResource(R.drawable.forward);

        playButton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the search View is clicked on.
            @Override
            public void onClick(View view) {
                if (playBool) {
                    playButton.setImageResource(R.drawable.pause);
                    playBool = false;
                }else {
                    playButton.setImageResource(R.drawable.play);
                    playBool = true;
                }
            }
        });

        //Gets the song data from the playlist activity
        position = (int) getIntent().getIntExtra("position",0);
        Playlist currentPlaylist = (Playlist) getIntent().getSerializableExtra("playlist");

        if (currentPlaylist == null){
            currentSong = (Song) getIntent().getSerializableExtra("song");

            if (currentSong == null){

            }else{
                TextView playlistPlayingTextView = (TextView) findViewById(R.id.playlist_playing_text_view);
                ImageView playlistPlayingImageView = (ImageView) findViewById(R.id.playlist_playing_image_view);
                TextView songPlayingTextView = (TextView) findViewById(R.id.song_playing_text_view);
                TextView authorPlayingTextView = (TextView) findViewById(R.id.author_playing_text_view);

                playlistPlayingTextView.setText(" ");
                playlistPlayingImageView.setImageResource(R.drawable.basic_playlist);
                songPlayingTextView.setText(currentSong.getSongName());
                authorPlayingTextView.setText(currentSong.getSongAuthor());
            }

        }else {
            currentSong = (Song) currentPlaylist.getSongList().get(position);

            TextView playlistPlayingTextView = (TextView) findViewById(R.id.playlist_playing_text_view);
            ImageView playlistPlayingImageView = (ImageView) findViewById(R.id.playlist_playing_image_view);
            TextView songPlayingTextView = (TextView) findViewById(R.id.song_playing_text_view);
            TextView authorPlayingTextView = (TextView) findViewById(R.id.author_playing_text_view);

            playlistPlayingTextView.setText(currentPlaylist.getPlaylistName());
            playlistPlayingImageView.setImageResource(currentPlaylist.getImageResourceId());
            songPlayingTextView.setText(currentSong.getSongName());
            authorPlayingTextView.setText(currentSong.getSongAuthor());

            // Goes to the previous song of the playlist
            backButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (position == 0){

                    }else{
                        position = position - 1;
                    }
                    currentSong = (Song) currentPlaylist.getSongList().get(position);
                    songPlayingTextView.setText(currentSong.getSongName());
                    authorPlayingTextView.setText(currentSong.getSongAuthor());
                }
            });

            // Goes to the next song of the playlist
            forwardButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (position == currentPlaylist.getPlaylistSongs() - 1){

                    }else{
                        position = position + 1;
                    }
                    currentSong = (Song) currentPlaylist.getSongList().get(position);
                    songPlayingTextView.setText(currentSong.getSongName());
                    authorPlayingTextView.setText(currentSong.getSongAuthor());
                }
            });
        }

        //Finds the search TextView and sets an OnClickListener to pass from NowPlaying to the SearchActivity
        TextView search = (TextView) findViewById(R.id.search_text_view);

        search.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the search View is clicked on.
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(NowPlayingActivity.this, SearchActivity.class);
                startActivity(searchIntent);
            }
        });

        //Finds the search TextView and sets an OnClickListener to pass from NowPlaying to the MainActivity
        TextView home = (TextView) findViewById(R.id.home_text_view);

        // Set a click listener on that View
        home.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the home View is clicked on.
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(NowPlayingActivity.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

    }
}