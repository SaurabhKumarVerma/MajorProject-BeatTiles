package trainedge.beattiles;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import java.util.List;

public class MusicActivity extends AppCompatActivity, RecyclerView.Adapter<MusicAdapter.MusicCardHolder> implements PrepareMusicRetrieverTask.MusicRetrieverPreparedListener {

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
        //recycler holder
    }


    @Override
    public MusicAdapter.MusicCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MusicAdapter.MusicCardHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
