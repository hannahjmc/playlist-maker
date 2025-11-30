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

public class deleteSongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_song);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button delButton = findViewById(R.id.delButton);
        TextView nameLabel = findViewById(R.id.nameInput);

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameLabel.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(deleteSongActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                }

                SongDatabase db = SongDatabase.getInstance(deleteSongActivity.this);

                List<Song> allSongs = db.songDAO().getAllSongs();
                Song match = null;

                for (Song s : allSongs) {
                    if (s.getName().equalsIgnoreCase(name)) {
                        match = s;
                        break;
                    }
                }

                if (match == null) {
                    Toast.makeText(deleteSongActivity.this, "Song not found", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.songDAO().delSong(match);

                Toast.makeText(deleteSongActivity.this, "Song deleted!", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

    }
}