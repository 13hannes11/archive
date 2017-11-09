package kochkurve;

/*
 * Klasse, die einen Text zur Kochkurve berechnet
 * F steht für forwärts
 * l steht für links drehen
 * r steht für rechts drehen
 */

public class KKurve {
    private int maxLevel = 8;
    private int level;
    private String initiator = "F";
    private String generator ="FlFrrFlF";
    private String kkurve;
    
    public KKurve(int l) {
        level = Math.min(maxLevel, l);
        kkurve = generiereKurve(level,initiator); 
        // speichert den String, so muss er immer nur einmal berechnet werden
    }
    @Override public String toString() {return kkurve;}
    
    public void paint(java.awt.Graphics g) {
        double  länge = 800 / Math.pow(3,level),
                x = 10.0,
                y = 30.0,
                x1,
                y1,
                winkel = 0.0;
        for (char c: kkurve.toCharArray()) {
            // Durchlaufe alle Buchstaben des Strings
            switch (c) {
                case 'F':
                    x1 = x + länge * Math.cos(winkel);
                    y1 = y + länge * Math.sin(winkel);
                    g.drawLine((int)x, (int)y, (int)x1, (int)y1);
                    x = x1;
                    y = y1;
                    break;
                case 'l': 
                    winkel += Math.PI/3; // 60°
                    break;
                case 'r':
                    winkel -= Math.PI/3;
                    break;
            }
        }        
    }
    /*
     * generiereKurve liefert als Ergebnis einen String
     * @param level: gibt an, die wievielte Generation ausgegeben werden soll
     * @out: String, der den Verlauf der Kurve beschreibt
     */
    private String generiereKurve(int level, String kurve){
        if (level == 0) {
            return kurve.replaceAll(initiator, generator);
        } else {
            return generiereKurve(level - 1, kurve.replaceAll(initiator, generator));
        }      
    }
}
