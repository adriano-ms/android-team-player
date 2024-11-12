package com.edu.fateczl.teamplayer.controller;

import com.edu.fateczl.teamplayer.controller.exceptions.NotFoundException;
import com.edu.fateczl.teamplayer.model.Player;
import com.edu.fateczl.teamplayer.persistence.ICRUDDao;
import com.edu.fateczl.teamplayer.persistence.IPlayerDao;
import com.edu.fateczl.teamplayer.persistence.PlayerDao;

import java.util.List;
import java.util.Objects;

/**
 * @author Adriano M Sanchez
 */
public class PlayerController implements IController<Player> {

    private final ICRUDDao<Player> crudDao;
    private final IPlayerDao playerDao;

    public PlayerController(ICRUDDao<Player> crudDao, IPlayerDao playerDao){
        this.crudDao = crudDao;
        this.playerDao = playerDao;
    }

    @Override
    public void insert(Player player) throws Exception {
        if(playerDao.open() == null)
            playerDao.open();
        crudDao.insert(player);
        playerDao.close();
    }

    @Override
    public void update(Player player) throws Exception {
        if(playerDao.open() == null)
            playerDao.open();
        crudDao.update(player);
        playerDao.close();
    }

    @Override
    public void delete(Player player) throws Exception {
        if(playerDao.open() == null)
            playerDao.open();
        crudDao.delete(player);
        playerDao.close();
    }

    @Override
    public Player findOne(Player player) throws Exception {
        if(playerDao.open() == null)
            playerDao.open();
        player = crudDao.findOne(player).orElseThrow(NotFoundException::new);
        playerDao.close();
        return player;
    }

    @Override
    public List<Player> listAll() throws Exception {
        if(playerDao.open() == null)
            playerDao.open();
        List<Player> list = crudDao.findAll();
        playerDao.close();
        return list;
    }
}
