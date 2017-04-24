package trainedge.beattiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class AccountSettingActivity extends AppCompatActivity implements View.OnClickListener ,CompoundButton.OnCheckedChangeListener{


    private Switch switchCloudSyncOp;

    private SharedPreferences pref;
    private TextView username;
    private ImageView ivpicaso;
    private Button logout;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG="AccountSettingActivity";
    private FirebaseAuth mAuth;
    private Switch switchOpenWifi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth=FirebaseAuth.getInstance();

        ivpicaso = (ImageView) findViewById(R.id.ivpicaso);
        username = (TextView) findViewById(R.id.username);
        switchOpenWifi = (Switch) findViewById(R.id.switchOpenWifi);
        switchCloudSyncOp = (Switch) findViewById(R.id.switchCloudSyncOp);
        logout = (Button) findViewById(R.id.logout);

        pref = getSharedPreferences("AccountSetting",MODE_PRIVATE);

        logout.setOnClickListener(this);
        switchOpenWifi.setOnClickListener(this);
        switchCloudSyncOp.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    // if (mAuth.getCurrentUser()==null){
                    Intent logint = new Intent(AccountSettingActivity.this, LoginActivity.class);
                    startActivity(logint);
                    finish();

                    // }
                }
            }
        };


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                 String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String name = profile.getDisplayName();
                String email = profile.getEmail();
                Uri photoUrl = profile.getPhotoUrl();

                username.setText(name);
                Picasso.with(this)
                        .load(photoUrl)
                        .transform(new CircleTransform())
                        .into(ivpicaso);
            }






        }

        updateUI();
    }




    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }




    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View v) {

        //clear settings
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        //pref.edit().clear().apply();

        if (v.getId() == R.id.logout) {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            AccessToken.setCurrentAccessToken(null);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        SharedPreferences.Editor editor = pref.edit();
        switch (buttonView.getId()) {
            case R.id.switchOpenWifi:
                //code
                editor.putBoolean("wifi_option", isChecked);
                break;
            case R.id.switchCloudSyncOp:
                //code
                editor.putBoolean("cloud_option", isChecked);
                break;
        }
        //save setting
        editor.apply();

    }


    private class CircleTransform implements Transformation {
            @Override
            public Bitmap transform(Bitmap source) {
                int size = Math.min(source.getWidth(), source.getHeight());

                int x = (source.getWidth() - size) / 2;
                int y = (source.getHeight() - size) / 2;

                Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
                if (squaredBitmap != source) {
                    source.recycle();
                }

                Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                BitmapShader shader = new BitmapShader(squaredBitmap,
                        BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                paint.setShader(shader);
                paint.setAntiAlias(true);

                float r = size / 2f;
                canvas.drawCircle(r, r, r, paint);

                squaredBitmap.recycle();
                return bitmap;
            }

            @Override
            public String key() {
                return "circle";
            }
        }

    private void updateUI() {

        boolean wifi_state = pref.getBoolean("wifi_option", false);
        boolean cloud_state = pref.getBoolean("cloud_option", false);
        switchOpenWifi.setChecked(wifi_state);
        switchCloudSyncOp.setChecked(cloud_state);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
    }

    }





