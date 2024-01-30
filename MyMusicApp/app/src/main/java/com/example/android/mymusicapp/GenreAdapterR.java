package com.example.android.mymusicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GenreAdapterR extends RecyclerView.Adapter<GenreAdapterR.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Playlist> playlist;
    private onItemClickListener listener;

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param localPlaylist ArrayList containing the data to populate views to be used
     * by RecyclerView.
     */
    public GenreAdapterR(Context context, ArrayList<Playlist> localPlaylist,onItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.playlist = localPlaylist;
        this.listener = listener;
    }

    //Provides the reference views that will be used as a custom ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView2;
        private final ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            textView1 = (TextView) view.findViewById(R.id.name_text_view);
            textView2 = (TextView) view.findViewById(R.id.songs_text_view);
            imageView = (ImageView) view.findViewById(R.id.cover_image_view);
        }

        public TextView getTextView1() {
            return textView1;
        }
        public TextView getTextView2() {
            return textView2;
        }
        public ImageView getImageView() {
            return imageView;
        }

        public void bind(final Playlist item, final GenreAdapterR.onItemClickListener listener, int position) {

            // Define click listener for the ViewHolder's View
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClick(item, position);

                }
            });
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.genre_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        Playlist currentPlaylist = playlist.get(position);

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView1().setText(currentPlaylist.getPlaylistName());
        viewHolder.getTextView2().setText(String.valueOf(currentPlaylist.getPlaylistSongs()));
        viewHolder.getImageView().setImageResource(currentPlaylist.getImageResourceId());
        viewHolder.bind(currentPlaylist, listener, position);

    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return playlist.size();
    }

    //set the OnClickListener on the items of the RecyclerView
    public interface onItemClickListener {
        void onItemClick(Playlist playlist, int position);

    }

}