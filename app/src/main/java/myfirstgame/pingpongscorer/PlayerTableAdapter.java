package myfirstgame.pingpongscorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by James on 19/11/2017.
 */

public class PlayerTableAdapter extends ArrayAdapter<Player> {

    public PlayerTableAdapter(Context context, ArrayList<Player> quests){
        super(context, 0, quests);
    }

    public View getView(int position, View listItemView, ViewGroup parent){
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.player_table_item, parent, false);
        }
        Player currentPlayer = getItem(position);

        TextView playerName = listItemView.findViewById(R.id.playerNameTable);
        playerName.setText(currentPlayer.getName().toString());

        TextView winCount = listItemView.findViewById(R.id.winAmount);
        winCount.setText(currentPlayer.getWinCount().toString());

        TextView lossCount = listItemView.findViewById(R.id.lossAmount);
        lossCount.setText(currentPlayer.getLossCount().toString());

        TextView pointsScored = listItemView.findViewById(R.id.pointsScored);
        pointsScored.setText(currentPlayer.getPointsScored().toString());

        TextView pointsConceded = listItemView.findViewById(R.id.pointsConceded);
        pointsConceded.setText(currentPlayer.getPointsConceded().toString());

        TextView winPercentage = listItemView.findViewById(R.id.winPercentage);
        winPercentage.setText(currentPlayer.winPercentage().toString() + "%");

        listItemView.setTag(currentPlayer);

        return listItemView;
    }
}
