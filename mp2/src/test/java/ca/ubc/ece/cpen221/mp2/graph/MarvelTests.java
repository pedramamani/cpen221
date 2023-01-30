package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.core.Graph;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MarvelTests {

    private static Graph marvelList = new AdjacencyListGraph();

    @BeforeClass
    public static void setup() {
        String dir = "datasets/marvel.txt";
        String del = "\"";

        try (Scanner database = new Scanner(new File(dir)).useDelimiter(del)) {
            createGraph(database);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file!");
        }
    }

    private static void createGraph(Scanner database) {
        Set<Vertex> vertices = new HashSet<>();
        Vertex vertex;
        String prevBook = "AA2 35";
        String character, book;

        while (database.hasNext()) {
            character = database.next();
            database.next();
            book = database.next();
            database.next();
            vertex = new Vertex(character);

            marvelList.addVertex(vertex);

            if (book.equals(prevBook)) {
                for (Vertex v : vertices) {
                    marvelList.addEdge(vertex, v);
                }
            } else {
                prevBook = book;
                vertices.clear();
                marvelList.addVertex(vertex);
            }
            vertices.add(vertex);
        }
    }

    @Test
    public void marvelCenter() {
        Vertex center = Algorithms.center(marvelList);
        assert (center.equals(new Vertex("3-D MAN/CHARLES CHAN")));
    }

    @Test
    public void marvelDiameter() {
        assertEquals(5, Algorithms.diameter(marvelList));
    }
}
