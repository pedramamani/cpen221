import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.fail;

public class PoetryTest {

    @Test
    public void poet_song()  {
        try {
            String word = "song";
            UBCPoet poet = new UBCPoet(new File("vocabulary.txt"), new File("dict"));
            System.out.println("=== Poem with the word " + word);
            System.out.println(poet.verse(word));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void poet_moon()  {
        try {
            String word = "moon";
            UBCPoet poet = new UBCPoet(new File("vocabulary.txt"), new File("dict"));
            System.out.println("=== Poem with the word " + word);
            System.out.println(poet.verse(word));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void poet_meter() {
        try {
            String word = "meter";
            UBCPoet poet = new UBCPoet(new File("vocabulary.txt"), new File("dict"));
            System.out.println("=== Poem with the word " + word);
            System.out.println(poet.verse(word));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void poet_gun() {
        try {
            String word = "gun";
            UBCPoet poet = new UBCPoet(new File("vocabulary.txt"), new File("dict"));
            System.out.println("=== Poem with the word " + word);
            System.out.println(poet.verse(word));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void poet_babel() {
        try {
            String word = "babel";
            UBCPoet poet = new UBCPoet(new File("vocabulary.txt"), new File("dict"));
            System.out.println("=== Poem with the word " + word);
            System.out.println(poet.verse(word));
        } catch (Exception e) {
            fail();
        }
    }

}