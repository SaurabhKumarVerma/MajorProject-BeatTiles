package trainedge.beattiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by CISE on 03/04/2017.
 */

public class ScoreHolder extends RecyclerView.ViewHolder {

    TextView tvCommentUser;
    TextView tvCommentMsg;

    public ScoreHolder(View itemView) {
        super(itemView);
        tvCommentMsg= (TextView) itemView.findViewById(R.id.tvCommentMsg);
        tvCommentUser= (TextView) itemView.findViewById(R.id.tvCommentUser);
    }
}
