package mp1;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

class DocumentSimilarity {

    /**
     * Determine the two documents from a given List that are most similar to
     * each other according to the Jensen-Shannon divergence score.
     *
     * @param docs is a List with at least two Document references
     * @return the DocumentPair that holds the two Documents most similar to
     * each other using the JS Divergence Score. If more than one
     * pair of Documents has the same similarity then returns any one.
     */
    static DocumentPair closestMatch(List<Document> docs) {
        DocumentPair minPair = new DocumentPair(docs.get(0), docs.get(1));
        DocumentPair tempPair;
        int minJSD = 100;
        int tempJSD;

        for (int i = 0; i < docs.size() - 1; i++) {
            for (int j = i + 1; j < docs.size(); j++) {
                tempPair = new DocumentPair(docs.get(i), docs.get(j));
                tempJSD = tempPair.getSimilarity();
                if (tempJSD < minJSD) {
                    minJSD = tempJSD;
                    minPair = tempPair;
                }
            }
        }
        return minPair;
    }

    /**
     * Return the two documents that have the greatest different in sentiment
     * scores as computed using Azure Computing Services.
     *
     * @param docs is not null
     * @return the DocumentPair with the two documents that have the greatest
     * difference in sentiment scores. If two pairs have the same difference
     * then the pair that has the lower JS Divergence is returned,
     * and if there is a still a tie then any pair that is part of the tie is returned.
     */
    static DocumentPair sentimentDiffMax(List<Document> docs) {
        DocumentPair maxPair = new DocumentPair(docs.get(0), docs.get(1));
        DocumentPair tempPair;
        int maxDiff = 0;
        int tempDiff;

        for (int i = 0; i < docs.size() - 1; i++) {
            for (int j = i + 1; j < docs.size(); j++) {
                tempPair = new DocumentPair(docs.get(i), docs.get(j));
                tempDiff = tempPair.getSentimentDiff();
                if (tempDiff > maxDiff || (tempDiff == maxDiff
                        && tempPair.compareTo(maxPair) < 0)) {
                    maxDiff = tempDiff;
                    maxPair = tempPair;
                }
            }
        }
        return maxPair;
    }

    /**
     * Determine a set of document groups where a group of Documents are more
     * similar to each other than to Documents in a different group.
     *
     * @param docs is a List with at least two Document references from which we
     * want to group Documents by similarity
     * @param nGroups n is the number of Document groups to create and 0 < nGroups <= n
     * @return a Map that represents how Documents are grouped.
     * Two Documents that are in the same group will map to the same Document,
     * and two Documents that are not in the same group will map to different
     * Documents. Further, the Document that represents a group will have the
     * lexicographically smallest id in that group.
     */
    static Map<Document, Document> groupSimilarDocuments(List<Document> docs, int nGroups) {
        Map<Document, Document> groups = new HashMap<>();
        List<DocumentPair> docPairs = new ArrayList<>();
        DocumentCollection docGrouping = new DocumentCollection();
        Document doc1, doc2;
        final int size = docs.size();

        for (Document doc : docs) {
            docGrouping.add(doc);
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                docPairs.add(new DocumentPair(docs.get(i), docs.get(j)));
            }
        }
        Collections.sort(docPairs);

        int i = 0;
        int countMerges = 0;
        while (size - countMerges > nGroups) {
            doc1 = docPairs.get(i).getDoc1();
            doc2 = docPairs.get(i).getDoc2();
            if (!docGrouping.find(doc1).equals(docGrouping.find(doc2))) {
                docGrouping.merge(doc1, doc2);
                countMerges++;
            }
            i++;
        }

        for (Document doc : docs) {
            groups.put(doc, docGrouping.find(doc));
        }
        return groups;
    }
}

