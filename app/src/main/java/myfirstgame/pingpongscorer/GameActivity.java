package myfirstgame.pingpongscorer;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

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
    }

    public void onClick2(View arg0) {
        playerTwoScoreNumber++;
        playerTwoScore.setText(playerTwoScoreNumber.toString());
        checkWin();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    public void checkWin() {
        if (playerOneScoreNumber >= 11 && playerOneScoreNumber - playerTwoScoreNumber >= 2) {
            Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        } else if (playerTwoScoreNumber >= 11 && playerTwoScoreNumber - playerOneScoreNumber >= 2) {
            Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        }
    }
}

