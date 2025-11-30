package com.example.project3;

import androidx.room.Embedded;
import androidx.room.Relation;
import androidx.room.Transaction;

import java.util.List;

public class ArtistsWithSongs {
    @Embedded
    public Artist artist;
    @Relation(
            parentColumn = "id",
            entityColumn = "artistId"
    )
    public List<Song> artistSongs;
}
