/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.ex;

/**
 * Thrown when the application attempts to populate
 * a maze without having start node
 * 
 * @author Petros Kolontis
 */
public class StartNotFoundException extends RuntimeException {
    
    /**
     * Constructs a StartNotFoundException with
     * the specified message
     * 
     * @param message the message to set 
     */
    public StartNotFoundException(String message) {
        super(message);
    }
}
