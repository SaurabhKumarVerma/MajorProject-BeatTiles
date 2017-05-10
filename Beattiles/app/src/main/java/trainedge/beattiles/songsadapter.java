package trainedge.beattiles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Lenovo on 4/15/2017.
 */

public class songsadapter extends RecyclerView.Adapter<songholder> {

    private List<MusicRetriever.Item> songlist;

    songsadapter(List<MusicRetriever.Item> songlist) {
        this.songlist = songlist;
    }

    @Override
    public songholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = ((LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.simple_song_list, parent, false);
        return new songholder(row);
    }

    @Override
    public void onBindViewHolder(final songholder holder, int position) {
        final MusicRetriever.Item musicitem = songlist.get(holder.getAdapterPosition());
        holder.tvsongs.setText(musicitem.getTitle());
        holder.tvartist.setText(musicitem.getArtist());
        holder.tvduration.setText(String.valueOf(musicitem.getDuration()));
        // holder.rlayout.setOnClickListener();
        holder.rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = musicitem.getURI();
                Intent i = new Intent(holder.rlayout.getContext(), Summary.class);
                i.putExtra("trainedge.beattiles.path", musicitem.getPath());
                i.putExtra("trainedge.beattiles.uri", uri);
                holder.rlayout.getContext().startActivity(i);
            }
        });
        holder.rlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i = new Intent(holder.rlayout.getContext(), SongPlayerActivity.class);
                i.putExtra("trainedge.beattiles.position", holder.getAdapterPosition());
                i.putExtra("trainedge.beattiles.path", musicitem.getPath());
                i.putExtra("trainedge.beattiles.uri", musicitem.getURI());
                holder.rlayout.getContext().startActivity(i);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return songlist.size();
    }
}
