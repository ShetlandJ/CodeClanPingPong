package myfirstgame.pingpongscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    Button playGame;
    Button leagueTable;
    Button addGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

    }

    public void onPlayGameButtonClicked(View button) {
        playGame = findViewById(R.id.addGame);

        Intent i = new Intent(this, PlayerListActivity.class);
        startActivity(i);
    }

    public void onLeagueTableButtonClicked(View button) {
        leagueTable = findViewById(R.id.leagueTableBtn);

        Intent i = new Intent(this, StatisticsActivity.class);
        startActivity(i);
    }


    public void addGame(View button) {
        addGame = findViewById(R.id.addGameBtn);

        Intent i = new Intent(this, AddGameActivity.class);
        startActivity(i);
    }

}
