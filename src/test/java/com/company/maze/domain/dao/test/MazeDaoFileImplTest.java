/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.dao.test;

import com.company.maze.domain.dao.MazeDao;
import com.company.maze.domain.dao.MazeDaoFileImpl;
import com.company.maze.domain.entities.Maze;
import com.company.maze.domain.entities.Node;
import com.company.maze.domain.enums.NodeType;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Represents a class used for testing of maze population.
 * SAMPLE_FILES_PATH and its files should not be changed 
 * since it is required for this test
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class MazeDaoFileImplTest {
    
    // THIS PATH MUST NOT BE CHANGED
    private static final String SAMPLE_FILES_PATH = "com/company/maze/test/samplefiles/";
    private static MazeDao mazeDao;
    
    public MazeDaoFileImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Path path = Paths.get(SAMPLE_FILES_PATH, "maze-valid.txt");
        mazeDao = new MazeDaoFileImpl(path.toString());
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws URISyntaxException{
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPopulate() {
        Maze maze = mazeDao.populate();
        
        Node[][] actualNodes = maze.getNodes();
        Node[][] expectedNodes = new Node[5][5];
        Assert.assertEquals("Checking nodes length", expectedNodes.length, actualNodes.length);
        
        expectedNodes[0][0] = new Node(new Node.Coordinate(0, 0), NodeType.START);
        Assert.assertEquals("Checking node[0][0]", expectedNodes[0][0], actualNodes[0][0]);
        
        expectedNodes[0][1] = new Node(new Node.Coordinate(0, 1), NodeType.OPEN);
        Assert.assertEquals("Checking node[0][1]", expectedNodes[0][1], actualNodes[0][1]);
        
        expectedNodes[2][2] = new Node(new Node.Coordinate(2, 2), NodeType.WALL);
        Assert.assertEquals("Checking node[2][2]", expectedNodes[2][2], actualNodes[2][2]);
        
        expectedNodes[3][4] = new Node(new Node.Coordinate(3, 4), NodeType.GOAL);
        Assert.assertEquals("Checking node[3][4]", expectedNodes[3][4], actualNodes[3][4]);
    }
}
