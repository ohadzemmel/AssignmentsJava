import java.util.LinkedList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public java.util.List<Point> intersectionPoints(Line line) {
        Line[] lines = getLines();
        List<Point> llist = new LinkedList<>();
        if (line.isIntersecting(lines[0])) {
            llist.add(line.intersectionWith(lines[0]));
        }
        if (line.isIntersecting(lines[2])) {
            llist.add(line.intersectionWith(lines[2]));
        }
        if (line.isIntersecting(lines[1])) {
            llist.add(line.intersectionWith(lines[1]));
        }
        if (line.isIntersecting(lines[3])) {
            llist.add(line.intersectionWith(lines[3]));
        }
        return llist;
    }

    public Line[] getLines() {
        Point bottomLeft = new Point(this.upperLeft.getX(), this.height + this.upperLeft.getY());
        Point bottomRight = new Point(this.width + this.upperLeft.getX(), this.height +this.upperLeft.getY());
        Point upperRight = new Point(this.width + this.upperLeft.getX(), this.upperLeft.getY());
        Line upperLine = new Line(this.upperLeft, upperRight);
        Line leftLine = new Line(this.upperLeft, bottomLeft);
        Line bottomLine = new Line(bottomLeft, bottomRight);
        Line rightLine = new Line(upperRight, bottomRight);
        Line[] res = new Line[4];
        res[0] = upperLine;
        res[1] = leftLine;
        res[2] = bottomLine;
        res[3] = rightLine;
        return res;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public Point getLowerRight() {
        return new Point(width, height);
    }

}

