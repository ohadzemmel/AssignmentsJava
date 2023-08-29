import java.text.DecimalFormat;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other) {
        double dx = (this.x - other.x);
        double dy = (this.y - other.y);
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Point)) return false;
        Point p = (Point)o;
        return (MathUtil.doubleEqual(this.x , p.x) && MathUtil.doubleEqual(this.y , p.y));
    }

    public boolean equalsHalf(Object o) {
        if(!(o instanceof Point)) return false;
        Point p = (Point)o;
        return (MathUtil.doubleEqualHalf(this.x , p.x) && MathUtil.doubleEqualHalf(this.y , p.y));
    }


    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return String.format("Point(%s,%s)", df.format(x), df.format(y));
    }
}