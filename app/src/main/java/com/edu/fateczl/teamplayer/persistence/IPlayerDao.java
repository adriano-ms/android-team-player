package com.edu.fateczl.teamplayer.persistence;

/**
 * @author Adriano M Sanchez
 */
public interface IPlayerDao {

    PlayerDao open();
    void close();

}
