package trainedge.beattiles;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageView ivanim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ivanim = (ImageView) findViewById(R.id.ivanim);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        ivanim.setAnimation(fadeInAnimation );

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Thread mythread=new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(3000);
                            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                mythread.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }

}