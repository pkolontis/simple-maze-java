/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.utils.test;

import com.company.maze.domain.entities.Node;
import com.company.maze.domain.enums.NodeType;
import com.company.maze.domain.utils.NodeUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Represents a class used for testing of NodeUtils method getNodeByType.
 * 
 * @author Petros Kolontis
 */
public class NodeUtilsTest {
    
    public NodeUtilsTest() {
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
    public void testGetNodeByType() {
        Node[][] actualNodes = new Node[2][2];
        actualNodes[0][0] = new Node(new Node.Coordinate(0, 0), NodeType.START);
        actualNodes[0][1] = new Node(new Node.Coordinate(0, 1), NodeType.OPEN);
        actualNodes[1][0] = new Node(new Node.Coordinate(2, 2), NodeType.WALL);
        actualNodes[1][1] = new Node(new Node.Coordinate(3, 4), NodeType.OPEN);
     
        List<Node> actualOpen = NodeUtils.getNodeByType(actualNodes, NodeType.OPEN);
        
        List<Node> expectedOpen = new ArrayList<Node>();
        expectedOpen.add(new Node(new Node.Coordinate(0, 1), NodeType.OPEN));
        expectedOpen.add(new Node(new Node.Coordinate(3, 4), NodeType.OPEN));
        
        Assert.assertEquals(expectedOpen, actualOpen);
    }
    
    @Test
    public void testBuildNodes() {
        String[] lines = {"S_X",
                          "X_G"};
        Node[][] actualNodes = NodeUtils.buildNodes(lines);
        
        Node[][] expectedNodes = new Node[2][3];
        expectedNodes[0][0] = new Node(new Node.Coordinate(0, 0), NodeType.START);
        expectedNodes[0][1] = new Node(new Node.Coordinate(0, 1), NodeType.OPEN);
        expectedNodes[0][2] = new Node(new Node.Coordinate(0, 2), NodeType.WALL);
        expectedNodes[1][0] = new Node(new Node.Coordinate(1, 0), NodeType.WALL);
        expectedNodes[1][1] = new Node(new Node.Coordinate(1, 1), NodeType.OPEN);
        expectedNodes[1][2] = new Node(new Node.Coordinate(1, 2), NodeType.GOAL);
        
        Assert.assertArrayEquals(expectedNodes, actualNodes);
    }
}
