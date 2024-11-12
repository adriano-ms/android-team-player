package com.edu.fateczl.teamplayer.persistence;

import android.database.SQLException;

import java.util.List;
import java.util.Optional;

/**
 * @author Adriano M Sanchez
 */
public interface ICRUDDao<T> {

    void insert(T t) throws SQLException;
    int update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    Optional<T> findOne(T t) throws SQLException;
    List<T> findAll() throws SQLException;
}
