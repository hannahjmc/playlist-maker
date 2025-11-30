package com.example.project3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class addSongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_song);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button addButton = findViewById(R.id.addButton);
        TextView nameLabel = findViewById(R.id.nameInput);
        TextView artistLabel = findViewById(R.id.artistInput);

        addButton.setOnClickListener(v -> {
            String name = nameLabel.getText().toString().trim();
            String artistName = artistLabel.getText().toString().trim();

            if (name.isEmpty() || artistName.isEmpty()) {
                Toast.makeText(this, "Please enter both name and artist", Toast.LENGTH_SHORT).show();
                return;
            }

            SongDatabase db = SongDatabase.getInstance(this);

            Artist artist = db.artistDAO().getArtistByName(artistName);

            if (artist == null) {
                artist = new Artist(artistName);
                db.artistDAO().addArtist(artist);

                artist = db.artistDAO().getArtistByName(artistName);
            }

            Song newSong = new Song(name, artist.getArtistId());
            db.songDAO().addSong(newSong);

            Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}