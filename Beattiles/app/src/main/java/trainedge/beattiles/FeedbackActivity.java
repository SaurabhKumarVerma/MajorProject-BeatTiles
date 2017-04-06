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
        tvfeedbck = (TextView) findViewById(R.id.tvfeedbck);
        etemail = (EditText) findViewById(R.id.etemail);
        sendbtn = (Button) findViewById(R.id.sendbtn);

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

        if(v.getId()==R.id.imbtn_nolike){
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_sad){
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_dis){
            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_epl){
            Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.imbtn_hapy){
            Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
        }
    }
}
