package com.example.belajardicoding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<PlayerItem> {
    private List<PlayerItem> playerItemList;

    private Context context;

    public ListViewAdapter(List<PlayerItem> playerItemList, Context context) {
        super(context, R.layout.list_item, playerItemList);
        this.playerItemList = playerItemList;
        this.context = context;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View listViewItem = inflater.inflate(R.layout.list_item, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewPosition = listViewItem.findViewById(R.id.textViewPosition);
        ImageView imgVIew = listViewItem.findViewById(R.id.Poster);


        PlayerItem playerItem = playerItemList.get(position);

        textViewName.setText(playerItem.getName());
        textViewPosition.setText(playerItem.getPosition());

        Glide.with(context).load(playerItem.getPoster()).apply(new RequestOptions().override(100, 100)).into(imgVIew);

        return listViewItem;
    }
}
