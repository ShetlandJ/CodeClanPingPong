package myfirstgame.pingpongscorer;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import static myfirstgame.pingpongscorer.DBHelper.GAME_COLUMN_PLAYER_ONE;
import static myfirstgame.pingpongscorer.DBHelper.GAME_COLUMN_PLAYER_ONE_SCORE;
import static myfirstgame.pingpongscorer.DBHelper.GAME_COLUMN_PLAYER_TWO;
import static myfirstgame.pingpongscorer.DBHelper.GAME_COLUMN_PLAYER_TWO_SCORE;
import static myfirstgame.pingpongscorer.DBHelper.GAME_TABLE_NAME;


/**
 * Created by James on 18/11/2017.
 */

public class Game {

    int id;
    Integer playerOneId;
    Integer playerTwoId;
    Integer playerOneScore;
    Integer playerTwoScore;

    public Game(Integer playerOneId, Integer playerTwoId, Integer playerOneScore, Integer playerTwoScore) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public Game(int id, Integer playerOneId, Integer playerTwoId, Integer playerOneScore, Integer playerTwoScore) {
        this.id = id;
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public int getId() {
        return id;
    }

    public Integer getPlayerOneId() {
        return playerOneId;
    }

    public Integer getPlayerTwoId() {
        return playerTwoId;
    }

    public Integer getPlayerOneScore() {
        return playerOneScore;
    }

    public Integer getPlayerTwoScore() {
        return playerTwoScore;
    }

    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAME_COLUMN_PLAYER_ONE, playerOneId);
        contentValues.put(GAME_COLUMN_PLAYER_TWO, playerTwoId);
        contentValues.put(GAME_COLUMN_PLAYER_ONE_SCORE, playerOneScore);
        contentValues.put(GAME_COLUMN_PLAYER_TWO_SCORE, playerTwoScore);
        db.insert(GAME_TABLE_NAME, null, contentValues);
        return true;
    }
}
