package myfirstgame.pingpongscorer;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlayerListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Spinner playerOneSpinner;
    Spinner playerTwoSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        DBHelper dbHelper = new DBHelper(this);
        ArrayList<Player> playerList = Player.all(dbHelper);
        ArrayList<String> playerNames = new ArrayList<>();
        for (Player player : playerList){
            playerNames.add(player.getName());
        }

        playerOneSpinner = findViewById(R.id.playerOneSpinner);

        ArrayAdapter<String> playerOneSpinnerAdapter = new ArrayAdapter<>(PlayerListActivity.this, R.layout.player_spinner, playerNames);
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

        playerTwoSpinner = findViewById(R.id.playerTwoSpinner);

        ArrayAdapter<String> playerTwoSpinnerAdapter = new ArrayAdapter<>(PlayerListActivity.this, R.layout.player_spinner, player2Names);
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



    public void onAddButtonClicked(View button) {
        FloatingActionButton fab = findViewById(R.id.addPlayer);

        Intent i = new Intent(this, AddPlayerActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void playerGame(View button) {

        String playerOne = playerOneSpinner.getSelectedItem().toString();
        String playerTwo = playerTwoSpinner.getSelectedItem().toString();

        if (!playerOne.equals(playerTwo)) {

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("playerOneName", playerOne.toString());
            intent.putExtra("playerTwoName", playerTwo.toString());

            startActivity(intent);
        } else {
            Toast.makeText(this, "You can't play against yourself, man!", Toast.LENGTH_SHORT).show();
        }
    }

}


