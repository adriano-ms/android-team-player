package com.edu.fateczl.teamplayer.controller;

import android.database.SQLException;

import java.util.List;

/**
 * @author Adriano M Sanchez
 */
public interface ICRUDDao<T> {

    void insert(T t) throws SQLException;
    int update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    T findOne(T t) throws SQLException;
    List<T> findAll() throws SQLException;
}
