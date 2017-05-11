package trainedge.beattiles;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by CISE on 03/04/2017.
 */

class ScoreModel {
    String username;
    String msg;

    public ScoreModel(DataSnapshot dataSnapshot) {
        this.username = dataSnapshot.child("score").getValue(String.class);
        this.msg = dataSnapshot.child("user").getValue(String.class);
    }
}
