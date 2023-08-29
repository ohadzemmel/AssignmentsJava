import java.text.DecimalFormat;
import java.util.Random;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    public double length() {
        double dx = (this.end.getX() - this.start.getX());
        double dy = (this.end.getY() - this.start.getY());
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point middle() {
        double mx = ((this.start.getX() + this.end.getX()) / 2);
        double my = ((this.start.getY() + this.end.getY()) / 2);
        return new Point(mx, my);
    }

    public Point start() {
        return start;
    }

    public Point end() {
        return end;
    }

    public double dy() {
        return end.getY() - start.getY();
    }

    public double dx() {
        return end.getX() - start.getX();
    }

    public double m() {
        if (MathUtil.doubleEqual(dx(), 0)) {
            return Double.POSITIVE_INFINITY;
        }
        return dy() / dx();
    }

    public double b() {
        double m = m();
        if (m == Double.POSITIVE_INFINITY) {
            throw new RuntimeException("Cannot calculate B for vertical line!");
        }
        return start.getY() - m * start.getX();
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) {
        /*
        double thisM = m();
        double otherM = other.m();

        double range_x_start = Math.min(this.start.getX(), other.start.getX());
        double range_x_end = Math.min(this.end.getX(), other.end.getX());
        double range_y_start = Math.min(this.start.getY(), other.start.getY());
        double range_y_end = Math.min(this.end.getX(), other.end.getX());

        if(this.b() == other.b() && ())

        if (MathUtil.doubleEqual(thisM, otherM)) {
            // maybe intersecting one point or more
            // [TODO] fix in case of single intersection point

            if (this.b() == other.b())
            {
                // infinite intersections
                return null;
            }
            else
            {
                // no intersections
                return null;
            }
        }
        else
        {

        }

        if (hasEdgeIntersectionWith(other) != null) {
            return null;
        }
        if(other.isOnLine(start) || other.isOnLine(end)){
            return null;
        }
        return null;
    }

         */
            double thisM = this.m();
            double otherM = other.m();
            if (MathUtil.doubleEqual(thisM, otherM)) {
                if (this.hasEdgeIntersectionWith(other) != null) {
                    return null;
                } else {
                    return !other.isOnLine(this.start) && !other.isOnLine(this.end) ? null : null;
                }
            } else {
                double colX;
                double colY;
                if (thisM == Double.POSITIVE_INFINITY) {
                    colX = this.start.getX();
                    colY = other.calcUnboundedY(colX);
                } else if (otherM == Double.POSITIVE_INFINITY) {
                    colX = other.start.getX();
                    colY = this.calcUnboundedY(colX);
                } else {
                    colX = (other.b() - this.b()) / (thisM - otherM);
                    colY = this.calcUnboundedY(colX);
                }

                Point col = new Point(colX, colY);
                return this.isOnLine(col) && other.isOnLine(col) ? col : null;
            }
        }




        private double calcUnboundedY(double x) {
        double m = m();
        if (m == Double.POSITIVE_INFINITY) {
            throw new RuntimeException("Invalid operation - cannot calculate Y for vertical line!");
        }

        return x * m + b();
    }

    public boolean isOnLine(Point p) {
        Line sp = new Line(start, p);
        Line ep = new Line(end, p);
        if (MathUtil.doubleEqual(start.getY() , end.getY()) && MathUtil.doubleEqual(start.getY() , p.getY())){
            return true;
        }
        double spM = sp.m();
        double epM = ep.m();
        double m = m();

        if (!MathUtil.doubleEqual(m, spM)) return false;

        if (!MathUtil.doubleEqual(spM, epM)) return false;

        double len = length();
        return sp.length() <= len && ep.length() <= len;
    }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        if (start.equals(other.end) && end.equals(other.start)){
            return true;
        }
        return start.equals(other.start) && end.equals(other.end);
    }




    private Point hasEdgeIntersectionWith(Line other) {
        if (start.equals(other.end)){
            return start;
        }
        if (start.equals(other.start)) {
            return start;
        }
        if (end.equals(other.end)) {
            return end;
        }
        if (end.equals(other.start)) {
            return end;
        }
        return null;
    }
    public static Line generateRandomLine(int lim){
        Random rand = new Random();
        int x1 = rand.nextInt(lim) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(lim) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(lim) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(lim) + 1; // get integer in range 1-300
        return  new Line(x1,y1 , x2, y2);
    }
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect){
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(new Line(this.start , this.end));
        if (intersectionPoints.size() > 0){
            Point res = intersectionPoints.get(0);
            Point point = intersectionPoints.get(1);
                Line l1 = new Line(this.start , point);
                Line l2 = new Line(this.start , res);
                if (l1.length() < l2.length()){
                    res = point;
                }
            return res;
        }
        return null;
}

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return String.format("Line((%s,%s) -> (%s, %s))", df.format(start.getX()), df.format(start.getY()), df.format(end.getX()), df.format(end.getY()));
    }
}
