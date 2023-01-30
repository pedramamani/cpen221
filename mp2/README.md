**CPEN 221 / Fall 2018: Machine Problem 2**

Design Document
===

**Students**\
Pedram Amani, 73993008, pedramamani\
Rehan Hafeez, 47057666, rhafeez221

**How do the adjacency list and adjacency matrix representation differ from each other? How will you implement these representations in Java for MP2? Notice that you cannot make assumptions about graph size ahead of time.**\
The adjacency list is a list of relationships between the vertices. If two vertices are adjacent (connected), they are related (placed in an array of size 2) in the adjacency list.
We will implement the adjacency list as a `List` of `Vertex[2]` arrays.
The adjacency matrix is an _n x n_ matrix with _n_ the number of vertices. Edges of adjacent vertices are represented by a binary _1/0_ entry in the corresponding row and column.
If a vertex is connected to itself, the corresponding diagonal entry takes a value of _2_. The matrix is diagonally symmetric.
We will implement the matrix as a `List` of a `List` of `Integer` objects.

**What is breadth first search on a graph? Construct a small graph (8-12 vertices and 16-20 edges) and illustrate the outcome from performing a BFS on this graph. You must indicate the output in a manner that is consistent with what is expected of the BFS implementation for this assignment.**\
Starting from a search key as our start index, all the adjacent vertices are visited in lexicographic order. For each adjacent vertex, the same algorithm is applied excluding the already visited vertices.
This produces an ordered `List` of vertex objects covering a full connected component of the graph.
Starting the BFS with different vertices as the search key produces _n_ lists with _n_ being the total number of vertices.
These lists are then returned in a `Set` (see provided diagram).

**State the representation invariant for the adjacency matrix implementation of the Graph interface.**\
The matrix is symmetric with non-diagonal entries _0/1_ and diagonal entries _0/2_.

**How will you strengthen the specification for the addVertex method when compared with the specification that is part of the Graph interface?**\
By weakening the precondition; we will check if the passed vertex exists in the `Graph` and only add it if it does not.

**Write a test case for the center method in Algorithms. Your test case must involve a graph with 6-8 vertices and 15-18 edges.**\
(See provided diagram).