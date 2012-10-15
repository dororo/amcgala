package org.amcgala.example.praktikum.drei;

import org.amcgala.framework.renderer.Renderer;
import org.amcgala.framework.shape.AbstractShape;
import org.amcgala.framework.shape.Line;
import org.amcgala.framework.shape.shape2d.Text;

/**
 * Abstract Graph mit einer Abstrakt definierten Funktion.
 * TODO: Zeichnen des Graphens in passenden Maßstab
 */
public abstract class Graph extends AbstractShape {

    private Line line1 = new Line(-390, -200, 390, -200);
    private Line line2 = new Line(-380, -210, -380, 210);
    private Text text = new Text("Textbeispiel", 0, 0);

    @Override
    public void render(Renderer renderer) {
        line1.render(renderer);
        line2.render(renderer);
        text.render(renderer);
    }

    /**
     * Abstrakte mathematische Funktion
     *
     * @param x Berechnungswert
     * @return Ergebnis
     */
    public abstract double function(double x);
}
