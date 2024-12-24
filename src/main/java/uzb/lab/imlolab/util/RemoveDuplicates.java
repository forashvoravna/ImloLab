package uzb.lab.imlolab.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
    public static String[] remove(String[] words) {
        Set<String> uniqueWordsSet = new HashSet<>(Arrays.asList(words));
        return uniqueWordsSet.toArray(new String[0]);
    }
}
