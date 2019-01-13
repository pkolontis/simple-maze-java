/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.maze.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a route in a maze from Start to Goal
 * 
 * @author Petros Kolontis
 */
public class Route {
    
    private List<Node> nodes;

    /**
     * Constructs an empty route
     */
    public Route() {
        this.nodes = new ArrayList<>();
    }

    /**
     * Constructs a route with given nodes
     * 
     * @param nodes list of nodes to set
     */
    public Route(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Gets nodes of a route
     * @return a list of nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Sets nodes in a route
     * 
     * @param nodes nodes to set
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Gets the distance of a route 
     * based on number of nodes
     * 
     * @return the number of nodes
     */
    public int getDistance() {
        return this.nodes.size();
    }
    
    /**
     * Gets the last node of a route
     * 
     * @return the last node
     */
    public Node getLastNode() {
        return this.nodes.get(this.nodes.size() - 1);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nodes);
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
        final Route other = (Route) obj;
        if (!Objects.equals(this.nodes, other.nodes)) {
            return false;
        }
        return true;
    }
}
