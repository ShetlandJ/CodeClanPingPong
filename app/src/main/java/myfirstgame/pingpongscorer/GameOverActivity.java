package myfirstgame.pingpongscorer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView winNameBox;
    TextView winScoreBox;
    TextView lossNameBox;
    TextView lossScoreBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        winNameBox = findViewById(R.id.winner);
        String winner = getIntent().getStringExtra("winName");
        winNameBox.setText(winner);

        lossNameBox = findViewById(R.id.loser);
        String lossName = getIntent().getStringExtra("winScore");
        lossNameBox.setText(lossName);

        winScoreBox = findViewById(R.id.winnerScore);
        String winnerScore = getIntent().getStringExtra("lossName");
        winScoreBox.setText(winnerScore);

        lossScoreBox = findViewById(R.id.loserScore);
        String lossScore = getIntent().getStringExtra("lossScore");
        lossScoreBox.setText(lossScore);
    }
}
