package myfirstgame.pingpongscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView playerOneName;
    TextView playerTwoName;
    TextView playerOneScore;
    TextView playerTwoScore;
    Integer playerOneScoreNumber;
    Integer playerTwoScoreNumber;

    Game game;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        dbHelper = new DBHelper(this);

        playerOneName = findViewById(R.id.playerOneGameName);
        String playerOne = getIntent().getStringExtra("playerOneName");
        playerOneName.setText(playerOne);

        playerTwoName = findViewById(R.id.playerTwoGameName);
        String playerTwo = getIntent().getStringExtra("playerTwoName");
        playerTwoName.setText(playerTwo);

        playerOneScoreNumber = 0;
        playerTwoScoreNumber = 0;

        playerOneScore = findViewById(R.id.playerOneScore);
        playerOneScore.setText("0");
        playerOneScore.setOnClickListener(this);

        playerTwoScore = findViewById(R.id.playerTwoScore);
        playerTwoScore.setText("0");
        playerOneScore.setOnClickListener(this);

    }

    public void onClick(View arg0) {
        playerOneScoreNumber++;
        playerOneScore.setText(playerOneScoreNumber.toString());
        checkWin();

        playerOneWins();
    }

    public void onClick2(View arg0) {
        playerTwoScoreNumber++;
        playerTwoScore.setText(playerTwoScoreNumber.toString());
        checkWin();
        playerTwoWins();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public boolean checkWin() {
        if (playerOneScoreNumber >= 11 && playerOneScoreNumber - playerTwoScoreNumber >= 2) {
            return true;
        } else if (playerTwoScoreNumber >= 11 && playerTwoScoreNumber - playerOneScoreNumber >= 2) {
            return true;
        }
        return false;
    }

    public void playerOneWins() {
        if (checkWin()) {
            String winnerName = getIntent().getStringExtra("playerOneName");
            String loserName = getIntent().getStringExtra("playerTwoName");

            Player winner = Player.load(dbHelper, winnerName);
            Player loser = Player.load(dbHelper, loserName);

            winner.setWinCount(1);
            loser.setLossCount(1);

            winner.setPointsScored(playerOneScoreNumber);
            loser.setPointsScored(playerTwoScoreNumber);

            winner.setPointsConceded(playerTwoScoreNumber);
            loser.setPointsConceded(playerOneScoreNumber);

            winner.winPercentage();
            loser.winPercentage();

            winner.update(dbHelper);
            loser.update(dbHelper);


            Intent i = new Intent(this, GameOverActivity.class);
            i.putExtra("winName", getIntent().getStringExtra("playerOneName"));
            i.putExtra("lossName", getIntent().getStringExtra("playerTwoName"));
            i.putExtra("winScore", playerOneScoreNumber.toString());
            i.putExtra("lossScore", playerTwoScoreNumber.toString());
            saveGame();
            startActivity(i);
        }
    }

    public void playerTwoWins() {
        if (checkWin()) {
            String winnerName = getIntent().getStringExtra("playerTwoName");
            String loserName = getIntent().getStringExtra("playerOneName");

            Player winner = Player.load(dbHelper, winnerName);
            Player loser = Player.load(dbHelper, loserName);

            winner.setWinCount(1);
            loser.setLossCount(1);

            winner.setPointsScored(playerTwoScoreNumber);
            loser.setPointsScored(playerOneScoreNumber);

            winner.setPointsConceded(playerOneScoreNumber);
            loser.setPointsConceded(playerTwoScoreNumber);

            winner.winPercentage();
            loser.winPercentage();

            winner.update(dbHelper);
            loser.update(dbHelper);


            Intent i = new Intent(this, GameOverActivity.class);
            i.putExtra("winName", getIntent().getStringExtra("playerTwoName"));
            i.putExtra("lossName", getIntent().getStringExtra("playerOneName"));
            i.putExtra("winScore", playerTwoScoreNumber.toString());
            i.putExtra("lossScore", playerOneScoreNumber.toString());
            saveGame();
            startActivity(i);
        }
    }

    public void saveGame() {
        String p1name = getIntent().getStringExtra("playerOneName");
        String p2name = getIntent().getStringExtra("playerTwoName");
        Player playerOne = Player.load(dbHelper, p1name);
        Player playerTwo = Player.load(dbHelper, p2name);
        game = new Game(playerOne.getId(), playerTwo.getId(), playerOneScoreNumber, playerTwoScoreNumber);
        game.save(dbHelper);
    }
}

