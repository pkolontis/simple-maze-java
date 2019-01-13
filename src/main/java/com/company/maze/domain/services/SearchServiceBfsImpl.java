/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.maze.domain.services;

import com.company.maze.domain.entities.Maze;
import com.company.maze.domain.entities.Node;
import com.company.maze.domain.entities.Route;
import com.company.maze.domain.enums.NodeState;
import com.company.maze.domain.enums.NodeType;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the service that searches paths in a maze
 * based on start and goal nodes and builds routes.
 * 
 * This is an implementation of Breadth-first search (BFS) algorithm.
 * 
 * @author Petros Kolontis
 */
public class SearchServiceBfsImpl implements SearchService {
    
    private static final Logger log = LoggerFactory.getLogger(SearchServiceBfsImpl.class);
    
    /**
     * Searches the shortest path in a maze based on start and goal nodes
     * by implementing BFS algorithm and backtracking
     * 
     * @param maze the maze
     * @return the shortest route or null if not found
     * 
     * @throws IllegalArgumentException if maze is null
     */
    @Override
    public Route searchPath(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException("maze argument cannot be null");
        }
        
        Deque<Node> queue = new ArrayDeque<>();
        enqueueNode(queue, maze.getStart(), null); // enqueue start node
        while (!queue.isEmpty()) {
            Node node = queue.poll(); // remove the first element from queue
            log.debug("Node {} dequeued. Current Q {}", node, queue);
            if (node.equals(maze.getGoal())) {
                // goal node is found, backtrack to find the shortest and return
                return backtrackForShortest(node);
            }
            maze.getAdjacentNodesOf(node).values().forEach(adjNode->{
                if (NodeType.WALL.equals(adjNode.getType()) && !adjNode.getState().equals(NodeState.VISITED)) {
                    adjNode.setState(NodeState.VISITED);
                    return; // wall node ignored and marked as visited
                }
                enqueueNode(queue, adjNode, node); // enqueue adjacent that is not visited
            });
        }
        
        return null; // no route is found
    }
    
    /**
     * Searches all possible paths from start to goal node
     * by implementing BFS algorithm
     * 
     * @param maze the maze
     * @return a list of routes
     * 
     * @throws IllegalArgumentException if maze is null
     */
    @Override
    public List<Route> searchPaths(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException("maze argument cannot be null");
        }
        
        List<Route> routes = new ArrayList<>();
        
        Deque<List<Node>> queue = new ArrayDeque<>();
        buildNodesAndEnqueue(queue, new ArrayList(), maze.getStart()); // enqueue start node in a list
        while (!queue.isEmpty()) {
            List<Node> list = queue.poll(); // deque the first list from queue
            //log.debug("Nodes {} dequeued. Current Q {}", list, queue);
            Node lastNode = list.get(list.size() - 1);
            if (lastNode.equals(maze.getGoal())) {
                routes.add(new Route(list)); // list's lastNode is goal. So, this list is a route.
            }
            maze.getAdjacentNodesOf(lastNode).values().forEach(adjNode-> {
                if (NodeType.WALL.equals(adjNode.getType())) {
                    return; // wall node is ignored
                }
                if (!list.contains(adjNode)) {
                    // list does not contain adjacent node, 
                    // so a new list with adjacent node should be inspected
                    buildNodesAndEnqueue(queue, new ArrayList(list), adjNode);
                }
            });
        }
        
        return routes;
    }
    
    /**
     * Finding the shortest route by backtracking 
     * from goal node based on node's source
     * 
     * @param node the goal node
     * @return a route
     */
    private Route backtrackForShortest(Node node) {
        log.debug("Backtracking to find the shortest path by using from node");
        Deque<Node> nodes = new ArrayDeque<>();
        
        while (node != null) {
            nodes.addFirst(node);
            log.debug("Node {} added to Stack", node);
            node = node.getSource();
        }
        return new Route(new ArrayList<>(nodes));
    }
    
    /**
     * Enqueues a node if it is visited 
     * and sets its state as visited.
     * Moreover, set the source that enqueues the node
     * to be used for backtracking
     * 
     * @param queue the queue
     * @param node the node
     * @param source 
     */
    private void enqueueNode(Queue queue, Node node, Node source) {
        if (NodeState.NOT_VISITED.equals(node.getState())) {
            node.setSource(source);
            queue.add(node);
            node.setState(NodeState.VISITED);
            log.debug("Node {} enqueued from {}. Current Q {}", node, source != null ? source : "", queue);
        }
    }
    
    /**
     * Adds a node in a list of nodes and
     * enqueues list 
     * 
     * @param queue the queue
     * @param nodes the list of nodes
     * @param node the node
     */
    private void buildNodesAndEnqueue(Queue queue, List<Node> nodes, Node node) {
        nodes.add(node);
        queue.add(nodes);
        //log.debug("Nodes {} enqueued. Current Q {}", nodes, queue);
    }
}
