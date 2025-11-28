import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Animals.Dog dog = new Animals.Dog("Шарик");
        Animals.Cat cat = new Animals.Cat("Марта");

        dog.run(501);
        cat.run(100);
        dog.swim(15);
        cat.swim(10);

        System.out.println("Всего животных: " + Animals.Animal.getAnimals());
        System.out.println("Собак: " + Animals.Dog.getDogs());
        System.out.println("Котов: " + Animals.Cat.getCats());


        Animals.Miska miska = new Animals.Miska(10); // миска с 10 единицами еды
        miska.feed(cat, 15);     // еды не хватит
        miska.refill(10);          // добавляем еду
        miska.feed(cat, 5);      // кот поест


        Shapes shapes [] = {
                new Circle(5, Color.RED, Color.BLACK),
                new Rectangle(4, 6, Color.BLUE, Color.ORANGE),
                new Triangle(3, 4, 5, Color.GREEN, Color.CYAN)
        };

        for (Shapes shapes1 : shapes) {
            System.out.printf("\n\tФигура: %s\n", shapes1.getClass().getSimpleName());
            System.out.printf("\tПлощадь: %.2f\n", shapes1.area());
            System.out.printf("\tПериметр: %.2f\n", shapes1.perimetr());
            System.out.printf("\tЦвет заливки: %s\n", shapes1.zalivka());
            System.out.printf("\tЦвет контуров: %s\n", shapes1.contur());
            System.out.println();
        }
    }
}