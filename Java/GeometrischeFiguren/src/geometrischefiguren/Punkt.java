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
public class Punkt {
    protected int x, y; //Koordinaten in der Zeichnung
    
    public Punkt(int posX, int posY)
    {
        this.x = posX;
        this.y = posY;
    }
    
    public void zeichne(Graphics g)
    {
        g.drawRect(x, y, 1, 1);
    }
    
    @Override
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
    
}
