package com.example.project3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyHandler> {

    private List<Song> songList;
    private List<Artist> artistList;
    private OnEditClickListener listener;

    public interface OnEditClickListener {
        void onEditClick(Song song, int position);
    }



    public CustomAdapter(List<Song> songList,
                         List<Artist> artistList,
                         OnEditClickListener listener) {
        this.songList = songList;
        this.artistList = artistList;
        this.listener = listener;
    }

    public static class MyHandler extends RecyclerView.ViewHolder {
        TextView song;
        TextView artist;

        public MyHandler(@NonNull View itemView) {
            super(itemView);
            song = itemView.findViewById(R.id.songLabel);
            artist = itemView.findViewById(R.id.artistLabel);
        }
    }

    @NonNull
    @Override
    public MyHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_layout, parent, false);
        return new MyHandler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHandler holder, int position) {
        Song theSong = songList.get(position);
        holder.song.setText(theSong.name);

        // Find artist by ID
        Artist matchedArtist = null;
        for (Artist a : artistList) {
            if (a.id == theSong.artistId) {
                matchedArtist = a;
                break;
            }
        }

        if (matchedArtist != null) {
            holder.artist.setText(matchedArtist.getName());
        } else {
            holder.artist.setText("Unknown Artist");
        }
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}
