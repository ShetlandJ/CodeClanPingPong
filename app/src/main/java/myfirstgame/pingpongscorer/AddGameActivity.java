package myfirstgame.pingpongscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;

public class AddGameActivity extends AppCompatActivity {

    Spinner playerOneSpinner;
    Spinner playerTwoSpinner;

    EditText playerOneScore;
    EditText playerTwoScore;

    Button addGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<Player> playerList = Player.all(dbHelper);
        ArrayList<String> playerNames = new ArrayList<>();
        for (Player player : playerList){
            playerNames.add(player.getName());
        }

        playerOneScore = findViewById(R.id.playerOneScore);
        playerTwoScore = findViewById(R.id.playerTwoScore);

        addGameBtn = findViewById(R.id.addGame);

        playerOneSpinner = findViewById(R.id.player1Spinner);

        ArrayAdapter<String> playerOneSpinnerAdapter = new ArrayAdapter<>(AddGameActivity.this, R.layout.player_spinner, playerNames);
        playerOneSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerOneSpinner.setAdapter(playerOneSpinnerAdapter);
        playerOneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DBHelper dbHelper2 = new DBHelper(this);
        ArrayList<Player> player2List = Player.all(dbHelper2);
        ArrayList<String> player2Names = new ArrayList<>();
        for (Player player : player2List){
            player2Names.add(player.getName());
        }

        playerTwoSpinner = findViewById(R.id.player2Spinner);

        ArrayAdapter<String> playerTwoSpinnerAdapter = new ArrayAdapter<>(AddGameActivity.this, R.layout.player_spinner, player2Names);
        playerTwoSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerTwoSpinner.setAdapter(playerTwoSpinnerAdapter);
        playerTwoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        playerOneSpinner.setOnItemSelectedListener(new MySpinnerSelectedListener());
        playerTwoSpinner.setOnItemSelectedListener(new MySpinnerSelectedListener());
    }

    public void addGameToDb(View button){
        DBHelper dbHelper = new DBHelper(this);
        String playerOneName = playerOneSpinner.getSelectedItem().toString();
        String playerTwoName = playerTwoSpinner.getSelectedItem().toString();

        Player playerOne = Player.load(dbHelper, playerOneName);
        Player playerTwo = Player.load(dbHelper, playerTwoName);

        Integer playerOneId = playerOne.getId();
        Integer playerTwoId = playerTwo.getId();

        Integer p1Score = Integer.parseInt(playerOneScore.getText().toString());
        Integer p2Score = Integer.parseInt(playerTwoScore.getText().toString());

        Integer winner;
        Integer loser;

        if (p1Score > p2Score) {
            winner = playerOneId;
            loser = playerTwoId;
        } else {
            winner = playerTwoId;
            loser = playerOneId;
        }

        Game game = new Game(playerOneId, playerTwoId, p1Score, p2Score, winner, loser);
        game.save(dbHelper);

        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

}
