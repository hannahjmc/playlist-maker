package com.example.project3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ArtistDAO {
    @Insert
    void addArtist(Artist a);

    @Update
    void updateArtist(Artist a);

    @Delete
    void delArtist(Artist a);

    @Query("SELECT * FROM Artist WHERE id = :id")
    Artist getArtist(int id);

    @Query("SELECT * FROM Artist WHERE name = :name LIMIT 1")
    Artist getArtistByName(String name);

    @Query("SELECT * FROM Artist")
    List<Artist> getAllArtists();
}
