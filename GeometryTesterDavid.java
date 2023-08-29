import java.util.List;
import java.util.stream.Stream;

/**
 * This class does some simple tessting of the Point and Line classes.
 */
public class GeometryTesterDavid {

    /**
     * The method is in charge of testing the Point class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testPoint() {
        boolean mistake = false;
        Point p1 = new Point(12, 2);
        Point p2 = new Point(9, -2);

        if (p1.getX() != 12) {
            System.out.println("Test p1.getX() failed.");
            mistake = true;
        }
        if (p1.getY() != 2) {
            System.out.println("Test p1.getY() failed.");
            mistake = true;
        }
        if (p1.distance(p1) != 0) {
            System.out.println("Test distance to self failed.");
            mistake = true;
        }
        if (p1.distance(p2) != p2.distance(p1)) {
            System.out.println("Test distance symmetry failed.");
            mistake = true;
        }
        if (p1.distance(p2) != 5) {
            System.out.println("Test distance failed.");
            mistake = true;
        }
        if (!p1.equals(p1)) {
            System.out.println("Equality to self failed.");
            mistake = true;
        }
        if (!p1.equals(new Point(12, 2))) {
            System.out.println("Equality failed.");
            mistake = true;
        }
        if (p1.equals(p2)) {
            System.out.println("Equality failed -- should not be equal.");
            mistake = true;
        }

        return !mistake;
    }

    /**
     * The method is in charge of testing the Line class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testLine() {
        Point bottomLeft = new Point(0, 0);
        Point topLeft = new Point(0, 1);
        Point topRight = new Point(1, 1);
        Point bottomRight = new Point(1, 0);

        testRectangle(bottomLeft, topLeft, topRight, bottomRight);

        boolean mistakes = false;
        Line l1 = new Line(12, 2, 9, -2);
        Line l2 = new Line(0, 0, 20, 0);
        Line l3 = new Line(9, 2, 12, -2);

        if (!l1.isIntersecting(l2)) {
            System.out.println("Test isIntersecting failed (1).");
            mistakes = true;
        }
        if (l1.isIntersecting(new Line(0, 0, 1, 1))) {
            System.out.println("Test isIntersecting failed (2).");
            mistakes = true;
        }
        Point intersectL1L2 = l1.intersectionWith(l2);
        if (!l1.middle().equals(intersectL1L2)) {
            System.out.println("Test intersectionWith middle failed.");
            mistakes = true;
        }

        return !mistakes;
    }

    private static void testRectangle(Point bl, Point tl, Point tr, Point br) {
        Line left = new Line(bl, tl);
        Line top = new Line(tl, tr);
        Line right = new Line(br, tr);
        Line bottom = new Line(bl, br);

        double leftX = bl.getX();
        double rightX = br.getX();
        double topY = tl.getY();
        double bottomY = bl.getY();
        double midX = (leftX + rightX) / 2;
        double midY = (topY + bottomY) / 2;

        Line horizontalCross = new Line(leftX - 1, midY, rightX + 1, midY);
        Line exactHorizontalCross = new Line(leftX, midY, rightX, midY);

        Line verticalCross = new Line(midX, topY + 1, midX, bottomY - 1);
        Line exactVerticalCross = new Line(midX, topY, midX, bottomY);

        Point horizontalLeftIntersection = new Point(leftX, midY);
        Point horizontalRightIntersection = new Point(rightX, midY);
        Point verticalTopIntersection = new Point(midX, topY);
        Point verticalBottomIntersection = new Point(midX, bottomY);

        List<TriTuple<Line, Line, Point>> cases = List.of(
                new TriTuple<>(left, horizontalCross, horizontalLeftIntersection),
                new TriTuple<>(left, exactHorizontalCross, horizontalLeftIntersection),
                new TriTuple<>(right, horizontalCross, horizontalRightIntersection),
                new TriTuple<>(right, exactHorizontalCross, horizontalRightIntersection),
                new TriTuple<>(top, verticalCross, verticalTopIntersection),
                new TriTuple<>(top, exactVerticalCross, verticalTopIntersection),
                new TriTuple<>(bottom, verticalCross, verticalBottomIntersection),
                new TriTuple<>(bottom, exactVerticalCross, verticalBottomIntersection)
        );

        cases.forEach(t -> expectedIntersection(t.first(), t.second(), t.third()));

        /*
        for (TriTuple<Line, Line, Point> c : cases) {
            Line l1 = c.first();
            Line l2 = c.second();
            Point expected = c.third();
            if (expectedIntersection(l1, l2, expected) == false) {
                System.out.println("Error: expected intersection between " + l1 + " to " + l2 + " to be " + expected + ", and it failed");
            }
        }*/
    }

    private static boolean expectedIntersection(Line l1, Line l2, Point expected) {
        Line oppositeL1 = new Line(l1.end(), l1.start());
        Line oppositeL2 = new Line(l2.end(), l2.start());

        List<Point> points = Stream.of(
                l1.intersectionWith(l2),
                l1.intersectionWith(oppositeL2),
                l2.intersectionWith(l1),
                l2.intersectionWith(oppositeL1),
                oppositeL1.intersectionWith(l2),
                oppositeL1.intersectionWith(oppositeL2),
                oppositeL2.intersectionWith(l1),
                oppositeL2.intersectionWith(oppositeL1)
        ).toList();

        for (Point p : points) {
            if (expected.equals(p) == false) {
                System.out.println("Error: expected intersection between " + l1 + " to " + l2 + " to be " + expected + ", got " + p + " instead");
                return false;
            }
        }

        return points.stream().allMatch(expected::equals);
    }

    /**
     * Main method, running tests on both the point and the line classes.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        GeometryTesterDavid tester = new GeometryTesterDavid();
        tester.testPoint();
        tester.testLine();
    }
}