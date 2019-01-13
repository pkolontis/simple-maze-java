/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.validators;

import com.company.maze.domain.entities.Node;
import com.company.maze.domain.enums.NodeType;
import com.company.maze.domain.ex.GoalNotFoundException;
import com.company.maze.domain.ex.GoalNotSingleException;
import com.company.maze.domain.ex.NodeTypeIllegalException;
import com.company.maze.domain.ex.NodesSizeInvalidException;
import com.company.maze.domain.ex.StartNotFoundException;
import com.company.maze.domain.ex.StartNotSingleException;
import com.company.maze.domain.utils.NodeUtils;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Validates an array of nodes that will be set in a maze.
 * Throws a suitable exception containing a specified
 * message when any constraint is violated.
 * 
 * @author Petros Kolontis
 */
public class NodesValidator {
    
    private static final Logger log = LoggerFactory.getLogger(NodesValidator.class);
    
    private static final String NODES_SIZE_NOT_VALID_MSG = "No valid size found. Array must be at least 2X2";
    private static final String START_NOT_FOUND_MSG = "No Start Node found";
    private static final String START_SINGLE_NOT_FOUND_MSG = "No single Start Node found. Found %s";
    private static final String GOAL_NOT_FOUND_MSG = "No Goal Node found";
    private static final String GOAL_SINGLE_NOT_FOUND_MSG = "No single Goal Node found. Found %s";
    private static final String NODETYPE_LEGAL_NOT_FOUND_MSG = "No legal NodeType found %s. Legal are %s";
    
    /**
     * Performs a validation in an array of nodes regarding 
     * size, start node existence and uniqueness,
     * goal node existence and uniqueness as well
     * as node type legality.
     * 
     * @param nodes the array of nodes to validate
     * @return a map of valid nodes such as start and goal
     * 
     * @throws NodesSizeInvalidException if an invalid size found
     * @throws StartNotFoundException if no start node found
     * @throws StartNotSingleException if no single start node found
     * @throws GoalNotFoundException if no goal node found
     * @throws GoalNotSingleException if no single node found
     * @throws NodeTypeIllegalException if illegal node type found
     * 
     */
    public static Map<NodeType, Node> validate(Node[][] nodes) {
        validateNodesSize(nodes);
        validateNodeTypeIllegal(nodes);
        
        Map<NodeType, Node> validNodes = new TreeMap<>();
        validateStart(nodes, validNodes);
        validateGoal(nodes, validNodes);
        
        return validNodes;
    }
    
    private static void validateNodesSize(Node[][] nodes) {
        log.debug("Validating if nodes array size is less than 2x2");
        if (nodes.length < 2 || nodes[0].length < 2) {
            log.error(NODES_SIZE_NOT_VALID_MSG);
            throw new NodesSizeInvalidException(NODES_SIZE_NOT_VALID_MSG);
        }
    }
    
    private static void validateNodeTypeIllegal(Node[][] nodes) {
        log.debug("Validating if there is any illegal NodeType");
        List<Node> nod = NodeUtils.getNodeByType(nodes, NodeType.UNKNOWN);
        if (!nod.isEmpty()) {
            String legalTypes = NodeType.getLegalTypes();
            log.error(String.format(NODETYPE_LEGAL_NOT_FOUND_MSG, nod, legalTypes));
            throw new NodeTypeIllegalException(String.format(NODETYPE_LEGAL_NOT_FOUND_MSG, nod, legalTypes));
        }
    }
    
    private static void validateStart(Node[][] nodes, Map<NodeType, Node> validNodes) {
        List<Node> startNodes = NodeUtils.getNodeByType(nodes, NodeType.START);
        validateStartNotFound(startNodes);
        validateStartNotSingle(startNodes);
        validNodes.put(NodeType.START, startNodes.get(0));
    }
    
    private static void validateStartNotFound(List<Node> startNodes) {
        log.debug("Validating if StartNode is found");
        if (startNodes.isEmpty()) {
            log.error(START_NOT_FOUND_MSG);
            throw new StartNotFoundException(START_NOT_FOUND_MSG);
        }
    }
    
    private static void validateStartNotSingle(List<Node> startNodes) {
        log.debug("Validating if StartNode is single");
        if (startNodes.size() > 1) {
            log.error(String.format(START_SINGLE_NOT_FOUND_MSG, startNodes));
            throw new StartNotSingleException(String.format(START_SINGLE_NOT_FOUND_MSG, startNodes));
        }
    }
    
    private static void validateGoal(Node[][] nodes, Map<NodeType, Node> validNodes) {
        List<Node> goalNodes = NodeUtils.getNodeByType(nodes, NodeType.GOAL);
        validateGoalNotFound(goalNodes);
        validateGoalNotSingle(goalNodes);
        validNodes.put(NodeType.GOAL, goalNodes.get(0));
    }
    
    private static void validateGoalNotFound(List<Node> goalNodes) {
        log.debug("Validating if GoalNode is found");
        if (goalNodes.isEmpty()) {
            log.error(GOAL_NOT_FOUND_MSG);
            throw new GoalNotFoundException(GOAL_NOT_FOUND_MSG);
        }
    }
    
    private static void validateGoalNotSingle(List<Node> goalNodes) {
        log.debug("Validating if GoalNode is single");
        if (goalNodes.size() > 1) {
            log.error(String.format(GOAL_SINGLE_NOT_FOUND_MSG, goalNodes));
            throw new GoalNotSingleException(String.format(GOAL_SINGLE_NOT_FOUND_MSG, goalNodes));
        }
    }
}
