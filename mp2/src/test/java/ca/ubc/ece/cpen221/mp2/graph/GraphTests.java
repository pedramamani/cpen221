package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class GraphTests {

    private static List<Vertex> f = new ArrayList<>();
    private static List<Vertex> n = new ArrayList<>();
    private static Graph singleList = new AdjacencyListGraph();
    private static Graph friendsList = new AdjacencyListGraph();
    private static Graph friendsMatrix = new AdjacencyMatrixGraph();
    private static Graph numbersList = new AdjacencyListGraph();
    private static Graph numbersMatrix = new AdjacencyMatrixGraph();

    @BeforeClass
    public static void setup() {
        String[] friends = {"Pedram", "Rehan", "Carly", "Hailey", "Bhavesh",
                            "JaeHyun", "Vala", "Ghazanfar", "Asghar"};
        int[][] friendRels = {
                {0, 1}, {0, 2}, {0, 3}, {0, 6}, {1, 4}, {1, 5},
                {1, 6}, {2, 3}, {2, 6}, {3, 5}, {3, 6}, {7, 8}};

        String[] numbers = {"01", "02", "03", "04", "05", "06", "07",
                            "08", "09", "10", "11", "12", "13"};
        int[][] numberRels = {
                {0, 3}, {0, 6}, {0, 8}, {0, 10}, {1, 7}, {1, 12}, {2, 3}, {2, 4},
                {4, 6}, {4, 8}, {5, 11}, {6, 11}, {7, 9}, {7, 12}, {9, 11}, {10, 11}};
        Vertex vertex;

        for (String friend : friends) {
            vertex = new Vertex(friend);
            f.add(vertex);
            friendsList.addVertex(vertex);
            friendsMatrix.addVertex(vertex);
        }
        for (int[] relation : friendRels) {
            friendsList.addEdge(f.get(relation[0]), f.get(relation[1]));
            friendsMatrix.addEdge(f.get(relation[0]), f.get(relation[1]));
        }

        for (String number : numbers) {
            vertex = new Vertex(number);
            n.add(vertex);
            numbersList.addVertex(vertex);
            numbersMatrix.addVertex(vertex);
        }
        for (int[] relation : numberRels) {
            numbersList.addEdge(n.get(relation[0]), n.get(relation[1]));
            numbersMatrix.addEdge(n.get(relation[0]), n.get(relation[1]));
        }

        singleList.addVertex(f.get(0));
    }

    @Test
    public void adjacencyListEdgeExists() {
        assert (friendsList.edgeExists(f.get(0), f.get(2)));
        assert (friendsList.edgeExists(f.get(1), f.get(4)));
        assert (friendsList.edgeExists(f.get(3), f.get(6)));
        assert (!friendsList.edgeExists(f.get(0), f.get(5)));
        assert (!friendsList.edgeExists(f.get(6), f.get(4)));
        assert (!friendsList.edgeExists(f.get(1), f.get(2)));
    }

    @Test
    public void adjacencyListGetNeighbors() {
        assert (friendsList.getNeighbors(f.get(2)).equals(new ArrayList<>(
                Arrays.asList(f.get(3), f.get(0), f.get(6)))));
        assert (friendsList.getNeighbors(f.get(4)).equals(new ArrayList<>(
                Collections.singletonList(f.get(1)))));
        assert (friendsList.getNeighbors(f.get(6)).equals(new ArrayList<>(
                Arrays.asList(f.get(2), f.get(3), f.get(0), f.get(1)))));
    }

    @Test
    public void adjacencyListGetVertices() {
        assert (friendsList.getVertices().equals(new ArrayList<>(
                Arrays.asList(f.get(8), f.get(4), f.get(2), f.get(7),
                        f.get(3), f.get(5), f.get(0), f.get(1), f.get(6)))));
    }

    @Test
    public void adjacencyMatrixEdgeExists() {
        assert (friendsMatrix.edgeExists(f.get(0), f.get(2)));
        assert (friendsMatrix.edgeExists(f.get(1), f.get(4)));
        assert (friendsMatrix.edgeExists(f.get(3), f.get(6)));
        assert (!friendsMatrix.edgeExists(f.get(0), f.get(5)));
        assert (!friendsMatrix.edgeExists(f.get(6), f.get(4)));
        assert (!friendsMatrix.edgeExists(f.get(1), f.get(2)));
    }

    @Test
    public void adjacencyMatrixGetNeighbors() {
        assert (friendsMatrix.getNeighbors(f.get(2)).equals(new ArrayList<>(
                Arrays.asList(f.get(3), f.get(0), f.get(6)))));
        assert (friendsMatrix.getNeighbors(f.get(4)).equals(new ArrayList<>(
                Collections.singletonList(f.get(1)))));
        assert (friendsMatrix.getNeighbors(f.get(6)).equals(new ArrayList<>(
                Arrays.asList(f.get(2), f.get(3), f.get(0), f.get(1)))));
    }

    @Test
    public void adjacencyMatrixGetVertices() {
        assert (friendsMatrix.getVertices().equals(new ArrayList<>(
                Arrays.asList(f.get(8), f.get(4), f.get(2), f.get(7), f.get(3),
                        f.get(5), f.get(0), f.get(1), f.get(6)))));
    }

    @Test
    public void algorithmsShortestDistance() {
        assertEquals(1,
                Algorithms.shortestDistance(friendsList, f.get(0), f.get(2)));
        assertEquals(3,
                Algorithms.shortestDistance(friendsList, f.get(3), f.get(4)));
        assertEquals(0,
                Algorithms.shortestDistance(friendsList, f.get(6), f.get(6)));
        assertEquals(-1,
                Algorithms.shortestDistance(friendsList, f.get(2), f.get(7)));
        assertEquals(5,
                Algorithms.shortestDistance(numbersList, n.get(4), n.get(1)));
        assertEquals(4,
                Algorithms.shortestDistance(numbersList, n.get(9), n.get(8)));

        assertEquals(1,
                Algorithms.shortestDistance(friendsMatrix, f.get(0), f.get(2)));
        assertEquals(3,
                Algorithms.shortestDistance(friendsMatrix, f.get(3), f.get(4)));
        assertEquals(0,
                Algorithms.shortestDistance(friendsMatrix, f.get(6), f.get(6)));
        assertEquals(-1,
                Algorithms.shortestDistance(friendsMatrix, f.get(2), f.get(7)));
        assertEquals(5,
                Algorithms.shortestDistance(numbersMatrix, n.get(4), n.get(1)));
        assertEquals(4,
                Algorithms.shortestDistance(numbersMatrix, n.get(9), n.get(8)));
    }

    @Test
    public void algorithmsBreadthFirstSearch() {
        Set<List<Vertex>> searchFriends = new HashSet<>();
        Set<List<Vertex>> searchNumbers = new HashSet<>();
        List<Vertex> newQuery;

        int[][] friendsBFS = {
                {0, 2, 3, 1, 6, 5, 4}, {1, 4, 5, 0, 6, 3, 2}, {2, 3, 0, 6, 5, 1, 4},
                {3, 2, 5, 0, 6, 1, 4}, {4, 1, 5, 0, 6, 3, 2}, {5, 3, 1, 2, 0, 6, 4},
                {6, 2, 3, 0, 1, 5, 4}, {7, 8}, {8, 7}};
        int[][] numbersBFS = {
                {0, 3, 6, 8, 10, 2, 4, 11, 5, 9, 7, 1, 12},
                {1, 7, 12, 9, 11, 5, 6, 10, 0, 4, 3, 8, 2},
                {2, 3, 4, 0, 6, 8, 10, 11, 5, 9, 7, 1, 12},
                {3, 0, 2, 6, 8, 10, 4, 11, 5, 9, 7, 1, 12},
                {4, 2, 6, 8, 3, 0, 11, 10, 5, 9, 7, 1, 12},
                {5, 11, 6, 9, 10, 0, 4, 7, 3, 8, 2, 1, 12},
                {6, 0, 4, 11, 3, 8, 10, 2, 5, 9, 7, 1, 12},
                {7, 1, 9, 12, 11, 5, 6, 10, 0, 4, 3, 8, 2},
                {8, 0, 4, 3, 6, 10, 2, 11, 5, 9, 7, 1, 12},
                {9, 7, 11, 1, 12, 5, 6, 10, 0, 4, 3, 8, 2},
                {10, 0, 11, 3, 6, 8, 5, 9, 2, 4, 7, 1, 12},
                {11, 5, 6, 9, 10, 0, 4, 7, 3, 8, 2, 1, 12},
                {12, 1, 7, 9, 11, 5, 6, 10, 0, 4, 3, 8, 2}};

        for (int[] query : friendsBFS) {
            newQuery = new ArrayList<>();
            for (int vertex : query) {
                newQuery.add(f.get(vertex));
            }
            searchFriends.add(new ArrayList<>(newQuery));
        }

        for (int[] query : numbersBFS) {
            newQuery = new ArrayList<>();
            for (int vertex : query) {
                newQuery.add(n.get(vertex));
            }
            searchNumbers.add(new ArrayList<>(newQuery));
        }

        assert (Algorithms.breadthFirstSearch(friendsList).equals(searchFriends));
        assert (Algorithms.breadthFirstSearch(numbersList).equals(searchNumbers));
        assert (Algorithms.breadthFirstSearch(numbersMatrix).equals(searchNumbers));
        assert (Algorithms.breadthFirstSearch(friendsMatrix).equals(searchFriends));
    }

    @Test
    public void algorithmsCenter() {
        assert (Algorithms.center(friendsList).equals(f.get(5)));
        assert (Algorithms.center(numbersList).equals(n.get(11)));
        assert (Algorithms.center(friendsMatrix).equals(f.get(5)));
        assert (Algorithms.center(numbersMatrix).equals(n.get(11)));
    }

    @Test
    public void algorithmsDepthFirstSearch() {
        Set<List<Vertex>> searchFriends = new HashSet<>();
        List<Vertex> newQuery;

        int[][] friendsDFS = {
                {0, 2, 3, 5, 1, 4, 6}, {1, 4, 5, 3, 2, 0, 6}, {2, 3, 5, 1, 4, 0, 6},
                {3, 2, 0, 1, 4, 5, 6}, {4, 1, 5, 3, 2, 0, 6}, {5, 3, 2, 0, 1, 4, 6},
                {6, 2, 3, 5, 1, 4, 0}, {7, 8}, {8, 7}};

        for (int[] query : friendsDFS) {
            newQuery = new ArrayList<>();
            for (int vertex : query) {
                newQuery.add(f.get(vertex));
            }
            searchFriends.add(new ArrayList<>(newQuery));
        }

        assert (Algorithms.depthFirstSearch(friendsList).equals(searchFriends));
    }

    @Test
    public void algorithmsDiameter() {
        assertEquals(-1, Algorithms.diameter(singleList));
        assertEquals(3, Algorithms.diameter(friendsList));
        assertEquals(6, Algorithms.diameter(numbersList));

        assertEquals(3, Algorithms.diameter(friendsMatrix));
        assertEquals(6, Algorithms.diameter(numbersMatrix));
    }
}
