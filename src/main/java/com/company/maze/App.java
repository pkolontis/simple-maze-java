/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze;

import com.company.maze.domain.dao.MazeDao;
import com.company.maze.domain.dao.MazeDaoFileImpl;
import com.company.maze.domain.entities.Maze;
import com.company.maze.domain.entities.Node;
import com.company.maze.domain.entities.Route;
import com.company.maze.domain.services.SearchService;
import com.company.maze.domain.services.SearchServiceBfsImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents application's main gateway.
 * 
 * This application reads a sample file, populates a maze by validating data
 * and searches for a shortest route as well as for any other route
 * based on maze's start and goal nodes.
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 *
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);
 
    private static final String SAMPLE_FILE = "com/company/maze/samplefiles/maze-basic.txt";
    
    /** 
     * Populates a maze and searches for routes
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        // populate maze
        Maze maze = populateMaze();
        printMaze(maze);
        System.out.println();
        
        // search for routes
        SearchService searchService = new SearchServiceBfsImpl();
        searchAndPrintPath(searchService, maze);
        
        System.out.println();
        
        searchAndPrintPaths(searchService, maze);
    }
    
    private static Maze populateMaze() {
        log.info("Populating maze by reading file {}", SAMPLE_FILE);
        MazeDao mazeDao = new MazeDaoFileImpl(SAMPLE_FILE);

        return mazeDao.populate();
    }
    
    private static void printMaze(Maze maze) {
        log.info("Printing populated maze");
        Node[][] nodes = maze.getNodes();
        
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                if (nodes[i][j] != null) {
                    // using System.out.print to show in a 2d matrix
                    System.out.print(nodes[i][j] + "\t");
                }
            }
            System.out.println();;
        }
    }
    
    private static void searchAndPrintPath(SearchService service, Maze maze) {
        log.info("Searching the shortest path from {} to {}", maze.getStart(), maze.getGoal());
        Route route = service.searchPath(maze);
        
        log.info("Printing the shortest path from {} to {}", maze.getStart(), maze.getGoal());
        log.info(route == null ? "No path found" : route.getNodes().toString());
    }
    
    private static void searchAndPrintPaths(SearchService service, Maze maze) {
        log.info("Searching all possible paths from {} to {}", maze.getStart(), maze.getGoal());
        List<Route> routes = service.searchPaths(maze);
        
        log.info("Printing all possible paths from {} to {}", maze.getStart(), maze.getGoal());
        if (routes.isEmpty()) {
            log.info("No path found");
        }
        routes.forEach(route->{
            log.info(route.getNodes().toString());
        });
    }
}
