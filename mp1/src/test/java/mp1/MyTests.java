package mp1;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class MyTests {

    private static Document studyInScarlet;
    private static Document tempest;
    private static Document persuasion;
    private static Document prideAndPrejudice;
    private static Document arcticCircle;
    private static Document blackPeter;
    private static Document flossieFumble;
    private static Document aesop;
    private static Document lastFreeMan;
    private static Document immortality;
    private static Document sixNapoleons;
    private static List<Document> docList1;

    @BeforeClass
    public static void setup() throws IOException {
        docList1 = new ArrayList<>();

        studyInScarlet = new Document("Conan Doyle: A Study in Scarlet", "http://www.gutenberg.org/files/244/244-0.txt");
        docList1.add(studyInScarlet);

        blackPeter = new Document("Conan Doyle: Adventure of Black Peter", "http://textfiles.com/stories/blackp.txt");
        docList1.add(blackPeter);

        tempest = new Document("Shakespeare: The Tempest", "http://www.gutenberg.org/files/23042/23042-0.txt");
        docList1.add(tempest);

        persuasion = new Document("Austen: Persuasion", "http://www.gutenberg.org/cache/epub/105/pg105.txt");
        docList1.add(persuasion);

        prideAndPrejudice = new Document("Austen: Pride and Prejudice", "http://www.gutenberg.org/files/1342/1342-0.txt");
        docList1.add(prideAndPrejudice);

        arcticCircle = new Document("Prentice: The Arctic Circle and Beyond", "http://textfiles.com/stories/arctic.txt");
        docList1.add(arcticCircle);

        flossieFumble = new Document("Flossie Fumble: Letter to Blossom", "http://textfiles.com/stories/blossom.pom");
        docList1.add(flossieFumble);

        aesop = new Document("Aesop: Fables", "http://textfiles.com/stories/aesop11.txt");
        docList1.add(aesop);

        lastFreeMan = new Document("Porretto: The Last Free Man", "http://textfiles.com/stories/freeman.fil");
        docList1.add(lastFreeMan);

        immortality = new Document("Burroughs: Immortality", "http://textfiles.com/stories/immortal");
        docList1.add(immortality);

        sixNapoleons = new Document("Conan Doyle: The Adventure of the Six Napoleons", "http://textfiles.com/stories/6napolen.txt");
        docList1.add(sixNapoleons);
    }

    @Test
    public void testGetId() {
        assertEquals("Burroughs: Immortality", immortality.getId());
    }

    @Test
    public void testWordCount() {
        assertEquals(8211, blackPeter.getWordCount());
    }

    @Test
    public void testJSDiv1() {
        assertEquals(30, blackPeter.computeJSDiv(studyInScarlet));
    }

    @Test
    public void testJSDiv2() {
        assertEquals(35, prideAndPrejudice.computeJSDiv(blackPeter));
    }

    @Test
    public void testJSDiv3() {
        assertEquals(0, prideAndPrejudice.computeJSDiv(prideAndPrejudice));
    }

    @Test
    public void testJSDiv4() {
        assertEquals(35, blackPeter.computeJSDiv(prideAndPrejudice));
    }

    @Test
    public void testSimilarity1() {
        assertEquals(new DocumentPair(prideAndPrejudice, persuasion), DocumentSimilarity.closestMatch(docList1));
    }

    @Test
    public void testSentiment1() {
        assertEquals(82, prideAndPrejudice.getOverallSentiment());
    }

    @Test
    public void testSentiment2() {
        assertEquals(20, studyInScarlet.getOverallSentiment());
    }

    @Test
    public void testSentiment3() {
        assertEquals(9, lastFreeMan.getOverallSentiment());
    }

    @Test
    public void testSentimentDiff1() {
        DocumentPair docPair = DocumentSimilarity.sentimentDiffMax(docList1);
        DocumentPair expPair = new DocumentPair(lastFreeMan, prideAndPrejudice);
        assertEquals(expPair, docPair);
    }

    @Test
    public void testGrouping1() {
        HashMap<Document, Document> expMap = new HashMap<>();
        expMap.put(tempest, aesop);
        expMap.put(flossieFumble, flossieFumble);
        expMap.put(lastFreeMan, lastFreeMan);
        expMap.put(studyInScarlet, aesop);
        expMap.put(persuasion, aesop);
        expMap.put(arcticCircle, aesop);
        expMap.put(blackPeter, aesop);
        expMap.put(aesop, aesop);
        expMap.put(prideAndPrejudice, aesop);
        expMap.put(sixNapoleons, aesop);
        expMap.put(immortality, immortality);
        assertEquals(expMap, DocumentSimilarity.groupSimilarDocuments(docList1, 4));
    }
}