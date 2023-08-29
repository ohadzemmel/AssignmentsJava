public class CollisionInfo {

    private Point p;
    private Collidable col;

    public CollisionInfo(Point p, Collidable col) {
        this.p = p;
        this.col = col;
    }

    // the point at which the collision occurs.
    public Point collisionPoint() {return p;};

    // the collidable object involved in the collision.
    public Collidable collisionObject() { return col;};
}