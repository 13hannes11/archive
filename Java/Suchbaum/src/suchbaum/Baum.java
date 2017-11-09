/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package suchbaum;

/**
 *
 * @author frank.baethge
 */
public class Baum {

    private Knoten wurzel;

    public Baum() {
        wurzel = null;
    }

    public void einfügen(Knoten k) {
        if (wurzel == null) {
            wurzel = k;
        } else {
            wurzel.einfügen(k);
        }
    }

    /**
     * Sucht nach dem String toFind in den Werten des Baumes und gibt den
     * jeweiligen Knoten zurück wird toFind nicht gefunden wird null
     * zurückgegeben
     *
     * @param toFind
     */
    public Knoten finde(String toFind) {
        return wurzel.finde(toFind);
    }

    /**
     * beim Rechtsrotieren wird der linke Verweis zur Wurzel
     *
     * @param k
     *
     * zur Erklärung die Struktur ist vorher k == 1, k.links == 2 und
     * k.links.rechts == 3 nachher soll der Baum 2, 2.rechts == 1 und
     * 2.rechts.links = 3 sein
     *
     */
    public void rotiereRechts(Knoten k1) {
        if (k1.getLinks() == null) {
            return;
        }
        if (k1 == wurzel) {
            wurzel = wurzel.getLinks();
        }
        Knoten k2 = k1.getLinks();
        Knoten k3 = k2.getRechts();
        k2.setRechts(k1);
        k2.getRechts().setLinks(k3);
        if (k3 != null) {
            k3.setZurück(k1);
        }
        k2.setZurück(k1.getZurück());
        k1.setZurück(k2);
        if (k2.getZurück().getRechts() == k1) {
            k2.getZurück().setRechts(k2);
        } else {
            k2.getZurück().setLinks(k2);
        }
    }

    public void rotiereLinks(Knoten k1) {
        if (k1.getRechts() == null) {
            return;
        }
        if (k1 == wurzel) {
            wurzel = wurzel.getRechts();
        }
        Knoten k2 = k1.getRechts();
        Knoten k3 = k2.getLinks();
        k2.setLinks(k1);
        k2.getLinks().setRechts(k3);
        if (k3 != null) {
            k3.setZurück(k1);
        }
        k2.setZurück(k1.getZurück());
        k1.setZurück(k2);
        if (k2.getZurück() != null) {
            if (k2.getZurück().getRechts() == k1) {
                k2.getZurück().setRechts(k2);
            } else {
                k2.getZurück().setLinks(k2);
            }
        }
    }

    @Override
    public String toString() {
        if (wurzel == null) {
            return "der Baum ist leer";
        } else {
            return wurzel.inorder();
        }
    }

    protected String getGraphviz() {
        return "digraph g {\n"
                + " graph [\n"
                + "  rankdir = \"TB\"\n"
                + "  bgcolor = \"white:lightblue\"\n"
                + "  style=\"filled\"\n"
                + "  gradientangle = 270\n"
                + " ];\n"
                + " node [shape=box,style=filled,color=\"lightgray\"];\n"
                + wurzel.getGraphviz()
                + "\n}";
    }
}
