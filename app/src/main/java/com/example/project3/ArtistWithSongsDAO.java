package com.example.project3;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ArtistWithSongsDAO {
    @Transaction
    @Query("SELECT * FROM Artist")
    public List<ArtistsWithSongs> getAllArtistsWithSongs();
}
