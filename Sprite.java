import java.util.*;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.awt.Color;
public interface Sprite {
    // draw the sprite to the screen
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed
    void timePassed();
}