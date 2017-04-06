package trainedge.beattiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

public class GameOptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button btnopt;
    private Spinner spn1;
    private Spinner spn2;
    private Switch swopt;
    private Switch sw1;
    private Switch sw2;
    private Spinner sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_option);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnopt = (Button) findViewById(R.id.btnopt);
        sp1 = (Spinner) findViewById(R.id.spn1);
        sp1.setOnItemSelectedListener(this);
        sw1 = (Switch) findViewById(R.id.sw1);
        sw2 = (Switch) findViewById(R.id.sw2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Tilescolor, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
