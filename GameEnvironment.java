import java.util.*;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.awt.Color;

public class GameEnvironment {
    private final GUI gui;
    private final Ball ball;
    private final List<Collidable> collidables;
    private final int WIDTH;
    private final int HEIGHT;

    public GameEnvironment(GUI gui) {
        this.gui = gui;
        this.WIDTH = gui.getDrawSurface().getWidth();
        this.HEIGHT = gui.getDrawSurface().getHeight();
        collidables = new ArrayList<>();
        ball = new Ball(75, 150, 4, Color.BLUE);
    }

    public Line LineTrajectory(Ball ball, DrawSurface d) {
        double pointX = ball.getX() + ball.getVelocity().getDx() * d.getWidth();
        double pointY = ball.getY() + ball.getVelocity().getDy() * d.getWidth();
        return new Line(new Point(ball.getX(), ball.getY()), new Point(pointX, pointY));
    }

    public void startGame() {
        Sleeper sleeper = new Sleeper();
        ball.setVelocity(Velocity.fromAngleAndSpeed(70,  1));
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            render(d);
            update();
            CollisionInfo collisionInfo = getClosestCollision(LineTrajectory(ball, d));
            if (new Point(ball.getX(), ball.getY()).equalsHalf(collisionInfo.collisionPoint())){
                    ball.setVelocity(collisionInfo.collisionObject().hit(collisionInfo.collisionPoint(), ball.getVelocity()));
                }
            sleeper.sleepFor(5);  // wait for 50 milliseconds.
          //  System.out.println(collisionInfo.collisionPoint());

        }
    }

    private void render(DrawSurface d) {
        ball.drawOn(d);
        for(Collidable b : collidables) {
            if(b instanceof Block) {
                ((Block) b).draw(d);
            }

        }
        CollisionInfo collisionInfo = getClosestCollision(LineTrajectory(ball, d));
        if (collisionInfo.collisionPoint() != null){
            d.fillCircle((int)collisionInfo.collisionPoint().getX() ,(int)collisionInfo.collisionPoint().getY() , 2);
        }
        gui.show(d);
    }

    private void update() {
        ball.moveOneStep(WIDTH, HEIGHT);
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    public CollisionInfo getClosestCollision(Line trajectory) {
        TriTuple <Double, Collidable, Point> shortest = new TriTuple<>(Double.MAX_VALUE, null, null);

        for (Collidable c : collidables) {
            for (Line l : c.getCollisionRectangle().getLines()) {
                Point intersection = l.intersectionWith(trajectory);
                if (intersection != null) {
                    double length = new Line(trajectory.start(), intersection).length();

                    if(length < shortest.first())
                    {
                        shortest = new TriTuple<>(length, c, intersection);
                    }
                }
            }
        }

        return new CollisionInfo(shortest.third(), shortest.second());
    }
}