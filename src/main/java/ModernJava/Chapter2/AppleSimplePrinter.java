package ModernJava.Chapter2;

public class AppleSimplePrinter implements ApplePrinter{
    @Override
    public String printApple(Apple apple) {
        return "Apple of " + apple.getWeight() + "g";
    }
}
