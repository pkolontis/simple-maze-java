/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.dao;

import com.company.maze.domain.entities.Maze;
import com.company.maze.domain.services.FileService;
import com.company.maze.domain.utils.NodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the Data Access Object that will populate 
 * a maze by reading a file
 * 
 * @author Petros Kolontis
 */
public class MazeDaoFileImpl implements MazeDao {
    
    private static final Logger log = LoggerFactory.getLogger(MazeDaoFileImpl.class);
    private String file;

    /**
     * Constructs a DAO file implementation based on the given file
     * @param file the file to set
     */
    public MazeDaoFileImpl(String file) {
        this.file = file;
    }
    
    /**
     * Creates and populates a maze by reading implementation's passed file,
     * building an array of nodes and calling setter of the maze
     * 
     * @return a populated maze
     */
    @Override
    public Maze populate() {
        String[] lines = FileService.readFile(file);

        log.debug("Populating Maze by using file that was read before");
        Maze maze = new Maze();
        maze.setNodes(NodeUtils.buildNodes(lines));
        log.debug("Population successfully completed");

        return maze;
    }

    /**
     * Sets a file to DAO
     * 
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }
}
