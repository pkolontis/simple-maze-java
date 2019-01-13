/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.services.test;

import com.company.maze.domain.dao.MazeDaoFileImpl;
import com.company.maze.domain.entities.Maze;
import com.company.maze.domain.entities.Node;
import com.company.maze.domain.entities.Route;
import com.company.maze.domain.enums.NodeType;
import com.company.maze.domain.services.SearchService;
import com.company.maze.domain.services.SearchServiceBfsImpl;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Represents a class used for testing of
 * SearchServiceBfsImpl's searchPath and searchPaths methods.
 * SAMPLE_FILES_PATH and its files should not be changed 
 * since it is required for this test
 * 
 * @author Petros Kolontis
 */
public class SearchServiceBfsImplTest {
    
    // THIS PATH MUST NOT BE CHANGED
    private static final String SAMPLE_FILES_PATH = "com/company/maze/test/samplefiles/";
    private static Maze maze;
    private static SearchService searchService;
    
    public SearchServiceBfsImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Path path = Paths.get(SAMPLE_FILES_PATH, "maze-valid.txt");
        // populates a maze
        maze = new MazeDaoFileImpl(path.toString()).populate();
        
        searchService = new SearchServiceBfsImpl();
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
    public void testSearchPath() {
        Route actualRoute = searchService.searchPath(maze);
        Assert.assertEquals(getExpectedRoute().getNodes(), actualRoute.getNodes());
    }
    
    @Test
    public void testSearchPaths() {
        List<Route> actualRoutes = searchService.searchPaths(maze);
        Assert.assertTrue(actualRoutes.contains(getExpectedRoute()));
        Assert.assertTrue(actualRoutes.contains(getAnotherExpectedRoute()));
        Assert.assertTrue(!actualRoutes.contains(getNotExpectedRoute()));
    }
    
    private Route getExpectedRoute() {
        Route expectedRoute = new Route();
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(0, 0), NodeType.START));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(1, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(2, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 1), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 2), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 2), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 3), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 4), NodeType.GOAL));
        
        return expectedRoute;
    }
    
    private Route getAnotherExpectedRoute() {
        Route expectedRoute = new Route();
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(0, 0), NodeType.START));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(1, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(2, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 1), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 2), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 2), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 3), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(2, 3), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(2, 4), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 4), NodeType.GOAL));
        
        return expectedRoute;
    }
    
    private Route getNotExpectedRoute() {
        Route expectedRoute = new Route();
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(0, 0), NodeType.START));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(1, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(2, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 0), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 1), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 2), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 3), NodeType.WALL));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(4, 4), NodeType.OPEN));
        expectedRoute.getNodes().add(new Node(new Node.Coordinate(3, 4), NodeType.GOAL));
        
        return expectedRoute;
    }
}
