abstract class Shape {
    public Shape() {
    }

    abstract double calcArea();

    private int dim1;
    private int dim2;

    public void set_dim1(int no1) {
        this.dim1 = no1;
    }

    public int get_dim1() {
        return this.dim1;
    }

    public void set_dim2(int no1) {
        this.dim2 = no1;
    }

    public int get_dim2() {
        return this.dim2;
    }
}

class Circle extends Shape {
    @Override
    double calcArea() {
        double radius = super.get_dim1();   
        double area = radius * radius * 3.14; 
        return area; 
    }
}

class Rectangle extends Shape {
    @Override
    double calcArea() {
        double width = super.get_dim1();   
        double length = super.get_dim2();
        return width * length; 
    }
}

class Triangle extends Shape {
    @Override
    double calcArea() {
        double width = super.get_dim1();   
        double length = super.get_dim2();
        return 0.5 * width * length; 
    }
}

class lab1 {

    // This method needs to be non-static so it can be called on an instance
    public void calcSum(Shape shape1, Shape shape2, Shape shape3) {
        System.out.println("\nThis is calcSum function");
        double result = shape1.calcArea();
        System.out.println("area = " + result);
        result = shape2.calcArea();
        System.out.println("area = " + result);
        result = shape3.calcArea();
        System.out.println("area = " + result);
    }
    
    public static void main(String[] args) {
        Triangle t1 = new Triangle();
        t1.set_dim1(5);
        t1.set_dim2(3);
        double result = t1.calcArea();
        System.out.println("Triangle area = " + result);

        Rectangle r1 = new Rectangle();
        r1.set_dim1(4);
        r1.set_dim2(7);
        result = r1.calcArea();
        System.out.println("Rectangle area = " + result);

        Circle c1 = new Circle();
        c1.set_dim1(2);
        result = c1.calcArea();
        System.out.println("Circle area = " + result);

        // Create an instance of Lab1 to call the non-static method
        lab1 lab1Instance = new lab1();
        lab1Instance.calcSum(t1, r1, c1);
    }
}

