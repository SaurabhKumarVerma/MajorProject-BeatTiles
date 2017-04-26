package trainedge.beattiles;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;


public class HomeActivity extends Activity {

    private static final int REQUEST_STORAGE = 332;
    private static final String TAG = "str";
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
                            if (isStoragePermissionGranted()) {
                                handleIntent();
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

    private void handleIntent() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            jumpToSlider();
        } else {
            jumpToBeat();

        }
    }

    private void jumpToBeat() {
        Intent homeIntent = new Intent(getApplicationContext(), BeatActivity.class);
        startActivity(homeIntent);
        finish();
    }

    private void jumpToSlider() {
        Intent intent = new Intent(getApplicationContext(), SliderActivity.class);
        startActivity(intent);
        finish();
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            isStoragePermissionGranted();
        }else{
            handleIntent();
        }
    }

}

