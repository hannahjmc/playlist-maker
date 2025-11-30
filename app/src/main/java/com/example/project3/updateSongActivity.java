package com.example.project3;

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

import java.util.List;

public class updateSongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_song);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView nameLabel = findViewById(R.id.nameInput);
        TextView updateName = findViewById(R.id.newNameInput);
        TextView updateArtist = findViewById(R.id.newArtistInput);
        Button updateButton = findViewById(R.id.updateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ogSong = nameLabel.getText().toString().trim();
                String newSong = updateName.getText().toString().trim();
                String newArtist = updateArtist.getText().toString().trim();

                if (ogSong.isEmpty() || newSong.isEmpty() || newArtist.isEmpty()) {
                    Toast.makeText(updateSongActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                SongDatabase db = SongDatabase.getInstance(updateSongActivity.this);

                List<Song> allSongs = db.songDAO().getAllSongs();
                Song match = null;

                for (Song s : allSongs) {
                    if (s.getName().equalsIgnoreCase(ogSong)) {
                        match = s;
                        break;
                    }
                }

                if (match == null) {
                    Toast.makeText(updateSongActivity.this, "Song not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                Artist artist = db.artistDAO().getArtistByName(newArtist);

                if (artist == null) {
                    Toast.makeText(updateSongActivity.this, "Artist not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                match.setName(newSong);
                match.setArtistId(artist.getArtistId());

                db.songDAO().updateSong(match);

                Toast.makeText(updateSongActivity.this, "Song updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}