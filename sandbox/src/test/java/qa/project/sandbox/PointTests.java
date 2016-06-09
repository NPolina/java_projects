package qa.project.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 14.04.2016.
 */
public class PointTests {

    Point p1 = new Point(-1, -3);
    Point p2 = new Point(2, -7);
    Point p3 = new Point(2,3);
    Point p4 = new Point(10,9);

    @Test
    public void testDistanceX() {
        Assert.assertEquals(p1.distanceX(p2.x),9.0);
        Assert.assertEquals(p3.distanceX(p4.x),6.0);
    }

    @Test
    public void testDistanceY(){
        Assert.assertEquals(p1.distanceY(p2.y),16.0);
        Assert.assertEquals(p3.distanceY(p4.y),36.0);
    }

    @Test
    public void testDistance(){
        Assert.assertEquals(p1.distance(p2),5.0);
        Assert.assertEquals(p3.distance(p4),10.0);
    }
}
