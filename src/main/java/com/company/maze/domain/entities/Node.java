/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.entities;

import com.company.maze.domain.enums.NodeState;
import com.company.maze.domain.enums.NodeType;
import java.util.Objects;

/**
 * Represents a node which can be identified
 * by a coordinate, a node type and a node state.
 * Given that every node is going to be inspected, 
 * the source represents the node that will start the inspection.
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class Node {
    
    private final Coordinate coord; 
    private Node source;
    private final NodeType type;
    private NodeState state = NodeState.NOT_VISITED;

    /**
     * Constructs a node with given coordinate and type.
     * Source will be null since is not inspected
     * 
     * @param coord
     * @param type 
     */
    public Node(Coordinate coord, NodeType type) {
        this.coord = coord;
        this.type = type;
    }

    /**
     * Gets the type
     * 
     * @return the type
     */
    public NodeType getType() {
        return type;
    }

    /**
     * Gets the source node
     * 
     * @return the source node
     */
    public Node getSource() {
        return source;
    }

    /**
     * Sets a source node
     * 
     * @param source the source node to set
     */
    public void setSource(Node source) {
        this.source = source;
    }

    /**
     * Gets the state of node
     * 
     * @return the state 
     */
    public NodeState getState() {
        return state;
    }

    /**
     * Sets a state based on the given state
     * 
     * @param state the state to set
     */
    public void setState(NodeState state) {
        this.state = state;
    }

    /**
     * Gets the coordinate of node
     * 
     * @return the coordinate
     */
    public Coordinate getCoord() {
        return coord;
    }

    /**
     * Converts node in a human readable format
     * showing coordinate and type
     * 
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();
        strB.append("(")
            .append(this.coord.x + 1)
            .append(":")
            .append(this.coord.y + 1)
            .append(")[")
            .append(this.type.getValue()).append("]");
                
        return strB.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.coord);
        hash = 67 * hash + Objects.hashCode(this.type);
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
        final Node other = (Node) obj;
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
    
    /**
     * Represents the coordinate of a node
     */
    public static class Coordinate {
        private final int x;
        private final int y;

        /**
         * Constructs a Coordinate based on x and y
         * 
         * @param x the x to set
         * @param y the y to set
         */
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Gets x of coordinate
         * 
         * @return x
         */
        public int getX() {
            return x;
        }

        /**
         * Gets y of coordinate
         * @return x
         */
        public int getY() {
            return y;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 23 * hash + this.x;
            hash = 23 * hash + this.y;
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
            final Coordinate other = (Coordinate) obj;
            if (this.x != other.x) {
                return false;
            }
            if (this.y != other.y) {
                return false;
            }
            return true;
        }
    }
}
