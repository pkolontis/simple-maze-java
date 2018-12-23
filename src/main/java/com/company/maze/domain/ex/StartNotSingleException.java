/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.ex;

/**
 * Thrown when the application attempts to populate
 * a maze without having a single start node
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class StartNotSingleException extends RuntimeException {
    
    /**
     * Constructs a StartNotSingleException with
     * the specified message
     * 
     * @param message the message to set 
     */
    public StartNotSingleException(String message) {
        super(message);
    }
}
