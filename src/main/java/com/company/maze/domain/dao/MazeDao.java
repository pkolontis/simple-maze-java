/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.dao;

import com.company.maze.domain.entities.Maze;

/**
 * Represents the Data Access Object that will populate 
 * a maze by reading data from a data source 
 * depends on implementation used
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public interface MazeDao {
    
    public Maze populate();
}
