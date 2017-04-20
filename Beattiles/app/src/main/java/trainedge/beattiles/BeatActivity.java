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

public class BeatActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MusicRetriever";
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
        ngamebtn.setOnClickListener(this);


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
            Intent settingint = new Intent(BeatActivity.this, SettingActivity.class);
            startActivity(settingint);
            return true;
        }
/*
        if (id == R.id.action_share) {

            Intent shareint = new Intent(BeatActivity.this, ShareActivity.class);
            startActivity(shareint);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ngamebtn) ;
        {

            Intent musicintent=new Intent(BeatActivity.this,MusicActivity.class);
            startActivity(musicintent);

        }

    }
}












