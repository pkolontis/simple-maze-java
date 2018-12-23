/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.enums;

/**
 * Represents the four cardinal directions.
 * 
 * @author Petros Kolontis <petros.kolontis@gmail.com>
 */
public enum CardinalDirection {
    
    NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');
    
    private final char initial;

    private CardinalDirection(char initial) {
        this.initial = initial;
    }
    
    /**
     * Gets the cardinal direction based on direction's initial character.
     * Throws a runtime exception if an illegal initial is found.
     * 
     * @param initial the initial character to set
     * @return the cardinal direction
     */
    public CardinalDirection getCardinalDirection(char initial) {
        switch (initial) {
            case 'N':
                return CardinalDirection.NORTH;
            case 'S':
                return CardinalDirection.SOUTH;
            case 'E':
                return CardinalDirection.EAST;
            case 'W':
                return CardinalDirection.WEST;
            default:
                throw new IllegalArgumentException(String.format("Invalid CardinalDirection Initial : %c", initial));
        }
    }
}
