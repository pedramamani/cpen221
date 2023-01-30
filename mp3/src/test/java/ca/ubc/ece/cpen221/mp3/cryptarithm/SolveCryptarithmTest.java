package ca.ubc.ece.cpen221.mp3.cryptarithm;

import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

public class SolveCryptarithmTest {

    @Test
    public void test1MainMethodSENDMOREMONEY() {
        final PrintStream stdout = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] testArr = {"SEND", "+", "MORE", "=", "MONEY"};
        SolveCryptarithm.main(testArr);

        assertEquals("1 solution(s):\n{R=8, S=9, D=7, E=5, Y=2, M=1, N=6, O=0}\n",
                outContent.toString());
        System.setOut(stdout);
    }

    @Test
    public void test2NoSolution() {
        final PrintStream stdout = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] testArr = {"I", "+", "CANT", "+", "GET", "=", "NO", "+", "SATISFACTION"};
        SolveCryptarithm.main(testArr);

        assertEquals("0 solution(s)\n", outContent.toString());
        System.setOut(stdout);
    }

    @Test
    public void test3Invalid() {
        final PrintStream stdout = System.out;
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] testArr = {"I", "+", "CANT", "+", "GET", "+", "NO", "+", "SATISFACTION"};
        SolveCryptarithm.main(testArr);

        assertEquals("Invalid Cryptarithm\n", outContent.toString());
        System.setOut(stdout);
    }

}
