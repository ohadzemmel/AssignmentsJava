import biuoop.DrawSurface;

public class Ball{
    private Point center;
    private int r;
    private  java.awt.Color color;
    private Velocity velocity;
    public Ball(double x, double y, int r, java.awt.Color color){
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    // accessors
    public double getX(){
        return center.getX();
    }
    public double getY(){
        return center.getY();
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface){
        surface.setColor(this.color);
        surface.fillCircle((int)this.getX(), (int)this.getY(),this.r);
    }
    public void setVelocity(Velocity v){
        this.velocity = v;
    }
    public void setVelocity(double dx, double dy){
        this.velocity = new Velocity(dx, dy);
    }
    public Velocity getVelocity(){
        return this.velocity;
    }

    public void moveOneStep( int borderX, int borderY) {
        if (r + (int)this.center.getX() >borderX || (int)this.center.getX() - r < 0 ){
            setVelocity(velocity.getDx() * ((double) -1), velocity.getDy());
        }
        if (r + (int)this.center.getY() > borderY || (int)this.center.getY() - r < 0 ){
            setVelocity(velocity.getDx(), velocity.getDy() *((double) -1));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
}