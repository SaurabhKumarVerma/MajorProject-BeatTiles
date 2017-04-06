package trainedge.beattiles;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AccountSettingActivity extends AppCompatActivity  {


    private Switch switchCloudSyncOp;
    private EditText defaultemail;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // switchCloudSyncOp =(Switch) findViewById(R.id.switchCloudSyncOp);
        defaultemail = (EditText) findViewById(R.id.defaultemail);
        //SharedPref obj
        pref = getSharedPreferences("setting_pref", MODE_PRIVATE);
        //listner
        switchCloudSyncOp.setOnCheckedChangeListener(this);
        defaultemail.addTextChangedListener( this);

        //read pref to update ui too
        updateUI();*/
    }


 /*@Override
         public void onClick(View v){

     SharedPreferences.Editor editor = pref.edit();
     editor.clear();
     editor.apply();
     //pref.edit().clear().apply();

 }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences.Editor editor = pref.edit();
        switch (buttonView.getId()) {

            case R.id.switchCloudSyncOp:
                //code
                editor.putBoolean("cloud_option", isChecked);
                break;
        }
        //save setting
        editor.apply();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //keep blank
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //keep blank

    }

    @Override
    public void afterTextChanged(Editable s) {
        //saving email address
        SharedPreferences.Editor editor = pref.edit();
        String email = s.toString();
        if (email.isEmpty() && email.length() < 10 && !email.contains("@")) {
            defaultemail.setError("Please give default email address");
            return;
        }
        editor.putString("def_email", email);
        editor.apply();

    }
    @Override
    public void onBackPressed(){

        super.onBackPressed();
        Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
    }

    private void updateUI() {

    }*/


}

