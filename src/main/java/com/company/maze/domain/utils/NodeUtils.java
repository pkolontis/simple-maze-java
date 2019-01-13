/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.utils;

import com.company.maze.domain.entities.Node;
import com.company.maze.domain.enums.NodeType;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a class containing utilities for nodes
 * 
 * @author Petros Kolontis
 */
public class NodeUtils {
    
    private static final Logger log = LoggerFactory.getLogger(NodeUtils.class);
    
    /**
     * Gets a list of nodes containing the given 
     * type by searching on the given array of nodes.
     * An empty list will be returned if there
     * is no node of the given type.
     * 
     * @param nodes nodes to be searched
     * @param type the type to be found
     * @return a list of nodes of empty list
     * 
     * @throws IllegalArgumentException if nodes argument is null
     */
    public static List<Node> getNodeByType(Node[][] nodes, NodeType type) {
        if (nodes == null) {
            throw new IllegalArgumentException("the nodes argument cannot be null");
        }
        
        return Arrays.stream(nodes)
                     .flatMap(Arrays::stream)
                     .filter(node -> node != null && node.getType().equals(type))
                     .collect(Collectors.toList());
    }
    
    /**
     * Builds a 2d array of nodes from the given array of lines.
     * Each character from lines array represents a node.
     * The second dimension of 2d array is the maximum number
     * of columns in the array of lines
     * 
     * @param lines the array of lines to be used
     * 
     * @return a 2d array of nodes
     * 
     * @throws IllegalArgumentException if lines argument is null
     */
    public static Node[][] buildNodes(String[] lines) {
        if (lines == null) {
            throw new IllegalArgumentException("the lines argument cannot be null");
        }
        
        Node[][] nodes = new Node[lines.length][getMaxColsCount(lines)];
        
        for (int i = 0; i < lines.length; i++) {
            char[] cols = lines[i].toCharArray();
            for (int j = 0; j < cols.length; j++) {
                nodes[i][j] = new Node(new Node.Coordinate(i, j), NodeType.getNodeType(cols[j]));
                log.debug("Node {} of type {} successfully built", nodes[i][j], NodeType.getNodeType(cols[j]));
            }
        }
        
        return nodes;
    }
    
    /**
     * Gets the maximum number of columns
     * from the given array of lines used for 
     * defining the second dimension of a 2d array
     * 
     * @param lines the array of lines to set
     * @return the maximum number of columns
     * 
     * @throws NoSuchElementException if max count cannot be found
     */
    private static int getMaxColsCount(String[] lines) {
        return Arrays.stream(lines)
                     .mapToInt(String::length)
                     .max()
                     .getAsInt();
    }
}
