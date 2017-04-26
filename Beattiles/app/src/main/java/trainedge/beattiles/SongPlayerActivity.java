package trainedge.beattiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class SongPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent recIntent = getIntent();

        int position=recIntent.getIntExtra("trainedge.beattiles.position",0);
        String path = recIntent.getExtras().getString("trainedge.beattile.path");
        Uri songUri= recIntent.getExtras().getParcelable("trainedge.beattile.uri");
        handleSongPlay();

    }

    private void handleSongPlay() {

    }

}
