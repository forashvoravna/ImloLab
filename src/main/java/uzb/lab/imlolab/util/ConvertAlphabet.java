package uzb.lab.imlolab.util;

public class ConvertAlphabet {
    public static String cyrillToLatin(String text) {
        String[] cyrill = {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "қ", "с", "т", "у", "ў", "ғ", "ф", "х", "ҳ", "ц", "ч", "ш", "ы", "э", "ю", "я", "ъ", "ь"};
        String[] latin = {"a", "b", "v", "g", "d", "e", "yo", "j", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "q", "s", "t", "u", "o'", "g'", "f", "x", "h", "ts", "ch", "sh", "i", "e", "yu", "ya", "'", ""};
        for (int i = 0; i < cyrill.length; i++) {
            text = text.replace(cyrill[i], latin[i]);
        }
        return text;
    }

    public static String latinToCyrill(String text) {
        String[] latin = {"ts", "o'", "g'", "yo", "yu", "ya", "a", "b", "v", "g", "d", "e", "j", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "q", "s", "t", "u", "f", "x", "h", "ch", "sh", "i", "e", "'"};
        String[] cyrill = {"ц", "ў", "ғ", "ё", "ю", "я", "а", "б", "в", "г", "д", "е", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "қ", "с", "т", "у", "ф", "х", "ҳ", "ч", "ш", "ы", "э", "ъ"};
        for (int i = 0; i < latin.length; i++) {
            text = text.replace(latin[i], cyrill[i]);
        }
        return text;
    }

}
