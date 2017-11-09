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
public class Strecke extends Punkt {
    protected Punkt _ende;
    protected double länge;
    
    public Strecke(Punkt anfang, Punkt ende)
    {
        super(anfang.x, anfang.y); //Super ruft den Konstruktor der Elternklasse Punkt auf 
        //ererbte eigenschaft hat somit die Bedeutung des Punktes von dem aus gezeichnet wird
        this._ende = ende;
        setLänge();
    }
    public Strecke(int x, int y, Punkt ende)
    {
        super(x,y);
        this._ende = ende;
        setLänge();
    }
    @Override
    public void zeichne(Graphics g)
    {
        g.drawLine(x, y, _ende.x, _ende.y);
    }
    @Override
    public String toString()
    {
        return "Strecke(" + super.toString() + " bis " + _ende + "; länge( " + länge + " ))";
    }
    private void setLänge()
    {
        int deltaX = x - _ende.x;
        int deltaY = y - _ende.y;
        länge = Math.sqrt(deltaX*deltaX + deltaY*deltaY); // sqrt(a^2 + b^2)
    }
}
