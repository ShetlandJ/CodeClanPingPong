package myfirstgame.pingpongscorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddPlayerActivity extends AppCompatActivity {

    EditText newPlayerInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        newPlayerInput = findViewById(R.id.userInput);
    }

    public void addPlayer(View button){
        DBHelper dbHelper = new DBHelper(this);
        String name = newPlayerInput.getText().toString();

        Player player = new Player(name, 0, 0, 0, 0);
        player.save(dbHelper);

        Intent intent = new Intent(this, PlayerListActivity.class);
        startActivity(intent);
    }

}
