package trainedge.beattiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button accbtn;
    private Button feedbackbutton;
    private Button sharebtn;
    private Button gameoptinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accbtn = (Button) findViewById(R.id.accountbutton);
        accbtn.setOnClickListener(this);
        feedbackbutton = (Button) findViewById(R.id.feedbackbutton);
        feedbackbutton.setOnClickListener(this);
        sharebtn = (Button) findViewById(R.id.sharebtn);
        sharebtn.setOnClickListener(this);
        gameoptinbtn = (Button) findViewById(R.id.gameoptinbtn);
        gameoptinbtn.setOnClickListener(this);


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
        if (v.getId()==R.id.sharebtn){
            Intent shareintent=new Intent(SettingActivity.this,FeedbackActivity.class);
            startActivity(shareintent);
        }
        if (v.getId()==R.id.gameoptinbtn){
            Intent gmointent=new Intent(SettingActivity.this,GameOptionActivity.class);
            startActivity(gmointent);
        }


    }
}
