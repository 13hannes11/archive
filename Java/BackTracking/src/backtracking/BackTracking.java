package backtracking;

/**
 * @author Hannes
 */
public class BackTracking {
    private static int anzahl = 0;
    private static int[][] brett = new int[8][8];
    //Negative Zahlen = Dame
    //0 = unbesetzt
    // >0 => gesperrt

    private static void setze(int zeile, int spalte, int addiere) {
        brett[zeile][spalte] -= addiere;
        //sperre Zeile
        for (int i = 0; i < 8; i++) {
            if (i != spalte) {
                brett[zeile][i] += addiere;
            }
        }
        //spalte
        for (int i = 0; i < 8; i++) {
            if (i != zeile) {
                brett[i][spalte] += addiere;
            }
        }

        //sperre Diagonalen
        int z = zeile + 1;
        int s = spalte + 1;
        while (s < 8 && z < 8) {
            brett[z][s] += addiere;
            z++;
            s++;
        }
        z = zeile - 1;
        s = spalte - 1;
        while (s >= 0 && z >= 0) {
            brett[z][s] += addiere;
            z--;
            s--;
        }
        z = zeile + 1;
        s = spalte - 1;
        while (s >= 0 && z < 8) {
            brett[z][s] += addiere;
            z++;
            s--;
        }
        z = zeile - 1;
        s = spalte + 1;
        while (s < 8 && z >= 0) {
            brett[z][s] += addiere;
            z--;
            s++;
        }


    }

    private static void draw() {
        for (int z = 0; z < 8; z++) {
            for (int s = 0; s < 8; s++) {
                if (brett[z][s] == -1) {
                    System.out.print(" D");
                } else {
                    System.out.print(" " + brett[z][s]);
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
        anzahl++;
    }

    private static int nächsteStelle(int zeile, int spalte) {
        while (spalte < 8 && brett[zeile][spalte] != 0) {
            spalte++;
        }

        return spalte;
    }

    private static void dame(int zeile, int spalte) {
        int nSpalte = nächsteStelle(zeile, spalte);
        //System.out.println("z:" + zeile + " s:" + spalte + " n:" + nSpalte);
        if (zeile == 7 && nSpalte != 8) {
            setze(zeile, spalte, 1);
            draw();
            setze(zeile, spalte, -1);

        } else {
            if (nSpalte < 8) {
                setze(zeile, nSpalte, 1);
                dame(zeile +1, 0);
                setze(zeile,nSpalte, -1);
                dame(zeile, nSpalte +1);
            }
        }
    }

    public static void main(String[] args) {
       dame(0,0);
       System.out.println("Anzahl: " + anzahl);
    }
}
