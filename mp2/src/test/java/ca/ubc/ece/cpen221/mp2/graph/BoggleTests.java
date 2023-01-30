package ca.ubc.ece.cpen221.mp2.graph;

import ca.ubc.ece.cpen221.mp2.boggle.BogglePlayer;
import ca.ubc.ece.cpen221.mp2.boggle.BoggleBoard;
import ca.ubc.ece.cpen221.utils.In;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.assertEquals;

public class BoggleTests {

    private static BoggleBoard board;
    private static BogglePlayer player;

    @BeforeClass
    public static void setup() {
        char[][] chars = {{'B', 'E'}, {'T', 'A'}};

        In in = new In(new File("datasets/dictionary-common.txt"));
        board = new BoggleBoard(chars);
        player = new BogglePlayer(in.readAllStrings());
    }

    @Test
    public void boggleValidWords() {
        int count = 0;
        for (String word : player.getAllValidWords(board)) {
            count++;
        }
        assertEquals(11, count);
    }
}
