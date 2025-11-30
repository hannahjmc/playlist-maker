package com.example.project3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Song {
    @PrimaryKey(autoGenerate = true)
    int id;
    int artistId;

    String name;

    public Song (String name, int artistId) {
        this.name = name;
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistId(int artistId) { this.artistId = artistId; }

    public String toString(){
        return this.name;
    }
}
