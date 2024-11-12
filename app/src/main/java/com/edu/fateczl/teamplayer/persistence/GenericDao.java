package com.edu.fateczl.teamplayer.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Adriano M Sanchez
 */
public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "COMPETITION.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_TEAM =
            "CREATE TABLE team ( " +
                "code INT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, " +
                "city VARCHAR(80) NOT NULL);";

    private static final String CREATE_TABLE_PLAYER =
            "CREATE TABLE player ( " +
                "id INT NOT NULL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "birthdate VARCHAR(10) NOT NULL, " +
                "height DECIMAL(4,2) NOT NULL, " +
                "weight DECIMAL(4,1) NOT NULL, " +
                "code_team INT NOT NULL, " +
                "FOREIGN KEY (code_team) REFERENCES team(code));";

    public GenericDao(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS player");
            db.execSQL("DROP TABLE IF EXISTS team");
            this.onCreate(db);
        }
    }
}
