package trainedge.beattiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {

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
    private Button sendbtn1;

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
        sendbtn1 = (Button) findViewById(R.id.sendbtn);


        imbtn_nolike.setOnClickListener(this);
        imbtn_sad.setOnClickListener(this);
        imbtn_dis.setOnClickListener(this);
        imbtn_epl.setOnClickListener(this);
        imbtn_hapy.setOnClickListener(this);
        sendbtn1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
       /* String email=etemail.getText().toString();
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
        tvfeedbck.setText("");*/

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
        if(v.getId()==R.id.sendbtn){
            send();
        }
    }




    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    String email=s.toString();
        if(email.isEmpty() || email.length()<10 || !email.contains(".com"));
        etemail.setError("Please give a correct email.com");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void send() {

        String str1 = edfeedbck.getText().toString();
        String str2 = etemail.getText().toString();
        if (str1.isEmpty()) {
            edfeedbck.setError("Recquired");
            return;
        }

        if(!str2.contains("@") && !str2.contains(".com") && str2.isEmpty() && str2.length() < 10){
            etemail.setError("Please enter a valid email address.");
            return;
        }
        if(rating == 0){
            Toast.makeText(FeedbackActivity.this, "Please rate our app first.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent emailint = new Intent(Intent.ACTION_SEND);
        emailint.setType("text/html");
        emailint.putExtra(Intent.EXTRA_EMAIL,new String[]{"ankitsrv995@gmail.com","ishusingh9721073220@gmail.com"});
        emailint.putExtra(Intent.EXTRA_SUBJECT,"Feedback");
        emailint.putExtra(Intent.EXTRA_TEXT,"Hi,\n \t You have got a feedback e-mail.Your app has been rated "+rating+" star by the user. And user's thought about the app are - \""+edfeedbck.getText().toString()+"\". To write him back please use the email \""+etemail.getText().toString()+"\". \n\tHave a nice day. \n\tThank You.");
        startActivity(Intent.createChooser(emailint,"Send Feedback"));
    }



}
