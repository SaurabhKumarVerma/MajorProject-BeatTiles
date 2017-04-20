package trainedge.beattiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    Intent shareIntent;
    String shareBody= "This is an amazing app, you must be try it out!";
    private Button accountbutton;
    private Button feedbackbutton;
    private Button gameoptinbtn;
    private Button aboutbtn;
    private Button sharebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accountbutton = (Button) findViewById(R.id.accountbutton);
        feedbackbutton = (Button) findViewById(R.id.feedbackbutton);
        gameoptinbtn = (Button) findViewById(R.id.gameoptinbtn);
        aboutbtn = (Button) findViewById(R.id.aboutbtn);
        sharebutton = (Button) findViewById(R.id.sharebutton);

        accountbutton.setOnClickListener(this);
        feedbackbutton.setOnClickListener(this);
        gameoptinbtn.setOnClickListener(this);
        aboutbtn.setOnClickListener(this);
        sharebutton.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.accountbutton){
            Intent accintent = new Intent(SettingActivity.this,AccountSettingActivity.class);
            startActivity(accintent);
        }
        if (v.getId()==R.id.feedbackbutton){
            Intent feedintent=new Intent(SettingActivity.this,FeedbackActivity.class);
            startActivity(feedintent);
        }


        if (v.getId()==R.id.gameoptinbtn){
            Intent gmointent=new Intent(SettingActivity.this,GameOptionActivity.class);
            startActivity(gmointent);
        }
        if (v.getId()==R.id.aboutbtn){

            Intent aboutintent=new Intent(SettingActivity.this,AboutActivity.class);
            startActivity(aboutintent);
        }
        if (v.getId()==R.id.sharebutton){

            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("Text/Plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "my_app");
            shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(shareIntent, "Share via"));

        }


    }
}
