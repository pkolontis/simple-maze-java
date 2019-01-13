/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.ex;

/**
 * Thrown when the application attempts to populate 
 * a maze having an invalid size
 * 
 * @author Petros Kolontis
 */
public class NodesSizeInvalidException extends RuntimeException {
    
    /**
     * Constructs a MazeSizeInvalidException with
     * the specified message
     * 
     * @param message the message to set 
     */
    public NodesSizeInvalidException(String message) {
        super(message);
    }
}
