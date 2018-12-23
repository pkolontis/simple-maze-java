/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.entities.test;

import com.company.maze.domain.dao.MazeDaoFileImpl;
import com.company.maze.domain.entities.Maze;
import com.company.maze.domain.entities.Node;
import com.company.maze.domain.enums.CardinalDirection;
import com.company.maze.domain.enums.NodeType;
import com.company.maze.domain.ex.MazeNotPopulatedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Represents a class used for testing of 
 * maze's getStart, getGoal and getAdjacentNodesOf methods.
 * SAMPLE_FILES_PATH and its files should not be changed 
 * since it is required for this test
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class MazeTest {
    
    // THIS PATH MUST NOT BE CHANGED
    private static final String SAMPLE_FILES_PATH = "com/company/maze/test/samplefiles/";
    private static Maze maze;
    
    public MazeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Path path = Paths.get(SAMPLE_FILES_PATH, "maze-valid.txt");
        // populates a maze
        maze = new MazeDaoFileImpl(path.toString()).populate();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetStart() {
        Assert.assertEquals(maze.getStart(), new Node(new Node.Coordinate(0, 0), NodeType.START));
    }
    
    @Test(expected = MazeNotPopulatedException.class)
    public void testGetStartMazeNotPopulated() {
        Maze notPopulatedMaze = new Maze();
        notPopulatedMaze.getStart();
    }
    
    @Test
    public void testGetGoal() {
        Assert.assertEquals(maze.getGoal(), new Node(new Node.Coordinate(3, 4), NodeType.GOAL));
    }
    
    @Test(expected = MazeNotPopulatedException.class)
    public void testGetGoalMazeNotPopulated() {
        Maze notPopulatedMaze = new Maze();
        notPopulatedMaze.getGoal();
    }
    
    @Test
    public void testGetAdjacentNodesOfStart() {
        Map<CardinalDirection, Node> actualAdjNodes = maze.getAdjacentNodesOf(maze.getStart());
        
        Map<CardinalDirection, Node> expectedAdjNodes = new TreeMap<CardinalDirection, Node>();
        expectedAdjNodes.put(CardinalDirection.SOUTH, new Node(new Node.Coordinate(1, 0), NodeType.OPEN));
        expectedAdjNodes.put(CardinalDirection.WEST, new Node(new Node.Coordinate(0, 1), NodeType.OPEN));
        
        Assert.assertEquals(expectedAdjNodes, actualAdjNodes);
    }
    
    @Test
    public void testGetAdjacentNodesOfGoal() {
        Map<CardinalDirection, Node> actualAdjNodes = maze.getAdjacentNodesOf(maze.getGoal());
        
        Map<CardinalDirection, Node> expectedAdjNodes = new TreeMap<CardinalDirection, Node>();
        expectedAdjNodes.put(CardinalDirection.NORTH, new Node(new Node.Coordinate(2, 4), NodeType.OPEN));
        expectedAdjNodes.put(CardinalDirection.SOUTH, new Node(new Node.Coordinate(4, 4), NodeType.OPEN));
        expectedAdjNodes.put(CardinalDirection.EAST, new Node(new Node.Coordinate(3, 3), NodeType.OPEN));
        
        Assert.assertEquals(expectedAdjNodes, actualAdjNodes);
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetAdjacentNodesOfNotFound() {
        Node notFound = new Node(new Node.Coordinate(5, 15), NodeType.OPEN);
        Map<CardinalDirection, Node> actualAdjNodes = maze.getAdjacentNodesOf(notFound);
        
    }
    
    @Test(expected = MazeNotPopulatedException.class)
    public void testGetAdjacentNodesOfNotPopulatedMaze() {
        Node node = new Node(new Node.Coordinate(0, 0), NodeType.START);
        Maze notPopulatedMaze = new Maze();
        notPopulatedMaze.getAdjacentNodesOf(node);
    }
}
