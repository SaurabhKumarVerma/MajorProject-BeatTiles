package trainedge.beattiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.ImageButton;
import android.widget.Toast;
=======
>>>>>>> origin/master

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
<<<<<<< HEAD
    private ImageButton sadbtn;
    private ImageButton unlikebtn;
    private ImageButton disaptbtn;
    private ImageButton explessbtn;
    private ImageButton happybtn;
=======
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
        sadbtn = (ImageButton) findViewById(R.id.sadbtn);
        unlikebtn = (ImageButton) findViewById(R.id.unlikebtn);
        disaptbtn = (ImageButton) findViewById(R.id.disaptbtn);
        explessbtn = (ImageButton) findViewById(R.id.explessbtn);
        happybtn = (ImageButton) findViewById(R.id.happybtn);

=======
        etname = (EditText) findViewById(R.id.etname);
>>>>>>> origin/master
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
<<<<<<< HEAD
        if (v.getId()==R.id.sadbtn){

            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
             }
          if(v.getId()==R.id.unlikebtn){

              Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.disaptbtn){

            Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.explessbtn){

            Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.happybtn){

            Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
        }

=======
>>>>>>> origin/master
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
<<<<<<< HEAD

=======
>>>>>>> origin/master
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


