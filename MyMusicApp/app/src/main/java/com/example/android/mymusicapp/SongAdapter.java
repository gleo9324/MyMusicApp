package com.example.android.mymusicapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SongAdapter extends ArrayAdapter<Song> {

    private ArrayList<Song> songs = null;
    private ArrayList<Song> arraylist;

    public SongAdapter(Activity context, ArrayList<Song> song) {
        super(context, 0, song);
        this.songs = song;
        this.arraylist = new ArrayList<Song>();
        this.arraylist.addAll(songs);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_text_view);
        // Get the version name from the current Song object and
        // set this text on the name TextView
        nameTextView.setText(currentSong.getSongName());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author_text_view);
        // Get the version number from the current Song object and
        // set this text on the number TextView
        authorTextView.setText(currentSong.getSongAuthor());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView durationTextView = (TextView) listItemView.findViewById(R.id.duration_text_view);
        // Get the version number from the current Song object and
        // set this text on the number TextView
        durationTextView.setText(String.valueOf(currentSong.getSongHours())+":"+String.valueOf(currentSong.getSongMinutes())+":"+String.valueOf(currentSong.getSongSeconds()));

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        songs.clear();
        if (charText.length() == 0) {
            songs.addAll(arraylist);
        } else {
            for (Song wp : arraylist) {
                if (wp.getSongName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    songs.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
