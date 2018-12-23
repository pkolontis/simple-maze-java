/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.ex;

/**
 * Thrown when the application attempts to perform an operation
 * that is not allowed in a not populated maze.
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public class MazeNotPopulatedException extends RuntimeException {
    
    /**
     * Constructs a MazeNotPopulatedException with the 
     * specified message
     * 
     * @param message the detailed message
     */
    public MazeNotPopulatedException(String message) {
        super(message);
    }
}
