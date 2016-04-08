package qa.project.sandbox;

/**
 * Created by user on 08.04.2016.
 */
public class Point {

    double x;
    double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }


    public double distance(Point p){
        return  Math.sqrt(distanceX(p.x) + distanceY(p.y));

    }

    public double distanceX(double x){
        return (x - this.x)*(x - this.x);
    }

    public double distanceY(double y){
        return (y - this.y)*(y - this.y);
    }
}
