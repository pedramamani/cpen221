package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

public class Algorithms {

    /**
     * Find the diameter of a graph. The diameter of a graph is the maximum
     * finite distance among the distances between all pairs of vertices in the
     * graph. The diameter of a graph with one Vertex is 0 by definition. When
     * all distances are infinite the diameter is infinite, and -1 is returned.
     * @param graph the graph to find diameter of
     * @return the diameter of the graph, -1 if diameter is infinite
     */
    public static int diameter(Graph graph) {
        int eccent;
        int maxEccent = 0;

        if (graph.getVertices().size() == 1) {
            return -1;
        }

        for (Vertex vertex : graph.getVertices()) {
            eccent = getCentrality(graph, vertex)[1];

            if (eccent > maxEccent) {
                maxEccent = eccent;
            }
        }
        return maxEccent;
    }

    /**
     * Perform a complete depth first search of the given graph. Start with the
     * search at each vertex of the graph and create a list of the vertices
     * visited. Return a set where each element of the set is a list of elements
     * seen by starting a DFS at a specific vertex of the graph.
     * @param graph  the graph to perform search on
     * @return a set all queries as lists of vertices
     */
    public static Set<List<Vertex>> depthFirstSearch(Graph graph) {
        Set<List<Vertex>> queries = new HashSet<>();
        ArrayList<Vertex> query;
        int key;
        boolean newKey;

        for (Vertex vertex : graph.getVertices()) {
            query = new ArrayList<>(Collections.singleton(vertex));
            key = 0;
            while (key >= 0) {
                newKey = false;
                for (Vertex n : graph.getNeighbors(query.get(key))) {
                    if (!query.contains(n)) {
                        query.add(n);
                        key = query.size() - 1;
                        newKey =  true;
                        break;
                    }
                }
                if (!newKey) {
                    key--;
                }
            }
            query.trimToSize();
            queries.add(query);
        }
        return queries;
    }

    /**
     * Perform a complete breadth first search of the given graph. Start with the
     * search at each vertex of the graph and create a list of the vertices
     * visited. Return a set where each element of the set is a list of elements
     * seen by starting a BFS at a specific vertex of the graph (the number of
     * elements in the returned set should correspond to the number of graph
     * vertices).
     * @param graph the graph to perform search on
     * @return the set of all queries as lists of vertices
     */
    public static Set<List<Vertex>> breadthFirstSearch(Graph graph) {
        Set<List<Vertex>> queries = new HashSet<>();
        ArrayList<Vertex> query = new ArrayList<>();
        List<Vertex> temp = new ArrayList<>();
        int key;

        for (Vertex vertex : graph.getVertices()) {
            key = 0;
            query.add(vertex);
            while (key < query.size()) {
                for (Vertex v : graph.getNeighbors(query.get(key))) {
                    if (!query.contains(v)) {
                        temp.add(v);
                    }
                }
                temp.sort(Comparator.comparing(Vertex::getLabel));
                query.addAll(temp);
                temp.clear();
                key++;
            }
            query.trimToSize();
            queries.add(new ArrayList<>(query));
            query.clear();
        }
        return queries;
    }

    /**
     * Find the center of the graph. The center is defined as the vertex in the largest
     * component of the graph with lowest eccentricity (eccentricity of a vertex is its
     * maximum distance from connected vertices).
     * Precondition: The graph is not empty.
     * @param graph the graph to find the center of
     * @return the center as a Vertex object
     */
    public static Vertex center(Graph graph) {
        Vertex center = new Vertex("");
        int maxSize = 0;
        int minEccent = 0;
        int[] attributes;

        for (Vertex vertex : graph.getVertices()) {
            attributes = getCentrality(graph, vertex);

            if (attributes[0] > maxSize || attributes[0] == maxSize && attributes[1] < minEccent) {
                minEccent = attributes[1];
                maxSize = attributes[0];
                center = vertex;
            }
        }
        return center;
    }

    /**
     * Find the length of the shortest path to go from one vertex to another.
     * Precondition: Passed vertices are in the graph.
     *
     * @param graph the graph
     * @param v1 first vertex
     * @param v2 second vertex
     * @return an integer indicating the shortest distance (-1 if a path does
     * not exist).
     */
    public static int shortestDistance(Graph graph, Vertex v1, Vertex v2) {
        List<Set<Vertex>> visited = new ArrayList<>(Arrays.asList(
                new HashSet<>(), new HashSet<>()));
        List<Set<Vertex>> fronts = new ArrayList<>(Arrays.asList(
                new HashSet<>(Collections.singleton(v1)),
                new HashSet<>(Collections.singleton(v2))));
        int distance = 0;

        if (shareVertex(fronts.get(0), fronts.get(1))) {
            return distance;
        }

        while (fronts.get(0).size() + fronts.get(1).size() != 0) {
            for (int i = 0; i <= 1; i++) {
                visited.get(i).addAll(fronts.get(i));
                fronts.set(i, newNeighbors(graph, fronts.get(i), visited.get(i)));
                distance++;

                if (shareVertex(fronts.get(0), fronts.get(1))) {
                    return distance;
                }
            }
        }
        return -1;
    }

    /**
     * Get a set of Vertex objects called new neighbors. New neighbors are neighbors of
     * all passed vertices in the front set, provided that they do not appear in the
     * visited set.
     * Precondition: The vertex is in the graph
     * @param graph the graph
     * @param front the set of vertices to find new neighbors of
     * @param visited the set of vertices that have already been visited
     * @return the set of new neighbors as Vertex objects
     */
    private static Set<Vertex> newNeighbors(Graph graph, Set<Vertex> front, Set<Vertex> visited) {
        Set<Vertex> temp = new HashSet<>();

        for (Vertex v : front) {
            for (Vertex neighbor : graph.getNeighbors(v)) {
                if (!visited.contains(neighbor)) {
                    temp.add(neighbor);
                }
            }
        }

        return new HashSet<>(temp);
    }

    /**
     * For a given vertex of a graph, get the size of the component of the graph
     * the vertex is in and its eccentricity as an int[] array of size 2. The size
     * of a component is the number of all connected vertices. The eccentricity
     * of a vertex is the maximum distance between the vertex and another vertex
     * in the same component.
     * @param graph the graph containing vertex
     * @param vertex the vertex to find attributes of
     * @return an int[] array of size 2 with its first index being the component
     * size and its second index being the eccentricity of the vertex
     */
    private static int[] getCentrality(Graph graph, Vertex vertex) {
        Set<Vertex> current = new HashSet<>(Collections.singleton(vertex));
        Set<Vertex> visited = new HashSet<>();
        Set<Vertex> previous;
        int eccent = -1;

        while (current.size() != 0) {
            previous = new HashSet<>(current);
            current.clear();

            for (Vertex v : previous) {
                for (Vertex n : graph.getNeighbors(v)) {
                    if (!visited.contains(n)) {
                        current.add(n);
                    }
                }
            }
            visited.addAll(current);
            eccent++;
        }

        return new int[]{visited.size(), eccent};
    }

    /**
     * Check if two sets share any Vertex objects.
     * @param set1 the first set
     * @param set2 the second set
     * @return true if any one Vertex object is contained in both sets and false
     * otherwise.
     */
    private static boolean shareVertex(Set<Vertex> set1, Set<Vertex> set2) {
        for (Vertex v1 : set1) {
            for (Vertex v2 : set2) {
                if (v1.equals(v2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
