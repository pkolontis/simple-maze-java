/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.services;

import com.company.maze.domain.entities.Maze;
import com.company.maze.domain.entities.Route;
import java.util.List;

/**
 * Represents the service that searches paths in a maze
 * based on start and goal nodes and returns routes
 * 
 * @author Petros Kolontis
 */
public interface SearchService {

    /**
     * Searches a route in a maze based on start and goal nodes
     * 
     * @param maze the maze
     * 
     * @return the route
     */
    public Route searchPath(Maze maze);
    
    /**
     * Searches routes in a maze based on start and goal nodes
     * 
     * @param maze the maze
     * 
     * @return routes or an empty list if no route found
     */
    public List<Route> searchPaths(Maze maze);
}
