package com.example.project3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SongDAO {
    @Insert
    void addSong(Song s);

    @Delete
    void delSong(Song s);

    @Update
    void updateSong (Song s);

    @Query("SELECT * FROM Song where id = :id")
    Song getSong(int id);

    @Query("SELECT * FROM Song ORDER BY name")
    List<Song> getAllSongs();
}
