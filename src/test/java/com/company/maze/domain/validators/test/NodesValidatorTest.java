/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.validators.test;

import com.company.maze.domain.entities.Node;
import com.company.maze.domain.enums.NodeType;
import com.company.maze.domain.ex.GoalNotFoundException;
import com.company.maze.domain.ex.GoalNotSingleException;
import com.company.maze.domain.ex.NodeTypeIllegalException;
import com.company.maze.domain.ex.NodesSizeInvalidException;
import com.company.maze.domain.ex.StartNotFoundException;
import com.company.maze.domain.ex.StartNotSingleException;
import com.company.maze.domain.services.FileService;
import com.company.maze.domain.utils.NodeUtils;
import com.company.maze.domain.validators.NodesValidator;
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
 * Represents a class used for testing of NodesValidator.
 * Nodes array is built by reading sample files.
 * 
 * SAMPLE_FILES_PATH and its files should not be changed 
 * since it is required for this test
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class NodesValidatorTest {
    
    private static final String SAMPLE_FILES_PATH = "com/company/maze/test/samplefiles/";
    private static Node[][] nodes;
    
    public NodesValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
    public void testValidateNodesValid() {
        Map<NodeType, Node> actualValidNodes = testValidate(Paths.get(SAMPLE_FILES_PATH, "maze-valid.txt"));
        
        Map<NodeType, Node> expectedValidNodes = new TreeMap<NodeType, Node>();
        expectedValidNodes.put(NodeType.START, new Node(new Node.Coordinate(0, 0), NodeType.START));
        expectedValidNodes.put(NodeType.GOAL, new Node(new Node.Coordinate(3, 4), NodeType.GOAL));
        
        Assert.assertEquals(expectedValidNodes, actualValidNodes);
    }

    @Test(expected = NodesSizeInvalidException.class)
    public void testValidateNodesSizeInvalid() {
        testValidate(Paths.get(SAMPLE_FILES_PATH, "maze-size-invalid.txt"));
    }
    
    @Test(expected = StartNotFoundException.class)
    public void testValidateStartNotFound() {
        testValidate(Paths.get(SAMPLE_FILES_PATH, "maze-start-not-found.txt"));
    }
    
    @Test(expected = StartNotSingleException.class)
    public void testValidateStartNotSingle() {
        testValidate(Paths.get(SAMPLE_FILES_PATH, "maze-start-not-single.txt"));
    }
    
    @Test(expected = GoalNotFoundException.class)
    public void testValidateGoalNotFound() {
        testValidate(Paths.get(SAMPLE_FILES_PATH, "maze-goal-not-found.txt"));
    }
    
    @Test(expected = GoalNotSingleException.class)
    public void testValidateGoalNotSingle() {
        testValidate(Paths.get(SAMPLE_FILES_PATH, "maze-goal-not-single.txt"));
    }
    
    @Test(expected = NodeTypeIllegalException.class)
    public void testValidateNodeTypeIllegal() {
        testValidate(Paths.get(SAMPLE_FILES_PATH, "maze-nodetype-illegal.txt"));
    }
    
    private Map<NodeType, Node> testValidate(Path path) {
        nodes = NodeUtils.buildNodes(FileService.readFile(path.toString()));
        return NodesValidator.validate(nodes);
    }
}
