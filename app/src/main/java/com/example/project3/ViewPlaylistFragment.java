package com.example.project3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPlaylistFragment extends Fragment {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private List<Song> songList = new ArrayList<>();
    private List<Artist> artistList = new ArrayList<>();

    private SongDatabase db;

    public ViewPlaylistFragment() {}

    public static ViewPlaylistFragment newInstance(String p1, String p2) {
        ViewPlaylistFragment frag = new ViewPlaylistFragment();
        Bundle args = new Bundle();
        args.putString("param1", p1);
        args.putString("param2", p2);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = SongDatabase.getInstance(requireContext());

        // Insert test data ONCE
        //insertDummyDataOnce();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_playlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.playlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load data from DB
        loadDataFromDB();

        // Create adapter
        adapter = new CustomAdapter(songList, artistList, (song, pos) -> {
            Toast.makeText(getContext(), "Editing: " + song.name, Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);

        Button backButton = view.findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    // -------------------------------------------------------------
    // Inserts test data ONCE — will NOT duplicate because names repeat
    // -------------------------------------------------------------
    private void insertDummyDataOnce() {

        // Prevent duplicates
        if (!db.artistDAO().getAllArtists().isEmpty()) return;

        Artist a1 = new Artist("Taylor Swift");
        Artist a2 = new Artist("Kendrick Lamar");
        Artist a3 = new Artist("Coldplay");

        // Inserts (void methods) — Room auto-fills IDs
        db.artistDAO().addArtist(a1);
        db.artistDAO().addArtist(a2);
        db.artistDAO().addArtist(a3);

        db.songDAO().addSong(new Song("Love Story", a1.id));
        db.songDAO().addSong(new Song("Alright", a2.id));
        db.songDAO().addSong(new Song("Yellow", a3.id));
    }

    // -------------------------------------------------------------
    // Loads data for RecyclerView using your actual DAO methods
    // -------------------------------------------------------------
    private void loadDataFromDB() {

        artistList.clear();
        artistList.addAll(db.artistDAO().getAllArtists());

        songList.clear();
        songList.addAll(db.songDAO().getAllSongs());
    }
}

