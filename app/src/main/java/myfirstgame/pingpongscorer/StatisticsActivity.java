package myfirstgame.pingpongscorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<Player> playerList = Player.all(dbHelper);

        PlayerTableAdapter questAdapter = new PlayerTableAdapter(this, playerList);
        ListView listView = findViewById(R.id.premierLeague);
        listView.setAdapter(questAdapter);

    }
}
