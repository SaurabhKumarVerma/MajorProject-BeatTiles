package trainedge.beattiles;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BeatActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG ="MusicRetriever" ;
    private Button ngamebtn;
    private ContentResolver mContentResolver;
    private ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ngamebtn = (Button) findViewById(R.id.ngamebtn);
        ngamebtn.setOnClickListener(this );


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingint=new Intent(BeatActivity.this,SettingActivity.class);
            startActivity(settingint);
            return true;
        }

        if(id==R.id.action_share){

            Intent shareint=new Intent(BeatActivity.this,ShareActivity.class);
            startActivity(shareint);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.ngamebtn);{

            MusicRetriever();

        }

    }

    private void MusicRetriever() {

        class MusicRetriever {
            final String TAG = "MusicRetriever";
            ContentResolver mContentResolver;
            // the items (songs) we have queried
            List<Item> mItems = new ArrayList<Item>();
            Random mRandom = new Random();
            public MusicRetriever(ContentResolver cr) {
                mContentResolver = cr;
            }
            /**
             * Loads music data. This method may take long, so be sure to call it asynchronously without
             * blocking the main thread.
             */
            public void prepare() {
                Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                Log.i(TAG, "Querying media...");
                Log.i(TAG, "URI: " + uri.toString());
                // Perform a query on the content resolver. The URI we're passing specifies that we
                // want to query for all audio media on external storage (e.g. SD card)
                Cursor cur = mContentResolver.query(uri, null,
                        MediaStore.Audio.Media.IS_MUSIC + " = 1", null, null);
                Log.i(TAG, "Query finished. " + (cur == null ? "Returned NULL." : "Returned a cursor."));
                if (cur == null) {
                    // Query failed...
                    Log.e(TAG, "Failed to retrieve music: cursor is null :-(");
                    return;
                }
                if (!cur.moveToFirst()) {
                    // Nothing to query. There is no music on the device. How boring.
                    Log.e(TAG, "Failed to move cursor to first row (no query results).");
                    return;
                }
                Log.i(TAG, "Listing...");
                // retrieve the indices of the columns where the ID, title, etc. of the song are
                int artistColumn = cur.getColumnIndex(MediaStore.Audio.Media.ARTIST);
                int titleColumn = cur.getColumnIndex(MediaStore.Audio.Media.TITLE);
                int albumColumn = cur.getColumnIndex(MediaStore.Audio.Media.ALBUM);
                int durationColumn = cur.getColumnIndex(MediaStore.Audio.Media.DURATION);
                int idColumn = cur.getColumnIndex(MediaStore.Audio.Media._ID);
                Log.i(TAG, "Title column index: " + String.valueOf(titleColumn));
                Log.i(TAG, "ID column index: " + String.valueOf(titleColumn));
                // add each song to mItems
                do {
                    Log.i(TAG, "ID: " + cur.getString(idColumn) + " Title: " + cur.getString(titleColumn));
                    mItems.add(new Item(
                            cur.getLong(idColumn),
                            cur.getString(artistColumn),
                            cur.getString(titleColumn),
                            cur.getString(albumColumn),
                            cur.getLong(durationColumn)));
                } while (cur.moveToNext());
                Log.i(TAG, "Done querying media. MusicRetriever is ready.");
            }
            public ContentResolver getContentResolver() {
                return mContentResolver;
            }
            /** Returns a random Item. If there are no items available, returns null. */
            public Item getRandomItem() {
                if (mItems.size() <= 0) return null;
                return mItems.get(mRandom.nextInt(mItems.size()));
            }
            class Item {
                long id;
                String artist;
                String title;
                String album;
                long duration;
                public Item(long id, String artist, String title, String album, long duration) {
                    this.id = id;
                    this.artist = artist;
                    this.title = title;
                    this.album = album;
                    this.duration = duration;
                }
                public long getId() {
                    return id;
                }
                public String getArtist() {
                    return artist;
                }
                public String getTitle() {
                    return title;
                }
                public String getAlbum() {
                    return album;
                }
                public long getDuration() {
                    return duration;
                }
                public Uri getURI() {
                    return ContentUris.withAppendedId(
                            android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
                }
            }
        }

    }


}





