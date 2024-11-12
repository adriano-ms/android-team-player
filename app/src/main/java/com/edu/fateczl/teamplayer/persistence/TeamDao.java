package com.edu.fateczl.teamplayer.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.edu.fateczl.teamplayer.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Adriano M Sanchez
 */
public class TeamDao implements ITeamDao, ICRUDDao<Team> {

    private final Context context;
    private GenericDao genericDao;
    private SQLiteDatabase database;

    public TeamDao(Context context) {
        this.context = context;
    }

    @Override
    public TeamDao open() {
        genericDao = new GenericDao(context);
        database = genericDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDao.close();
    }

    @Override
    public void insert(Team team) throws SQLException {
        ContentValues contentValues = buildContentValues(team);
        database.insert("team", null, contentValues);
    }

    @Override
    public int update(Team team) throws SQLException {
        ContentValues contentValues = buildContentValues(team);
        return database.update("team", contentValues, "code = " + team.getCode(), null);
    }

    @Override
    public void delete(Team team) throws SQLException {
        database.delete("team", "code = " + team.getCode(), null);
    }

    @SuppressLint("Range")
    @Override
    public Optional<Team> findOne(Team team) throws SQLException {
        Team t = null;
        Cursor cursor = database.rawQuery("SELECT code, name, city FROM team WHERE code = " + team.getCode() + ";", null);
        if(cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                if(!cursor.getString(cursor.getColumnIndex("name")).isEmpty()) {
                    t = new Team();
                    t.setCode(cursor.getInt(cursor.getColumnIndex("code")));
                    t.setName(cursor.getString(cursor.getColumnIndex("name")));
                    t.setCity(cursor.getString(cursor.getColumnIndex("city")));
                }
                cursor.moveToNext();
            }
        }
        Objects.requireNonNull(cursor).close();
        return Optional.ofNullable(t);
    }

    @SuppressLint("Range")
    @Override
    public List<Team> findAll() throws SQLException {
        List<Team> teams = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT code, name, city FROM team;", null);
        if(cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                if(!cursor.getString(cursor.getColumnIndex("name")).isEmpty()) {
                    Team team = new Team();
                    team.setCode(cursor.getInt(cursor.getColumnIndex("code")));
                    team.setName(cursor.getString(cursor.getColumnIndex("name")));
                    team.setCity(cursor.getString(cursor.getColumnIndex("city")));
                    teams.add(team);
                }
                cursor.moveToNext();
            }
        }
        Objects.requireNonNull(cursor).close();
        return teams;
    }

    private ContentValues buildContentValues(Team team) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("code", team.getCode());
        contentValues.put("name", team.getName());
        contentValues.put("city", team.getCity());
        return contentValues;
    }
}
