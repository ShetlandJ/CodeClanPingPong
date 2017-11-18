package myfirstgame.pingpongscorer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static myfirstgame.pingpongscorer.DBHelper.PLAYER_COLUMN_ID;
import static myfirstgame.pingpongscorer.DBHelper.PLAYER_COLUMN_LOSSES;
import static myfirstgame.pingpongscorer.DBHelper.PLAYER_COLUMN_NAME;
import static myfirstgame.pingpongscorer.DBHelper.PLAYER_COLUMN_POINTS_CONCEDED;
import static myfirstgame.pingpongscorer.DBHelper.PLAYER_COLUMN_POINTS_SCORED;
import static myfirstgame.pingpongscorer.DBHelper.PLAYER_COLUMN_WINS;
import static myfirstgame.pingpongscorer.DBHelper.PLAYER_TABLE_NAME;

/**
 * Created by James on 16/11/2017.
 */

public class Player {

    private int id;
    private String name;
    private Integer winCount;
    private Integer lossCount;
    private Integer pointsScored;
    private Integer pointsConceded;

    public Player(String name, Integer winCount, Integer lossCount, Integer pointsScored, Integer pointsConceded) {
        this.name = name;
        this.winCount = winCount;
        this.lossCount = lossCount;
        this.pointsScored = pointsScored;
        this.pointsConceded = pointsConceded;
    }

    public Player(int id, String name, Integer winCount, Integer lossCount, Integer pointsScored, Integer pointsConceded) {
        this.id = id;
        this.name = name;
        this.winCount = winCount;
        this.lossCount = lossCount;
        this.pointsScored = pointsScored;
        this.pointsConceded = pointsConceded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public Integer getLossCount() {
        return lossCount;
    }

    public Integer getPointsScored() {
        return pointsScored;
    }

    public Integer getPointsConceded() {
        return pointsConceded;
    }

    public int calculateScoreDifference(){
        return pointsScored - pointsConceded;
    }

    public int progressByPercentage(){
        double calc = (winCount * 100) / (winCount + lossCount);
        double percentage = calc;
        int myInt = (int) (percentage * 1);
        return myInt;
    }

    public static Player load(DBHelper dbHelper, String name){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String where = " WHERE name=?";
        String[] whereArgs = new String[] {String.valueOf(name)};

        Cursor cursor = db.rawQuery("SELECT * FROM " + PLAYER_TABLE_NAME + where, whereArgs);
        Player player = null;
        while (cursor.moveToNext()) {

            Integer id = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_ID));
            String playerName = cursor.getString(cursor.getColumnIndex(PLAYER_COLUMN_NAME));
            Integer numberOfWins = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_WINS));
            Integer numberOfLosses = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_LOSSES));
            Integer pointsScored = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_POINTS_SCORED));
            Integer pointsConceded = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_POINTS_CONCEDED));

            cursor.close();
            player = new Player(id, playerName, numberOfWins, numberOfLosses, pointsScored, pointsConceded);
        }
        return player;
    }


    public boolean save(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_COLUMN_NAME, name);
        contentValues.put(PLAYER_COLUMN_WINS, winCount);
        contentValues.put(PLAYER_COLUMN_LOSSES, lossCount);
        contentValues.put(PLAYER_COLUMN_POINTS_SCORED, pointsScored);
        contentValues.put(PLAYER_COLUMN_POINTS_CONCEDED, pointsConceded);
        db.insert(PLAYER_TABLE_NAME, null, contentValues);
        return true;
    }

    public static ArrayList<Player> all(DBHelper dbHelper){
        ArrayList<Player> players = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PLAYER_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(PLAYER_COLUMN_NAME));
            Integer winCount = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_WINS));
            Integer lossCount = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_LOSSES));
            Integer pointsScored = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_POINTS_SCORED));
            Integer pointsConceded = cursor.getInt(cursor.getColumnIndex(PLAYER_COLUMN_POINTS_CONCEDED));

            Player player = new Player(id, name, winCount, lossCount, pointsScored, pointsConceded);
            players.add(player);
        }
        cursor.close();
        return players;
    }

    public static boolean deleteAll(DBHelper dbHelper){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + PLAYER_TABLE_NAME);
        return true;
    }

    public static boolean delete(DBHelper dbHelper, Integer id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = " id = ?";
        String[] values = {id.toString()};
        db.delete(PLAYER_TABLE_NAME, selection, values);
        return true;
    }

    public void update(DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_COLUMN_NAME, name);
        contentValues.put(PLAYER_COLUMN_WINS, winCount);
        contentValues.put(PLAYER_COLUMN_LOSSES, lossCount);
        contentValues.put(PLAYER_COLUMN_POINTS_SCORED, pointsScored);
        contentValues.put(PLAYER_COLUMN_POINTS_CONCEDED, pointsConceded);

        String where = "name=?";
        String[] whereArgs = new String[] {String.valueOf(name)};

        db.update(PLAYER_TABLE_NAME, contentValues, where, whereArgs);
        db.close();
    }
}