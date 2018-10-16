package com.example.user.ludo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter{
    public ArrayList<Integer> player_id, player_score;
    public ArrayList<String> player_name;
    Context context;
    SQLiteDatabase sqlitedb;

    public static LayoutInflater inflater = null;

    public customAdapter(HighscorePage javalist, ArrayList<Integer> id, ArrayList<Integer> score, ArrayList<String> name){
        context = javalist;
        player_id = id;
        player_name = name;
        player_score = score;

        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return player_id.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class isiItem{
        TextView tvName, tvScore;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final isiItem isiItemnya = new isiItem();
        View barisList;

        barisList = inflater.inflate(R.layout.highscore_listview, null);
        isiItemnya.tvName = (TextView) barisList.findViewById(R.id.name);
        isiItemnya.tvScore = (TextView) barisList.findViewById(R.id.score);

        isiItemnya.tvName.setText(player_name.get(position));
        isiItemnya.tvScore.setText(player_score.get(position).toString());

        return barisList;
    }
}