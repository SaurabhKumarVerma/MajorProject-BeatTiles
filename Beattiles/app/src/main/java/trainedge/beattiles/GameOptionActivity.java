package trainedge.beattiles;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class GameOptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    private Button btnopt;
    private Spinner spn1;
    private Spinner spn2;
    private Switch swopt;
    private Switch sw1;
    private Switch sw2;
    private Spinner sp1;
    private Switch switchvib;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pref=getSharedPreferences("GameOptionActivity",MODE_PRIVATE);

        btnopt = (Button) findViewById(R.id.btnopt);
        sp1 = (Spinner) findViewById(R.id.spn1);
        sp1.setOnItemSelectedListener(this);
        sw1 = (Switch) findViewById(R.id.sw1);
        switchvib = (Switch) findViewById(R.id.switchvib);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Tilescolor, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        switchvib.setOnClickListener(this);



        updateUI();


    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {




    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        SharedPreferences.Editor editor=pref.edit();

        switch (buttonView.getId()) {
            case R.id.switchvib:
                //code
                editor.putBoolean("Vibratio_option", isChecked);
                break;

        }
        editor.apply();


    }

    private void updateUI() {

        boolean vibration_state = pref.getBoolean("wifi_option", false);

        switchvib.setChecked(vibration_state);

    }
}
