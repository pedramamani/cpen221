package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;


/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Lists.
 *  This data type does not implement multi-graphs or self-connecting edges.
 *
 *  RI: all Vertex objects in Sets in adjList values are in adjList.keySet()
 *  AF: v1-v2, v1-v3, v4 => adjList: {v1: {v2, v3}, v2: {v1}, v3: {v1}, v4: {}}
 ******************************************************************************/

public class AdjacencyListGraph implements Graph {

    private Map<Vertex, Set<Vertex>> adjList = new HashMap<>();

    /**
     * Add a vertex to the graph if it does not already exist.
     * @param v the vertex to be added
     */
    public void addVertex(Vertex v) {
        if (!adjList.keySet().contains(v)) {
            adjList.put(v, new HashSet<>());
        }
    }

    /**
     * Add an edge from v1 to v2 if it does not already exist. Does nothing if
     * either vertex does not exist in the graph.
     * @param v1 first vertex
     * @param v2 second vertex
     */
    public void addEdge(Vertex v1, Vertex v2) {
        if (adjList.keySet().contains(v1) && adjList.keySet().contains(v2)) {
            adjList.get(v1).add(v2);
            adjList.get(v2).add(v1);
        }
    }

    /**
     * Check if an edge connects two vertices v1 and v2.
     * @param v1 first vertex
     * @param v2 second vertex
     * @return true iff v1 and v2 are adjacent vertices in the graph and false
     * otherwise.
     */
    public boolean edgeExists(Vertex v1, Vertex v2) {
        return adjList.get(v1).contains(v2);
    }

    /**
     * Get a list containing all vertices that are adjacent to v. The vertices
     * are sorted in non-descending lexicographical order by their labels. The size
     * of the list is as small as possible (no trailing null elements).
     * Precondition: v is a vertex in the graph.
     * @param v the vertex whose neighbors are desired
     * @return an ordered list of vertices adjacent to v
     */
    public List<Vertex> getNeighbors(Vertex v) {
        ArrayList<Vertex> neighbors = new ArrayList<>(adjList.get(v));
        neighbors.sort(Comparator.comparing(Vertex::getLabel));
        neighbors.trimToSize();
        return neighbors;
    }

    /**
     * Get a list of all vertices in the graph. The vertices are in non-descending
     * lexicographical order by their labels. The size of the list is as small
     * as possible (no trailing null elements).
     * @return an ordered list of all vertices in the graph.
     */
    public List<Vertex> getVertices() {
        ArrayList<Vertex> verticesCopy = new ArrayList<>(adjList.keySet());
        verticesCopy.sort(Comparator.comparing(Vertex::getLabel));
        verticesCopy.trimToSize();
        return verticesCopy;
    }
}
