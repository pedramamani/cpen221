package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/******************************************************************************
 *  Dependencies: Graph.java Vertex.java
 *
 *  A data type that represents a Graph using Adjacency Matrices.
 *
 * Note: F represents "false" and T represents "true"
 * RI: adjMatrix is a symmetric matrix with F diagonal elements
 *     adjMatrix and vertices are of equal size
 *     all values in vertices are integers strictly less than vertices.size()
 * AF: v1-v2, v1-v3, v4 => vertices: {v1: 0, v2: 1, v3: 2, v4: 3}
 *                      => adjMatrix: {{F, T, T, F},
 *                                     {T, F, F, F},
 *                                     {T, F, F, F},
 *                                     {F, F, F, F}}
 ******************************************************************************/

public class AdjacencyMatrixGraph implements Graph {

    private List<List<Boolean>> adjMatrix = new ArrayList<>();
    private Map<Vertex, Integer> vertices = new HashMap<>();

    /**
     * Add a vertex to the graph if it does not already exist.
     * @param v the vertex to be added
     */
    public void addVertex(Vertex v) {
        if (!vertices.keySet().contains(v)) {
            int index = vertices.size();
            ArrayList<Boolean> newRow = new ArrayList<>(index + 1);

            vertices.put(v, index);

            for (int row = 0; row < index; row++) {
                adjMatrix.get(row).add(false);
            }
            for (int i = 0; i <= index; i++) {
                newRow.add(i, false);
            }

            adjMatrix.add(newRow);
        }
    }

    /**
     * Add an edge from v1 to v2 if it does not already exist. Does nothing
     * if the vertices do not exist in the graph.
     * @param v1 first vertex
     * @param v2 second vertex
     */
    public void addEdge(Vertex v1, Vertex v2) {
        if (vertices.keySet().contains(v1) && vertices.keySet().contains(v2)) {
            adjMatrix.get(vertices.get(v1)).set(vertices.get(v2), true);
            adjMatrix.get(vertices.get(v2)).set(vertices.get(v1), true);
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
        return adjMatrix.get(vertices.get(v1)).get(vertices.get(v2));
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
        ArrayList<Vertex> neighbors = new ArrayList<>();
        int index = vertices.get(v);

        for (Vertex vertex : vertices.keySet()) {
            if (adjMatrix.get(index).get(vertices.get(vertex))) {
                neighbors.add(vertex);
            }
        }

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
        ArrayList<Vertex> verticesCopy = new ArrayList<>(vertices.keySet());
        verticesCopy.sort(Comparator.comparing(Vertex::getLabel));
        verticesCopy.trimToSize();
        return verticesCopy;
    }
}
