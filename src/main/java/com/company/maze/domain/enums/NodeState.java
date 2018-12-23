/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.enums;

/**
 * Represents the state of a node in a maze
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public enum NodeState {
    
    NOT_VISITED(0), VISITED(1);
    
    private final int stateId;

    private NodeState(int stateId) {
        this.stateId = stateId;
    }
    
    /**
     * Gets the state of a node based on stateId.
     * 
     * @param stateId the stateId to set
     * @return the node state or default NOT_VISITED if stateId is not found
     */
    public NodeState getNodeState(int stateId) {
        switch (stateId) {
            case 0:
                return NodeState.NOT_VISITED;
            case 1:
                return NodeState.VISITED;
            default:
                return NodeState.NOT_VISITED;
        }
    }
}
