/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.ex;

/**
 * Thrown when the application attempts to read a file containing maze data
 * but the process is failed.
 * 
 * @author Petros Kolontis
 */
public class FileNotReadException extends RuntimeException {
    
    /**
     * Constructs a FileNotReadException with the 
     * specified message and cause
     * 
     * @param message the detailed message
     * @param cause the cause of exception
     */
    public FileNotReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
