package trainedge.beattiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import trainedge.beattiles.R;

/**
 * Created by Lenovo on 4/15/2017.
 */

public class songholder extends RecyclerView.ViewHolder {
    TextView tvsongs;
    TextView tvartist;
    TextView tvduration;
    View rlayout;
    public songholder(View itemView) {
        super(itemView);
        tvsongs=(TextView) itemView.findViewById(R.id.tvsongs);
        tvartist=(TextView) itemView.findViewById(R.id.tvartist);
        tvduration=(TextView) itemView.findViewById(R.id.tvduration);
         rlayout=itemView.findViewById(R.id.rlcontainer);
    }
}
