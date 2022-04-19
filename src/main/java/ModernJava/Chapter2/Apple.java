package ModernJava.Chapter2;

public class Apple {
    Color color;
    Integer weight;

    public Apple() { }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public String getColorToString() {
        return color.toString();
    }

    public Integer getWeight() {
        return weight;
    }
}
