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

    }
}
