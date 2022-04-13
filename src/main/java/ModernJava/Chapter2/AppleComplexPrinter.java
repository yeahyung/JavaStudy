package ModernJava.Chapter2;

public class AppleComplexPrinter implements ApplePrinter {

    @Override
    public String printApple(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "Apple is " + characteristic + " and " +  apple.getColor() + " color";
    }
}
