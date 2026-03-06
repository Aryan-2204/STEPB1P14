import java.util.*;

public class PlagiarismDetector {

    HashMap<String, Set<String>> ngramIndex = new HashMap<>();

    public List<String> generateNgrams(String text, int n) {
        String[] words = text.split(" ");
        List<String> ngrams = new ArrayList<>();

        for (int i = 0; i <= words.length - n; i++) {
            String gram = "";
            for (int j = 0; j < n; j++)
                gram += words[i + j] + " ";

            ngrams.add(gram.trim());
        }

        return ngrams;
    }

    public void indexDocument(String docId, String text) {

        for (String gram : generateNgrams(text, 5)) {

            ngramIndex.putIfAbsent(gram, new HashSet<>());
            ngramIndex.get(gram).add(docId);
        }
    }

    public void checkDocument(String text) {

        int matches = 0;

        for (String gram : generateNgrams(text, 5)) {

            if (ngramIndex.containsKey(gram))
                matches++;
        }

        System.out.println("Matching n-grams: " + matches);
    }
}