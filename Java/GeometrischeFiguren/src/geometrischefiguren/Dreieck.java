package geometrischefiguren;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class Dreieck extends Strecke {

    protected Strecke _b, _a;

    public Dreieck(Punkt a, Punkt b, Punkt c) {
        super(a, b);
        this._a = new Strecke(b, c);
        this._b = new Strecke(c, a);
    }

    @Override
    public void zeichne(Graphics g) {
//        super.zeichne(g);
//        _a.zeichne(g);
//        _b.zeichne(g);
        // draw GeneralPath (polygon)
        
        int x1Points[] = {this.x, this._a.x, this._b.x};
        int y1Points[] = {this.y, this._a.y, this._b.y};
        GeneralPath polygon =
                new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                x1Points.length);
        polygon.moveTo(x1Points[0], y1Points[0]);

        for (int index = 1; index < x1Points.length; index++) {
            polygon.lineTo(x1Points[index], y1Points[index]);
        };

        polygon.closePath();
        ((Graphics2D)g).draw(polygon);
        //((Graphics2D)g).fill(polygon);
        
    }
}
