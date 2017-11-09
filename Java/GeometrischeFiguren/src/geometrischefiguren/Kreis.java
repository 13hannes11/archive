/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrischefiguren;

import java.awt.Graphics;

/**
 *
 * @author Hannes
 */
public class Kreis extends Punkt {

    int r;

    public Kreis(Punkt p, int radius) {
        super(p.x, p.y);
        r = radius;
    }

    @Override
    public void zeichne(Graphics g) {
        super.zeichne(g);
        g.drawOval(x - r, y - r, r*2, r*2);
        
    }
}
