package trainedge.beattiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imbtn_nolike;
    private ImageButton imbtn_sad;
    private ImageButton imbtn_dis;
    private ImageButton imbtn_epl;
    private ImageButton imbtn_hapy;
    private TextView tvfeedbck;
    private EditText etemail;
    private Button sendbtn;
    private FirebaseDatabase db;
    private DatabaseReference commentsRfs;
    private int rating;
    private EditText edfeedbck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imbtn_nolike = (ImageButton) findViewById(R.id.imbtn_nolike);
        imbtn_sad = (ImageButton) findViewById(R.id.imbtn_sad);
        imbtn_dis = (ImageButton) findViewById(R.id.imbtn_dis);
        imbtn_epl = (ImageButton) findViewById(R.id.imbtn_epl);
        imbtn_hapy = (ImageButton) findViewById(R.id.imbtn_hapy);
        edfeedbck = (EditText) findViewById(R.id.edfeedbck);
        etemail = (EditText) findViewById(R.id.etemail);

        imbtn_nolike.setOnClickListener(this);
        imbtn_sad.setOnClickListener(this);
        imbtn_dis.setOnClickListener(this);
        imbtn_epl.setOnClickListener(this);
        imbtn_hapy.setOnClickListener(this);


        db = FirebaseDatabase.getInstance();
        commentsRfs = db.getReference("COMMENTS");
        sendbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email=etemail.getText().toString();
        String commentd=tvfeedbck.getText().toString();

        if (email.isEmpty()){

            etemail.setError("Fill Your Email");
            return;
        }
        if (commentd.isEmpty()){

            tvfeedbck.setError("Fill Your Comments");
            return;
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("msg",commentd);
        map.put("User",email);
        commentsRfs.push().setValue(map);
        etemail.setText("");
        tvfeedbck.setText("");

        if(v.getId()==R.id.imbtn_nolike)
        {
            rating =1;
            Toast.makeText(this, "HATED", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_sad)
        {
            rating=2;
            Toast.makeText(this, "LESS HATED", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_dis)
        {
            rating=3;
            Toast.makeText(this, "NORMAL", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_epl)
        {
            rating=4;
            Toast.makeText(this, "LIKE IT", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_hapy)
        {
            rating=5;
            Toast.makeText(this, "LOVE IT", Toast.LENGTH_SHORT).show();
        }
    }
}
