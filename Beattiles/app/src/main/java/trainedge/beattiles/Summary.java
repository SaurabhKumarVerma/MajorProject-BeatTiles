package trainedge.beattiles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.vodyasov.amr.AudiostreamMetadataManager;
import com.vodyasov.amr.OnNewMetadataListener;
import com.vodyasov.amr.UserAgent;

import java.util.List;

public class Summary extends AppCompatActivity implements View.OnClickListener {

    private int position;
    private String path;
    private Uri songUri;
    private TextView tvname;
    private TextView tvaudio;
    private TextView tvgener;
    private TextView tvbitrate;
    private TextView tvdecription;
    private FloatingActionButton fabAnalyse;
    private FloatingActionButton fabGo;
    private MediaMetadataRetriever metaRetriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent recIntent = getIntent();


        path = recIntent.getExtras().getString("trainedge.beattiles.path");
        songUri = recIntent.getExtras().getParcelable("trainedge.beattiles.uri");
        tvname = (TextView) findViewById(R.id.tvname);
        tvaudio = (TextView) findViewById(R.id.tvaudio);
        tvgener = (TextView) findViewById(R.id.tvgener);
        tvbitrate = (TextView) findViewById(R.id.tvbitrate);

       ;
        fabGo = (FloatingActionButton) findViewById(R.id.fabGo);

        fabGo.setOnClickListener(this);

        metaRetriver = new MediaMetadataRetriever();
        metaRetriver.setDataSource(this, songUri);
        try {
            byte[] art = metaRetriver.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory.decodeByteArray(art, 0, art.length);
            //album_art.setImageBitmap(songImage);
            String album = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String artist = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String genre = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);
            String bitrate = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
            String duration = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            String title = metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            tvname.setText(title);
            tvaudio.setText(artist);
            tvgener.setText(genre);
            tvbitrate.setText(bitrate);

        } catch (Exception e) {
        }


        /**/

    }

    public void startGame() {
        Intent i = new Intent(this, MusicAnalyser.class);
        i.putExtra("trainedge.beattiles.path", path);
        i.putExtra("trainedge.beattiles.uri", songUri);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fabGo:
                startGame();
                break;
        }


    }

    private void startAnalysis() {
        //Start parsing
    }

    public static class BpmCalculator {
        private static final Long MILLISECONDS_IN_A_MINUTE = 60000L;
        public ArrayList<Long> times;
        private boolean isRecording;

        public BpmCalculator() {
            times = new ArrayList<Long>();
            isRecording = false;
        }

        public void recordTime() {
            Long time = System.currentTimeMillis();
            times.add(time);
            isRecording = true;
        }

        public int getBpm() {
            ArrayList<Long> deltas = getDeltas();
            return calculateBpm(deltas);
        }

        public void clearTimes() {
            times.clear();
            isRecording = false;
        }

        private ArrayList<Long> getDeltas() {
            ArrayList<Long> deltas = new ArrayList<Long>();

            for (int i = 0; i < times.size() - 1; i++) {
                Long delta = times.get(i + 1) - times.get(i);
                deltas.add(delta);
            }

            return deltas;
        }

        private int calculateBpm(ArrayList<Long> deltas) {
            Long sum = 0L;

            for (Long delta : deltas) {
                sum = sum + delta;
            }

            Long average = sum / deltas.size();

            return (int) (MILLISECONDS_IN_A_MINUTE / average);
        }

        public boolean isRecording() {
            return isRecording;
        }
    }

}
