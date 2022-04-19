package ModernJava.Chapter3;

public class Letter {
    public static String addHeader(String text) {
        return "Header " + text;
    }

    public static String addFooter(String text) {
        return text + " Footer";
    }

    public static String checkSpelling(String text) {
        return text.replace("labda", "lambda");
    }
}
