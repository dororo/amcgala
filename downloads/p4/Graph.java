package org.amcgala.example.plotter;

import org.amcgala.framework.animation.Animation;
import org.amcgala.framework.renderer.Renderer;
import org.amcgala.framework.shape.AbstractShape;
import org.amcgala.framework.shape.Line;
import org.amcgala.framework.shape.shape2d.Text;

import java.awt.*;

/**
 * User: Steffen Tröster
 * Date: 15.10.12
 * Time: 11:30
 * Abstract Graph mit einer Abstrakt definierten Funktion.
 */
public abstract class Graph extends AbstractShape {

    private Line line1 = new Line(-390, -200, 390, -200);
    private Line line2 = new Line(-380, -210, -380, 210);
    private Text xText, yText;
    private final double maxX;
    private final double maxY;
    private final String stringX;
    private final String stringY;
    private double solution[];
    private int n = 0;

    @Override
    public void render(Renderer renderer) {
        renderer.setColor(Color.black);
        line1.render(renderer);
        line2.render(renderer);
        renderer.setColor(Color.red);
        for (int i = 0; i < (solution.length - 1); i++) {
            renderer.drawLine(i - ((int) maxX / 2) + 200, (int) solution[i], i + 1 - ((int) maxX / 2) + 200, (int) solution[i + 1]);
        }
        xText.render(renderer);
        yText.render(renderer);
        renderer.setColor(Color.black);
        renderer.drawCircle(-780, -200, (int) maxY * 2);
        renderer.setColor(Color.blue);
        int x = (int) (Math.sin(((double) n / (double) maxX) * Math.PI * 2) * maxY);
        int y = (int) (Math.cos(((double) n / (double) maxX) * Math.PI * 2) * maxY);
        renderer.drawLine(-180, 0, -180 + x, y);
        renderer.drawLine(-180 + x, y, -180, y);
        x = (int) (Math.sin(((double) n / (double) maxX) * Math.PI * 2 + Math.PI / 2) * maxY);
        y = (int) (Math.cos(((double) n / (double) maxX) * Math.PI * 2 + Math.PI / 2) * maxY);
        renderer.drawLine(-180, 0, -180 + x, y);
        renderer.drawLine(-180 + x, y, -180, y);
    }

    protected Graph(double maxX, double maxY, String stringX, String stringY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.stringX = stringX;
        this.stringY = stringY;
        xText = new Text(stringX, 300, -230);
        yText = new Text(stringY, -390, 200);
        solution = new double[(int) maxX];
        init();
        this.setAnimation(new Animation(this) {
            @Override
            public void update() {
                init();
            }
        });
    }

    protected void init() {
        for (int i = 0; i < (solution.length - 1); i++) {
            double range = ((double) i / (double) solution.length) * Math.PI * 2;
            solution[(i + n) % solution.length] = function(range) * maxY;

        }
        n = (n + 2) % solution.length;
    }

    public abstract double function(double x);

}
