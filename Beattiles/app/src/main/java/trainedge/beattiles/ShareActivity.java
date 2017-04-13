package trainedge.beattiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ShareActivity extends AppCompatActivity {

    Button btnshare;
    Intent shareIntent;
    String shareBody= "This is an amazing app, you must be try it out!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        btnshare = (Button) findViewById(R.id.button3);

        btnshare.setOnClickListener(View.onClickListener(){
            public void onClick(View view){
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("Text/Plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "my_app");
                shareIntent.putExtra(Intent.EXTRA_TEXT, sharebody);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }





        }

    }

}
