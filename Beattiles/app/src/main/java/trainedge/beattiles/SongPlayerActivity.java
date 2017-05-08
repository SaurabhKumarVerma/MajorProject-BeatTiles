package trainedge.beattiles;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.io.IOException;

public class SongPlayerActivity extends AppCompatActivity {


    private int position;
    private Uri songUri;
    private String path;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent recIntent = getIntent();


        position = recIntent.getIntExtra("trainedge.beattiles.position", 0);
        path = recIntent.getExtras().getString("trainedge.beattiles.path");
        songUri = recIntent.getExtras().getParcelable("trainedge.beattiles.uri");


        try {
            handleSongPlay();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleSongPlay() throws IOException {


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getApplicationContext(), songUri);
        mediaPlayer.prepare();
        mediaPlayer.start();


    }

    @Override
    public void onBackPressed() {
        try {

            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            }
        } catch (Exception e) {

        }
        finish();
    }
}
