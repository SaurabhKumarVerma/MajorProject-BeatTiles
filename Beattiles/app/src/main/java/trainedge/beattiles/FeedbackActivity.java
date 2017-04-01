package trainedge.beattiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etname;
    private EditText etemail;
    private EditText etcomment;
    private Button btnsend;
    private FirebaseDatabase db;
    private DatabaseReference commentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etname = (EditText) findViewById(R.id.etname);
        etemail = (EditText) findViewById(R.id.etemail);
        etcomment = (EditText) findViewById(R.id.etcomment);
        btnsend = (Button) findViewById(R.id.btnsend);
        db = FirebaseDatabase.getInstance();
        commentRef = db.getReference("comments");
        btnsend.setOnClickListener(this) ;
    }
    @Override
    public void onClick(View v){
        String name =etname.getText().toString();
        String emailid = etemail.getText().toString();
        String comment = etcomment.getText().toString();
        if (name.isEmpty()) {
            etname.setError("Please enter your name");
            return;
        }
        if (emailid.isEmpty()) {
            etemail.setError("Please enter your email");
            return;
        }
        if (comment.isEmpty()) {
            etcomment.setError("Please add your comment for better experience");
            return;
        }
        //firebase upload
        HashMap<String,String> map=new HashMap<>();
        map.put("msg", comment);
        map.put("user", name);
        map.put("user", emailid);

        commentRef.push().setValue(map);
        etname.setText("");
        etemail.setText("");
        etcomment.setText("");

        }


    }


