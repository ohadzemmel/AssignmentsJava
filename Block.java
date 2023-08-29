import biuoop.DrawSurface;

import java.awt.*;

public class Block implements Collidable {
    private Rectangle rectangle;

    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getCollisionRectangle() {
        return rectangle;
    }


    //todo
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Line[] lines = rectangle.getLines();
        Line hit_line = lines[0];
        Velocity velocity;

        for (Line line : lines) {
            if (line.isOnLine(collisionPoint)) {
                hit_line = line;
                break;
            }
        }

        if (MathUtil.doubleEqual(hit_line.m(), Double.POSITIVE_INFINITY))
        {
            velocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        else
        {
            velocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return velocity;
    }

    public void draw(DrawSurface d) {
        d.setColor(Color.black);
        d.drawRectangle(
                (int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(),
                (int) rectangle.getHeight()
        );
    }

}
