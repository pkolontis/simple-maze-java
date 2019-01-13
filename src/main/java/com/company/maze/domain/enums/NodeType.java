/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.enums;

import java.util.Arrays;

/**
 * Represents the type of a node in a maze.
 * A node can have only one type.
 * A default UNKNOWN is specified if value is illegal.
 * 
 * @author Petros Kolontis
 */
public enum NodeType {
    
    START('S'), GOAL('G'), WALL('X'), OPEN('_'), UNKNOWN('U');
    
    private final char value;

    private NodeType(char value) {
        this.value = value;
    }

    /**
     * Gets the value of a type
     * 
     * @return the value
     */
    public char getValue() {
        return value;
    }
    
    /**
     * Gets the node type based on the given value
     * 
     * @param value the value to set
     * @return the node type or default UNKNOWN if value is illegal
     */
    public static NodeType getNodeType(char value) {
        switch (value) {
            case 'S':
                return NodeType.START;
            case 'G':
                return NodeType.GOAL;
            case 'X':
                return NodeType.WALL;
            case '_':
                return NodeType.OPEN;
            default:
                return NodeType.UNKNOWN;
        }
    }
    
    /**
     * Gets all types excluding UNKNOWN (illegal)
     * and appends them to a string containing
     * type and value
     * 
     * @return all legal types as string
     */
    public static String getLegalTypes() {
        StringBuilder legalTypes = new StringBuilder();
        Arrays.stream(NodeType.values())
              .filter(type -> !type.equals(NodeType.UNKNOWN))
              .forEach(type -> {
                    if (legalTypes.length() > 0) {
                        legalTypes.append(",");
                    }
                    legalTypes.append(type).append("(").append(type.getValue()).append(")");
              });
        
        return legalTypes.toString();
    }
}
