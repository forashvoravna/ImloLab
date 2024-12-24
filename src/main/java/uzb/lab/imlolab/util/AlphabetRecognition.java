package uzb.lab.imlolab.util;

public class AlphabetRecognition {

    public static boolean isKirill(String text) {
        return text.matches(".*[а-яА-ЯёЁўЎқҚғҒҳҲ].*");
    }

    public static boolean isLotin(String text) {
        return text.matches(".*[a-zA-Z].*");
    }
}
