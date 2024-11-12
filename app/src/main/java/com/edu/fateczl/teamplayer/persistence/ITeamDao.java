package com.edu.fateczl.teamplayer.persistence;

/**
 * @author Adriano M Sanchez
 */
public interface ITeamDao {

    TeamDao open();
    void close();
}
