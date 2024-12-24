package uzb.lab.imlolab.util;

public class CleaningWords {

    public static String cleanWord(String word) {
        return word.replaceAll("[\\p{Punct}&&[^']]|\\d", "");
    }

    public static String cleanFullWord(String word) {
        return word.replaceAll("[^a-zA-Zа-яА-ЯўқғҳЎҚҒҲ]", "");
    }

    public static String textFilter(String text) {
        text = text.toLowerCase().replaceAll("\n", " ").trim();
        text = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ'ʻ ]", " ").trim();
        text = text.replaceAll("\\s+", " ").trim();
        return text;
    }
}
