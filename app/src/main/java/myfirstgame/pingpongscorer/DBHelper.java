package myfirstgame.pingpongscorer;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by James on 16/11/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test12.db";

    //    Players table
    public static final String PLAYER_TABLE_NAME = "players";
    public static final String PLAYER_COLUMN_ID = "id";
    public static final String PLAYER_COLUMN_NAME = "name";
    public static final String PLAYER_COLUMN_WINS = "wins";
    public static final String PLAYER_COLUMN_LOSSES = "losses";
    public static final String PLAYER_COLUMN_POINTS_SCORED = "points_scored";
    public static final String PLAYER_COLUMN_POINTS_CONCEDED = "points_conceded";

    //    Games table
    public static final String GAME_TABLE_NAME = "games";
    public static final String GAME_COLUMN_ID = "id";
    public static final String GAME_COLUMN_PLAYER_ONE = "player_one_id";
    public static final String GAME_COLUMN_PLAYER_TWO = "player_two_id";
    public static final String GAME_COLUMN_PLAYER_ONE_SCORE = "player_one_score";
    public static final String GAME_COLUMN_PLAYER_TWO_SCORE = "player_two_score";
    public static final String GAME_COLUMN_WINNER = "game_winner";
    public static final String GAME_COLUMN_LOSER = "game_loser";



    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 2);
    }

    public void onCreate(SQLiteDatabase db){
        setForeignKeyConstraintsEnabled(db);


        db.execSQL("CREATE TABLE " + PLAYER_TABLE_NAME + "(id INTEGER primary key autoincrement NOT NULL, name TEXT, wins INTEGER, losses INTEGER, points_scored INTEGER, points_conceded INTEGER )");
        db.execSQL("CREATE TABLE " + GAME_TABLE_NAME + "("
                + GAME_COLUMN_ID + " INTEGER primary key autoincrement NOT NULL, "
                + GAME_COLUMN_PLAYER_ONE + " INTEGER REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_COLUMN_ID + ") ON DELETE CASCADE, "
                + GAME_COLUMN_PLAYER_TWO + " INTEGER REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_COLUMN_ID + ") ON DELETE CASCADE, "
                + GAME_COLUMN_PLAYER_ONE_SCORE + " INTEGER, "
                + GAME_COLUMN_PLAYER_TWO_SCORE + " INTEGER, "
                + GAME_COLUMN_WINNER + " INTEGER REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_COLUMN_ID + ") ON DELETE CASCADE, "
                + GAME_COLUMN_LOSER + " INTEGER REFERENCES " + PLAYER_TABLE_NAME + "(" + PLAYER_COLUMN_ID + ") ON DELETE CASCADE);");

        db.execSQL("INSERT INTO " + PLAYER_TABLE_NAME + "('name', 'wins', 'losses', 'points_scored', 'points_conceded') VALUES ('James S', 0, 0, 0, 0 );");
        db.execSQL("INSERT INTO " + PLAYER_TABLE_NAME + "('name', 'wins', 'losses', 'points_scored', 'points_conceded') VALUES ('Peter M', 0, 0, 0, 0 );");
        db.execSQL("INSERT INTO " + PLAYER_TABLE_NAME + "('name', 'wins', 'losses', 'points_scored', 'points_conceded') VALUES ('Jamie K', 0, 0, 0, 0 );");
        db.execSQL("INSERT INTO " + PLAYER_TABLE_NAME + "('name', 'wins', 'losses', 'points_scored', 'points_conceded') VALUES ('Chad T', 0, 0, 0, 0 );");
        db.execSQL("INSERT INTO " + PLAYER_TABLE_NAME + "('name', 'wins', 'losses', 'points_scored', 'points_conceded') VALUES ('Alistair K', 0, 0, 0, 0 );");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + GAME_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE_NAME);
        onCreate(db);
    }

    private static void setForeignKeyConstraintsEnabled(@NonNull SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=1;");
        }
    }


    //    TABLE CHECKER
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

}

