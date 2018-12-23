/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.ex;

/**
 * Thrown when the application attempts to populate
 * a maze having a node with illegal node type
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class NodeTypeIllegalException extends RuntimeException {
    
    /**
     * Constructs a NodeTypeIllegalException with
     * the specified message
     * 
     * @param message the message to set
     */
    public NodeTypeIllegalException(String message) {
        super(message);
    }
}
