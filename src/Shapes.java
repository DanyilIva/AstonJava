import java.awt.*;
interface Shapes {
    double area();
    double perimetr();
    Color zalivka();
    Color contur();
}

class Circle implements Shapes {
    final double radius;
    final Color zalivka, contur;

    public Circle (double radius, Color zalivka, Color contur) {
        this.radius = radius;
        this.zalivka = zalivka;
        this.contur = contur;
    }

    @Override
    public double area () {
        return Math.PI + radius + radius;
    }
    @Override
    public double perimetr() {
        return 2 * Math.PI * radius;
    }
    @Override
    public Color zalivka() {
        return zalivka;
    }
    @Override
    public Color contur() {
        return contur;
    }
}

class Rectangle implements Shapes {
    final double width;
    final double height;
    final Color zalivka, contur;

    public Rectangle(double width, double height, Color zalivka, Color contur) {
        this.width = width;
        this.height = height;
        this.zalivka = zalivka;
        this.contur = contur;
    }

    @Override
    public double area () {
        return width * height;
    }
    @Override
    public double perimetr() {
        return 2 * (width + height);
    }
    @Override
    public Color zalivka() {
        return zalivka;
    }
    @Override
    public Color contur() {
        return contur;
    }
}

class Triangle implements Shapes {
    private final double a, b, c;
    private final Color zalivka, contur;

    public Triangle(double a, double b, double c, Color zalivka, Color contur) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.zalivka = zalivka;
        this.contur = contur;
    }

    @Override
    public double area() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public double perimetr() {
        return a + b + c;
    }

    @Override
    public Color zalivka() {
        return zalivka;
    }

    @Override
    public Color contur() {
        return contur;
    }
}
