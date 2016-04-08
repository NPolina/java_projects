package qa.project.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        Point p1 = new Point( -1, -3);
        Point p2 = new Point(2,-7);

        Point p3 = new Point(2,3);
        Point p4 = new Point(10,9);

        System.out.println("Расстояние между двумя точками p1("+ p1.x +","+ p1.y +") и p2("+ p2.x +","+ p2.y +") на плоскости = " + p1.distance(p2));
        System.out.println("Расстояние между двумя точками p3("+ p3.x +","+p3.y +") и p4("+ p4.x +","+ p4.y +") на плоскости = " + p3.distance(p4));
    }



}