// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {

    private double dx;
    private double dy;
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    public double getDx(){
        return this.dx;
    }
    public double getDy(){
        return this.dy;
    }

    public Point applyToPoint(Point p){
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * (Math.cos(Math.toRadians(angle)));
        double dy = speed * (Math.sin(Math.toRadians(angle -180)));

        return new Velocity(dx, dy);
    }
}