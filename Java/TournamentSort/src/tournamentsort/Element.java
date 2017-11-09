package tournamentsort;

import java.util.Random;

public class Element {

    private int füllung;
    private Boolean gefüllt;
    private Element mum, dad;

    public Element() {
        gefüllt = false;
        mum = null;
        dad = null;
    }

    public Element(int f) {
        füllung = f;
        gefüllt = true;
    }

    public void addEbene() {
        if (mum != null) {
            mum.addEbene();
            dad.addEbene();
        } else {
            mum = new Element();
            dad = new Element();
        }
    }

    public int fillEbene(int anzahl) {
        if (mum != null) {
            anzahl = mum.fillEbene(anzahl);
            anzahl = dad.fillEbene(anzahl);
        } else {
            Random r = new Random();
            if (anzahl > 0) {
                mum = new Element(r.nextInt(100));
                anzahl--;
            }
            if (anzahl > 0) {
                dad = new Element(r.nextInt(100));
                anzahl--;
            }
        }
        return anzahl;
    }

    @Override
    public String toString() {
        String eleS = "\n Element: " + this.hashCode();
        String füllS = (gefüllt) ? " Wert: " + füllung : " ungefüllt";

        String mumS = (mum != null) ? mum.toString() : " mum: null";
        String dadS = (dad != null) ? dad.toString() : " dad: null";

        return eleS + füllS + mumS + dadS + " z";
    }
}
