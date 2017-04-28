package trainedge.beattiles;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import static android.R.attr.path;
import static android.os.Looper.prepare;

public class SongPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent recIntent = getIntent();

        mediaPlayer = new MediaPlayer();

        int position=recIntent.getIntExtra("trainedge.beattiles.position",0);
        String path = recIntent.getExtras().getString("trainedge.beattile.path");
        Uri songUri= recIntent.getExtras().getParcelable("trainedge.beattile.uri");
        try {
            handleSongPlay();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleSongPlay() throws IOException {

        mediaPlayer.start();
        mediaPlayer.prepare();

    }

}
