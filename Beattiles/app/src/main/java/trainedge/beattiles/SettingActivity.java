package trainedge.beattiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button accbt;
    private Button feedbackbutto;
    private Button sharebt;
    private Button gameoptinbt;
    private Button aboutbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        accbt = (Button) findViewById(R.id.accountbutton);
        feedbackbutto = (Button) findViewById(R.id.feedbackbutton);

        gameoptinbt = (Button) findViewById(R.id.gameoptinbtn);
        aboutbt = (Button) findViewById(R.id.aboutbtn);


        accbt.setOnClickListener(this);
        feedbackbutto.setOnClickListener(this);

        gameoptinbt.setOnClickListener(this);
        aboutbt.setOnClickListener(this);

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


    }
}
