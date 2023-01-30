package mp1;

import java.net.URL;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;

public class Document implements Comparable<Document> {
    private final String docURL;
    private final String id;
    private final String language;
    private int overallSentiment;

    private int wordCount;
    private TextCollection textChunks = new TextCollection();
    private HashMap<String, Integer> wordMap = new HashMap<>();


    /**
     * Create a document given a URL to the document's text as well as an id
     *
     * @param id is not null and is not the empty string
     * @param url is not null and is not the empty string
     */
    Document(String id, String url) throws IOException {
        assert (id != null);
        assert (!id.equals(""));
        assert (url != null);
        assert (!url.equals(""));

        this.docURL = url;
        this.id = id;
        this.language = "en";
        this.wordCount = 0;
        decomposeText();
        this.overallSentiment = computeSentiment();
    }

    /**
     * If passed only a URL, set the id of document to the URL
     *
     * @param url is not null and is not the empty string
     */
    Document(String url) throws IOException {
        this(url, url);
    }

    /**
     * Compute the Jensen-Shannon Divergence between the two textList
     * on the basis of the words used.
     *
     * @param otherDoc is not null
     * @return JSD multiplied by 100 and rounded to the nearest integer
     */
    long computeJSDiv(Document otherDoc) {
        HashMap<String, Integer> wordFreqOther = new HashMap<>(otherDoc.getWordMap());
        int wordCountOther = otherDoc.getWordCount();
        double p, q, m;
        double sum = 0;

        for (String key: wordMap.keySet()) {
            p = (double) wordMap.get(key) / wordCount;
            if (wordFreqOther.containsKey(key)) {
                q = (double) wordFreqOther.get(key) / wordCountOther;
                wordFreqOther.remove(key);
                m = (p + q) / 2;
                sum += (p * Math.log(p / m) + q * Math.log(q / m)) / Math.log(2);
            } else {
                sum += p;
            }
        }

        for (String key: wordFreqOther.keySet()) {
            q = (double) wordFreqOther.get(key) / wordCountOther;
            sum += q;
        }
        return Math.round((sum / 2) * 100);
    }

    /**
     * Compute the overall sentiment of the Document.
     * The overall sentiment is the median sentiment obtained by computing
     * the median of segments of length of ~5000 characters.
     *
     * @return median sentiment score multiplied by 100 and rounded to the nearest integer
     */
    private int computeSentiment() throws IOException {
        AzureSentimentAnalysis.init();
        List<SentimentResponse> scores =
                new LinkedList<>(AzureSentimentAnalysis.getSentiments(textChunks));
        Collections.sort(scores);
        int size = scores.size();
        int median;

        if (scores.size() % 2 == 1) {
            median = scores.get((size - 1) / 2).getScore();
        } else {
            median = (scores.get(size / 2 - 1).getScore() + scores.get(size / 2).getScore()) / 2;
        }
        return Math.round(median);
    }

    /**
     * Pull full text from the URL and perform the following operations:
     * Construct the wordMap attribute, a map from all words contained in text to their counts
     * Break text into chunks and store it in the textChunks attribute to be compatible with Azure
     * Calculates the total word count and store it in the wordCount attribute
     */
    private void decomposeText() throws IOException {
        final int maxChunkSize = 5000;
        final int maxChunkCount = 100;

        try (Scanner fullText = new Scanner(new URL(docURL).openStream())) {
            StringBuilder chunk = new StringBuilder();
            String newWord;
            boolean doneChunks = false;

            while (fullText.hasNext()) {
                newWord = fullText.next();
                wordCount++;

                if (!wordMap.containsKey(newWord)) {
                    wordMap.put(newWord, 1);
                } else {
                    wordMap.put(newWord, wordMap.get(newWord) + 1);
                }

                if (!doneChunks) {
                    if ((chunk.length() + newWord.length()) <= maxChunkSize) {
                        chunk.append(newWord);
                        chunk.append(" ");
                    } else {
                        chunk.deleteCharAt(chunk.length() - 1);
                        textChunks.add(Integer.toString(textChunks.size()),
                                language, chunk.toString());
                        if (textChunks.size() == maxChunkCount) {
                            doneChunks = true;
                        }
                        chunk.delete(0, chunk.length());
                        chunk.append(newWord);
                        chunk.append(" ");
                    }
                }
            }
            if (!doneChunks) {
                chunk.deleteCharAt(chunk.length() - 1);
                textChunks.add(Integer.toString(textChunks.size()), language, chunk.toString());
                chunk.delete(0, chunk.length());
            }
        }
    }

    /**
     * @return the total word count of document
     */
    int getWordCount() {
        return wordCount;
    }

    /**
     * @return the overall sentiment of text as analyzed by Azure
     */
    int getOverallSentiment() {
        return overallSentiment;
    }

    /**
     * @return the map from words to their counts in the document
     */
    HashMap<String, Integer> getWordMap() {
        return wordMap;
    }

    /**
     * @return the id associated with the document
     */
    String getId() {
        return id;
    }

    /**
     * Compare two Document objects for equality (based on document id)
     *
     * @param other is not null
     * @return true if this Document and the other Document represent the same
     * document and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Document) {
            Document otherDoc = (Document) other;
            return (this.id.equals(otherDoc.id));
        } else {
            return false;
        }
    }

    /**
     * @return the hashcode for this Document object
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * @return a string representation of this Document
     */
    @Override
    public String toString() {
        return this.id;
    }

    public int compareTo(Document other) {
        if (this.equals(other)) {
            return 0;
        } else {
            return id.compareTo(other.id);
        }
    }
}
