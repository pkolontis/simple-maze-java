# Project simple-maze-java
A basic programmatic approach to solve the simplest maze problem by using Java.


# General Info
The application has been developed to solve the problem of a simple maze
focused on code design, simplicity, readability, reusability and extensibility.
There is no target to find the most efficient algorithm in terms of performance
and used resources.


# The Problem:
A two-dimensional array of nodes represents a maze.
Each node can be maze's start or goal. 

The application will find an actor's route by inspecting
each node from start to goal in accordance with below restrictions:
- A maze can have only one start and goal, an arbitrary number
of wall nodes that cannot be used in a route and an arbitrary number
of open nodes that can be used in a route.
- The actor can inspect and move to any nodes that are North, South, East and West from 
its current node.

A maze is populated by reading a file (a sample file is provided in project's other sources).


# The Solution
- The application will find a sequence of nodes being the shortest route from start to goal.
- It will also find all available actor's routes from start to goal.


# Build and Package:
- The source code can be built, packaged and run by using Maven. 
- An executable jar is generated by packaging the project and its dependencies supporting
the standalone java execution. 

# Contributing:
- Project's contribution process is currently developed...
