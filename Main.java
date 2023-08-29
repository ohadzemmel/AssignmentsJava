import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;
public class Main {
    public static void main(String[] args) {

//        Line l1 = new Line(50, 80, 100, 160);
//        Line l2 = new Line(50, 80, 100, 160);
        AbstractArtDrawing.drawRandomLines(8 , 600);
       int[] myNum = {12, 2, 3, 4, 2, 9 , 8, 5};

        MultipleFramesBouncingBallsAnimation(myNum);
       MultipleBouncingBallsAnimation(myNum);
        Rectangle rectangle = new Rectangle(new Point(20 , 20) , 100 , 100);
        System.out.println((new Line(new Point(50 , 30), new Point(50 , -100))).closestIntersectionToStartOfLine(rectangle));
        Line[] lines =  rectangle.getLines();
        for (int i = 0; i < 4; i++) {
            System.out.println(lines[i]);
        }
    }

    public static void MultipleBouncingBallsAnimation(int[] sizes){
        GUI gui = new GUI("Multiple Bouncing Balls",200,200);
        Sleeper sleeper = new Sleeper();
        Random rand = new Random();
        Ball[] balls = new Ball[sizes.length];
        for (int i = 0; i < balls.length ; i++) {
            int s = 10/sizes[i];
            if (sizes[i] > 50){
                s = 1/5;
            }
            balls[i] =new Ball(rand.nextInt(140)+30 , rand.nextInt(140)+30, sizes[i], Color.black);
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), s);
            balls[i].setVelocity(v);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep(gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(500);  // wait for 50 milliseconds.
        }
    }
    static  public void MultipleFramesBouncingBallsAnimation(int[] sizes){
        Random rand = new Random();
        Sleeper sleeper = new Sleeper();
        GUI gui = new GUI("Multiple Frames Bouncing Balls1", rand.nextInt(450) +50, rand.nextInt(450) +50);
        GUI gui2 = new GUI("Multiple Frames Bouncing Balls2", rand.nextInt(150) +450, rand.nextInt(150) +450);
        Ball[] balls = new Ball[sizes.length];
        gui2.getDrawSurface().getHeight();

        for (int i = 0; i < balls.length; i++) {
           int s = sizes[i];
           if (sizes[i] > 50){
            s = 1/5;
        }
           balls[i] =new Ball(rand.nextInt(140)+30 , rand.nextInt(140)+30, sizes[i], Color.black);
           Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), s);
           balls[i].setVelocity(v);
       }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            DrawSurface a = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                if (i >= balls.length/2 ){
                    balls[i].moveOneStep(gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());
                    balls[i].drawOn(d);
                }else {
                    balls[i].moveOneStep(gui2.getDrawSurface().getWidth(), gui2.getDrawSurface().getHeight());
                    balls[i].drawOn(a);
                }
            }
            gui.show(d);
            gui2.show(a);
            sleeper.sleepFor(500);  // wait for 50 milliseconds.
        }

    }


}

