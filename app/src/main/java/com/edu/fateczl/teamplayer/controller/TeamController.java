package com.edu.fateczl.teamplayer.controller;

import android.database.SQLException;

import com.edu.fateczl.teamplayer.controller.exceptions.NotFoundException;
import com.edu.fateczl.teamplayer.model.Team;
import com.edu.fateczl.teamplayer.persistence.ICRUDDao;
import com.edu.fateczl.teamplayer.persistence.ITeamDao;
import com.edu.fateczl.teamplayer.persistence.TeamDao;

import java.util.List;

/**
 * @author Adriano M Sanchez
 */
public class TeamController implements IController<Team> {

    private final ICRUDDao<Team> crudDao;
    private final ITeamDao teamDao;

    public TeamController(ICRUDDao<Team> crudDao, ITeamDao teamDao){
        this.crudDao = crudDao;
        this.teamDao = teamDao;
    };

    @Override
    public void insert(Team team) throws Exception {
        if(teamDao.open() == null)
            teamDao.open();
        crudDao.insert(team);
        teamDao.close();
    }

    @Override
    public void update(Team team) throws Exception {
        if(teamDao.open() == null)
            teamDao.open();
        crudDao.update(team);
        teamDao.close();
    }

    @Override
    public void delete(Team team) throws Exception {
        if(teamDao.open() == null)
            teamDao.open();
        crudDao.delete(team);
        teamDao.close();
    }

    @Override
    public Team findOne(Team team) throws Exception {
        if(teamDao.open() == null)
            teamDao.open();
        team = crudDao.findOne(team).orElseThrow(NotFoundException::new);
        teamDao.close();
        return team;
    }

    @Override
    public List<Team> listAll() throws Exception {
        if(teamDao.open() == null)
            teamDao.open();
        List<Team> list = crudDao.findAll();
        teamDao.close();
        return list;
    }
}
