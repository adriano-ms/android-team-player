package com.edu.fateczl.teamplayer.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.edu.fateczl.teamplayer.model.Player;
import com.edu.fateczl.teamplayer.model.Team;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Adriano M Sanchez
 */
public class PlayerDao implements IPlayerDao, ICRUDDao<Player> {

    private final Context context;
    private GenericDao genericDao;
    private SQLiteDatabase database;

    public PlayerDao(Context context){
        this.context = context;
    }

    @Override
    public PlayerDao open() {
        genericDao = new GenericDao(context);
        database = genericDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        genericDao.close();
    }

    @Override
    public void insert(Player player) throws SQLException {
        ContentValues contentValues = buildContentValues(player);
        database.insert("player", null, contentValues);
    }

    @Override
    public int update(Player player) throws SQLException {
        ContentValues contentValues = buildContentValues(player);
        return database.update("player", contentValues, "id = " + player.getId(), null);
    }

    @Override
    public void delete(Player player) throws SQLException {
        database.delete("player", "id = " + player.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Optional<Player> findOne(Player player) throws SQLException {
        String sql = "SELECT p.id, p.name AS player_name, p.birthdate, p.height, p.weight, t.code, t.name, t.city FROM player p " +
                "INNER JOIN team t " +
                "ON p.code_team = t.code " +
                "WHERE p.id = " + player.getId() + ";";
        Player p = null;
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                p = new Player();
                p.setId(cursor.getInt(cursor.getColumnIndex("id")));
                p.setName(cursor.getString(cursor.getColumnIndex("player_name")));
                p.setBirthdate(LocalDate.parse(cursor.getString(cursor.getColumnIndex("birthdate"))));
                p.setHeight(cursor.getFloat(cursor.getColumnIndex("height")));
                p.setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
                Team team = new Team();
                team.setCode(cursor.getInt(cursor.getColumnIndex("code")));
                team.setName(cursor.getString(cursor.getColumnIndex("name")));
                team.setCity(cursor.getString(cursor.getColumnIndex("city")));
                p.setTeam(team);
                cursor.moveToNext();
            }
        }
        Objects.requireNonNull(cursor).close();
        return Optional.ofNullable(p);
    }

    @SuppressLint("Range")
    @Override
    public List<Player> findAll() throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT p.id, p.name AS player_name, p.birthdate, p.height, p.weight, t.code, t.name, t.city FROM player p " +
                "INNER JOIN team t " +
                "ON p.code_team = t.code ;";
        Cursor cursor = database.rawQuery(sql, null);
        if(cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Player player = new Player();
                player.setId(cursor.getInt(cursor.getColumnIndex("id")));
                player.setName(cursor.getString(cursor.getColumnIndex("player_name")));
                player.setBirthdate(LocalDate.parse(cursor.getString(cursor.getColumnIndex("birthdate"))));
                player.setHeight(cursor.getFloat(cursor.getColumnIndex("height")));
                player.setWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
                Team team = new Team();
                team.setCode(cursor.getInt(cursor.getColumnIndex("code")));
                team.setName(cursor.getString(cursor.getColumnIndex("name")));
                team.setCity(cursor.getString(cursor.getColumnIndex("city")));
                player.setTeam(team);
                players.add(player);
                cursor.moveToNext();
            }
        }
        Objects.requireNonNull(cursor).close();
        return players;
    }

    private ContentValues buildContentValues(Player player) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", player.getId());
        contentValues.put("name", player.getName());
        contentValues.put("birthdate", player.getBirthdate().toString());
        contentValues.put("height", player.getHeight());
        contentValues.put("weight", player.getWeight());
        contentValues.put("code_team", player.getTeam().getCode());
        return contentValues;
    }
}
