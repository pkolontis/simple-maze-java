/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.entities;

import com.company.maze.domain.enums.CardinalDirection;
import com.company.maze.domain.enums.NodeType;
import com.company.maze.domain.ex.MazeNotPopulatedException;
import com.company.maze.domain.validators.NodesValidator;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a maze that consists of Nodes in a two-dimensional array
 * 
 * @author Petros Kolontis
 */
public class Maze {
    
    private static final Logger log = LoggerFactory.getLogger(Maze.class);
    
    private static final String GET_START_NOT_ALLOWED_MSG = "Maze not populated yet. Get start node operation not allowed.";
    private static final String GET_GOAL_NOT_ALLOWED_MSG = "Maze not populated yet. Get goal node operation not allowed.";
    private static final String ADJ_NODES_NOT_ALLOWED_MSG = "Maze not populated yet. Get adjacent nodes operation not allowed.";
    
    private Node[][] nodes;
    private Node start;
    private Node goal;
    private boolean populated;

    /**
     * Constructs an empty maze
     */
    public Maze() {
    }

    /**
     * Gets nodes of a maze
     * 
     * @return nodes
     */
    public Node[][] getNodes() {
        return nodes;
    }

    /**
     * Sets all nodes (including start and goal) in a maze 
     * after their successful validation and sets 
     * populated flag to true
     * 
     * @param nodes nodes to validate and set
     */
    public void setNodes(Node[][] nodes) {
        Map<NodeType, Node> validNodes = NodesValidator.validate(nodes);
        this.nodes = nodes;
        this.start = validNodes.get(NodeType.START);
        this.goal = validNodes.get(NodeType.GOAL);
        this.populated = true;
    }

    /**
     * Gets maze's populated flag
     * 
     * @return true if populated, otherwise false
     */
    public boolean isPopulated() {
        return populated;
    }
    
    /**
     * Gets the start node of maze.
     * If not populated, an exception will be thrown
     * showing that this operation is not allowed.
     * 
     * @return the start node
     * 
     * @throws MazeNotPopulatedException if maze is not populated
     */
    public Node getStart() {
        if (!this.populated) {
            log.error(GET_START_NOT_ALLOWED_MSG);
            throw new MazeNotPopulatedException(GET_START_NOT_ALLOWED_MSG);
        }
        return start;
    }
    
    /**
     * Gets the goal node of maze.
     * If not populated, an exception will be thrown
     * showing that this operation is not allowed.
     * 
     * @return the goal node
     * 
     * @throws MazeNotPopulatedException if maze is not populated
     */
    public Node getGoal() {
        if (!this.populated) {
            log.error(GET_GOAL_NOT_ALLOWED_MSG);
            throw new MazeNotPopulatedException(GET_GOAL_NOT_ALLOWED_MSG);
        }
        return goal;
    }
    
    /**
     * Gets a map of adjacent nodes of the given node in a maze. 
     * The map contains the cardinal direction as key and adjacent node as value
     * If maze is not populated, an exception will be thrown
     * showing that this operation is not allowed.
     * If given node's coordinate is out of maze's range, an exception will be thrown.
     * 
     * @param node the node
     * @return a map of adjacent nodes
     * 
     * @throws MazeNotPopulatedException if maze is not populated
     * @throws ArrayIndexOutOfBoundsException if given node's coordinate is out of maze's range
     */
    public Map<CardinalDirection, Node> getAdjacentNodesOf(Node node) {
        if (!this.populated) {
            log.error(ADJ_NODES_NOT_ALLOWED_MSG);
            throw new MazeNotPopulatedException(ADJ_NODES_NOT_ALLOWED_MSG);
        }
        Map<CardinalDirection, Node> adjacentNodes = new TreeMap<>();
        putNorthAdjacent(node, adjacentNodes);
        putSouthAdjacent(node, adjacentNodes);
        putEastAdjacent(node, adjacentNodes);
        putWestAdjacent(node, adjacentNodes);

        return adjacentNodes;
    }
    
    private void putNorthAdjacent(Node n, Map<CardinalDirection, Node> adjNodes) {
        if (n.getCoord().getX() - 1 < 0) {
            return;
        }
        putAdjacent(adjNodes, CardinalDirection.NORTH, this.nodes[n.getCoord().getX() - 1][n.getCoord().getY()]);
    }
    
    private void putSouthAdjacent(Node n, Map<CardinalDirection, Node> adjNodes) {
        if (n.getCoord().getX() + 1 == this.nodes.length) {
            return;
        }
        putAdjacent(adjNodes, CardinalDirection.SOUTH, this.nodes[n.getCoord().getX() + 1][n.getCoord().getY()]);
    }
    
    private void putEastAdjacent(Node n, Map<CardinalDirection, Node> adjNodes) {
        if (n.getCoord().getY() - 1 < 0) {
            return;
        }
        putAdjacent(adjNodes, CardinalDirection.EAST, this.nodes[n.getCoord().getX()][n.getCoord().getY() - 1]);
    }
    
    private void putWestAdjacent(Node n, Map<CardinalDirection, Node> adjNodes) {
        if (n.getCoord().getY() + 1 == this.nodes[n.getCoord().getX()].length) {
            return;
        }
        putAdjacent(adjNodes, CardinalDirection.WEST, this.nodes[n.getCoord().getX()][n.getCoord().getY() + 1]);
    }
    
    private void putAdjacent(Map<CardinalDirection, Node> adjNodes, CardinalDirection dir, Node node) {
        if (node == null) {
            return;
        }
        adjNodes.put(dir, node);
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Arrays.deepHashCode(this.nodes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Maze other = (Maze) obj;
        if (!Arrays.deepEquals(this.nodes, other.nodes)) {
            return false;
        }
        return true;
    }
}
