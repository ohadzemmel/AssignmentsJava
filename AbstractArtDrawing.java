import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;

public class AbstractArtDrawing{

    public static void drawLine(Line l, DrawSurface d){
        int x1 = (int) l.start().getX();
        int y1 = (int) l.start().getY();
        int x2 = (int) l.end().getX();
        int y2 = (int) l.end().getY();
        d.setColor(Color.black);
        d.drawLine(x1,y1,x2,y2);
    }
    private static void drawIntersectingPoints(Line a, Line b , DrawSurface d){
        if (a.isIntersecting(b)) {
            Point intersectingP = a.intersectionWith(b);
            int x = (int) intersectingP.getX();
            int y = (int) intersectingP.getY();
            d.setColor(Color.red);
            d.fillCircle(x, y, 2);
        }
    }
    private static void drawMidPoints(Line line , DrawSurface d){
        int x = (int)line.middle().getX();
        int y = (int)line.middle().getY();
        d.setColor(Color.blue);
        d.fillCircle(x ,y , 2);
    }
    public static void drawRandomLines(int numOfLines, int size){
        GUI gui = new GUI("Random lines Example", size, size);
        Line[] lines = new Line[numOfLines];
        DrawSurface d = gui.getDrawSurface();
        for (int i = 0; i < numOfLines; i++) {
            lines[i] = Line.generateRandomLine(size);
            drawLine(lines[i], d);
            drawMidPoints(lines[i] , d);
        }
        for (int i = 0; i < numOfLines; i++) {
            for (int j = 0 ; j < numOfLines ; j++) {
                drawIntersectingPoints(lines[i] , lines[j] , d);
            }
        }
        gui.show(d);
    }
}
