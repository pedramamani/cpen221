package ca.ubc.ece.cpen221.mp2.boggle;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.graph.AdjacencyListGraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class BogglePlayer {
    private final String[] dictionary;
    private static boolean contains = false;

    /**
     * Create a new BogglePlayer with given dictionary.
     * @param dictionary that the game is played with
     */
    public BogglePlayer(String[] dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Get a set of all valid words on the board according to dictionary.
     * Valid words have minimum length of 3 letters and are constructed
     * by following a non-repeating path on the board.
     * Precondition: Board has at least dimensions 2x2
     * @param board the boggle board
     * @return a set of all valid words
     */
    public Set<String> getAllValidWords(BoggleBoard board) {
        Set<String> validWords = new TreeSet<>();

        for (String word : dictionary) {
            if (word.length() >= 3 && containsSequence(board, word)) {
                validWords.add(word);
            }
        }
        return validWords;
    }

    /**
     * Check if a board contains a given character sequence
     * @param board the board to perform search on
     * @param word thw word to search for on the board
     * @return true if the board contains the sequence and false otherwise
     */
    private boolean containsSequence(BoggleBoard board, String word) {
        Graph graph = graphize(board);
        char[] characters = word.toCharArray();
        List<Vertex> vertices = graph.getVertices();
        List<Vertex> startChars = new ArrayList<>();

        for (int v = 0; v < vertices.size(); v++) {
            if (vertices.get(v).getLabel().charAt(0) == characters[0]) {
                startChars.add(vertices.get(v));
            }
        }

        for (int i = 0; i < startChars.size(); i++) {
            contains = false;
            String curr = word.substring(0, 1);
            List<Vertex> visited = new ArrayList<>();
            visited.add(startChars.get(i));
            findSequence(graph, startChars.get(i), visited, curr, removeQus(word));

            if (contains) {
                return true;
            }
        }
        return false;
    }

    /**
     * recursive helper function to check if a given
     * word can be found from a given vertex in a
     * graph.
     * Postcondition: Sets answer attribute to true if word can be found
     * @param graph given graph that contains vertices
     * @param v starting vertex
     * @param visited list of visited vertices to prevent repeatition
     * @param curr word built from visited vertices
     * @param word test word
     */
    private static void findSequence(Graph graph,
                                     Vertex v, List<Vertex> visited, String curr, String word) {

        if (curr.equals(word)) {
            contains = true;
            return;
        }

        List<Vertex> neigh = graph.getNeighbors(v);
        for (Vertex k: neigh) {
            if (k.getLabel().charAt(0) == word.charAt(curr.length()) && !visited.contains(k)) {
                StringBuilder newCurr = new StringBuilder(curr);
                newCurr.append(k.getLabel().charAt(0));
                visited.add(k);
                findSequence(graph, k, visited, newCurr.toString(), word);
            }
        }
    }

    /**
     * removes 'U' characters from a given word if preceded by a 'Q'
     * @param word the word to process
     * @return a new String word with removed 'U'
     */
    private String removeQus(String word) {
        StringBuilder newWord = new StringBuilder(word.toUpperCase());
        for (int i = 0; i < newWord.length() - 1; i++) {
            if (newWord.charAt(i) == 'Q' && newWord.charAt(i + 1) == 'U') {
                newWord.deleteCharAt(i + 1);
            }
        }
        return newWord.toString();
    }

    /**
     * Represent the Boggle board as a graph. Each cell on the board is a vertex
     * and is connected to all 8 adjacent vertices. The board wraps around such that
     * cells on one edge of the board are connected to cells on the opposite
     * edge of the board.
     * @param board the board to represent
     * @return a Graph object representation of the board
     */
    private Graph graphize(BoggleBoard board) {
        int m = board.rows(), n = board.cols();
        Graph graph = new AdjacencyListGraph();
        List<List<Vertex>> vertices = new ArrayList<>(m);
        Vertex vertex;

        for (int i = 0; i < m; i++) {
            vertices.add(new ArrayList<>(n));
            for (int j = 0; j < n; j++) {
                vertex = new Vertex(Character.toString(board.getLetter(i, j)));
                vertices.get(i).add(vertex);
                graph.addVertex(vertex);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph.addEdge(vertices.get(i).get(j),
                        vertices.get((i + 1) % m).get((j) % n));
                graph.addEdge(vertices.get(i).get(j),
                        vertices.get((i) % m).get((j + 1) % n));
                graph.addEdge(vertices.get(i).get(j),
                        vertices.get((i + 1) % m).get((j + 1) % n));
                graph.addEdge(vertices.get(i).get(j),
                        vertices.get((i + m - 1) % m).get((j + 1) % n));
            }
        }

        return graph;
    }
}
