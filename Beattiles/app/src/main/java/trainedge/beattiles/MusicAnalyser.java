package trainedge.beattiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Arrays;

public class MusicAnalyser extends AppCompatActivity {

    private CountDownView mCountDownView;
    private PianoTilesView mPianoTilesView;
    private RelativeLayout mMarkRela;
    private AlertScoreDialog mAlertScoreDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_analyser);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();

    }

    private void initView() {
        mPianoTilesView = (PianoTilesView) findViewById(R.id.pianoTilesView);
        mCountDownView = (CountDownView) findViewById(R.id.countTextView);
        mMarkRela = (RelativeLayout) findViewById(R.id.markRela);
        mCountDownView.setData(Arrays.asList("3", "2", "1", "start"));
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

}
