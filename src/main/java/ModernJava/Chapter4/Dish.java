package ModernJava.Chapter4;

public class Dish {
    String name;
    boolean vegetarian;
    int calories;
    String type;

    public int getCalories() {
        return this.calories;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean isVegetarian() {
        return this.vegetarian;
    }

    public Dish(String name, boolean vegetarian, Integer calories, String type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String toString() {
        return this.type + " type of " + this.name + " food(" + this.vegetarian + ") has " + this.calories;
    }
}
