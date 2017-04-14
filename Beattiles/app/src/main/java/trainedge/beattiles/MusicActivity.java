package trainedge.beattiles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class MusicActivity extends AppCompatActivity implements PrepareMusicRetrieverTask.MusicRetrieverPreparedListener {

    private MusicRetriever mRetriever;
    private List<MusicRetriever.Item> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRetriever = new MusicRetriever(getContentResolver());
        (new PrepareMusicRetrieverTask(mRetriever,this)).execute();

    }

    @Override
    public void onMusicRetrieverPrepared() {
        songList = mRetriever.mItems;

        //pass Recycler View Adapter
        
    }




}
