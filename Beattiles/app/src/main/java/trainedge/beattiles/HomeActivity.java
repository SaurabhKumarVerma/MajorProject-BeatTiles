package trainedge.beattiles;

import android.*;
import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

import static com.facebook.FacebookSdk.getApplicationContext;


public class HomeActivity extends Activity {

    private static final int REQUEST_STORAGE = 332;
    private ImageView ivanim;
    private TextView tvbeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_home);


        tvbeat = (TextView) findViewById(R.id.tvbeat);

        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        tvbeat.setAnimation(fadeInAnimation);

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Thread mythread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(2000);
                            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                                Intent intent = new Intent(getApplicationContext(), SliderActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent homeIntent = new Intent(getApplicationContext(), BeatActivity.class);
                                startActivity(homeIntent);
                                finish();

                            }
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

