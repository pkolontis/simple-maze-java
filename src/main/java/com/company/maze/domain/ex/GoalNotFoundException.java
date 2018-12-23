/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.ex;

/**
 * Thrown when the application attempts to populate
 * a maze without having goal node
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class GoalNotFoundException extends RuntimeException {
    
    /**
     * Constructs a GoalNotFoundException with
     * the specified message
     * 
     * @param message the message to set
     */
    public GoalNotFoundException(String message) {
        super(message);
    }
}
