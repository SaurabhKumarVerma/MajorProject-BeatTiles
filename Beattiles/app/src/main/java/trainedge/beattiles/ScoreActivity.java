package trainedge.beattiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    private DatabaseReference dbRef;
    private RecyclerView rvComments;
    List<ScoreModel> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        commentList = new ArrayList<>();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference("scores").child(uid);
        final RecyclerView rvComments = (RecyclerView) findViewById(R.id.rvComments);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        //passing layout manager in recyclerview
        rvComments.setLayoutManager(manager);

        final ScoreAdapter adapter = new ScoreAdapter(commentList);
        rvComments.setAdapter(adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //data is in dataSnapshot obj
                int position = 0;
                commentList.clear();
                if (dataSnapshot.hasChildren()) { //tab
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // datasnapshot.getChildren().iter
                        commentList.add(new ScoreModel(snapshot));
                        adapter.notifyItemInserted(position);
                        position++;
                    }
                    Toast.makeText(ScoreActivity.this, "data loaded", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ScoreActivity.this, "No Scores", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
