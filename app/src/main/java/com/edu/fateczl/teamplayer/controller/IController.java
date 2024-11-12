package com.edu.fateczl.teamplayer.controller;

import android.database.SQLException;

import java.util.List;
import java.util.Optional;

/**
 * @author Adriano M Sanchez
 */
public interface IController<T> {

    void insert(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(T t) throws Exception;
    T findOne(T t) throws Exception;
    List<T> listAll() throws Exception;
}
