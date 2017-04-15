package trainedge.beattiles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Lenovo on 4/15/2017.
 */

public class songsadapter extends RecyclerView.Adapter<songholder> {

    List<MusicRetriever.Item> songlist;

    public songsadapter(List<MusicRetriever.Item> songlist) {
        this.songlist = songlist;
    }

    @Override
    public songholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row=((LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.simple_song_list,parent,false);
        return new songholder(row);
    }

    @Override
    public void onBindViewHolder(songholder holder, int position) {
        MusicRetriever.Item musicitem = songlist.get(position);
        holder.tvsongs.setText(musicitem.getTitle());
        holder.tvartist.setText(musicitem.getArtist());
        holder.tvduration.setText(String.valueOf(musicitem.getDuration()));


    }

    @Override
    public int getItemCount() {
        return songlist.size();
    }
}
