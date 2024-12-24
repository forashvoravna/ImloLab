package uzb.lab.imlolab.util;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class SimilarityChecker {

    public static double getCheckPercent(String input, String dbWord) {

        LevenshteinDistance distance = new LevenshteinDistance();
        int result = distance.apply(input, dbWord);

        int maxLength = Math.max(input.length(), dbWord.length());
        double similarity = (1 - ((double) result / maxLength)) * 100;
        return similarity;
    }
}
