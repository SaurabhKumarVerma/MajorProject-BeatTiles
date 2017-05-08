package trainedge.beattiles;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.Arrays;

public class MusicAnalyser extends AppCompatActivity {

    private CountDownView mCountDownView;
    private PianoTilesView mPianoTilesView;
    private RelativeLayout mMarkRela;
    private AlertScoreDialog mAlertScoreDialog;
    private int position;
    private Uri songUri;
    private String path;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_analyser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getData();
        initView();




    }

    private void getData() {
        Intent recIntent = getIntent();
        path = recIntent.getExtras().getString("trainedge.beattiles.path");
        songUri = recIntent.getExtras().getParcelable("trainedge.beattiles.uri");
    }

    private void handleSongPlay() throws IOException {

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(getApplicationContext(), songUri);
        mediaPlayer.prepare();
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        displaySummary(mp);
                    }
                });

    }

    private void displaySummary(MediaPlayer mp) {
        try {
            mPianoTilesView.endGame();
        }catch (Exception e){

        }
    }

    private void initView() {
        mPianoTilesView = (PianoTilesView) findViewById(R.id.pianoTilesView);
        mCountDownView = (CountDownView) findViewById(R.id.countTextView);
        mMarkRela = (RelativeLayout) findViewById(R.id.markRela);
        mCountDownView.setData(Arrays.asList("3", "2", "1", "start"));
        try {
            handleSongPlay();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCountDownView.init();
        mCountDownView.setCountDownListener(new CountDownView.CountDownListener() {
            @Override
            public void finish() {
                mMarkRela.setVisibility(View.GONE);
                mPianoTilesView.setZOrderOnTop(true);
                mPianoTilesView.startGame();
            }
        });

        mAlertScoreDialog = new AlertScoreDialog.Builder(MusicAnalyser.this)
                .setFinishClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        mAlertScoreDialog.dismiss();
                    }
                })
                .setRestartClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAlertScoreDialog.dismiss();
                        mPianoTilesView.restart();
                        mMarkRela.setVisibility(View.VISIBLE);
                        mCountDownView.init();

                    }
                })
                .builder();


        mPianoTilesView.setGameListener(new PianoTilesView.GameListener() {
            @Override
            public void gameEnd(final String number) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mAlertScoreDialog != null) {
                            mAlertScoreDialog.setScore(number);
                            mAlertScoreDialog.show();


                        }

                    }
                });

            }
        });
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
