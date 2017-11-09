/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizen;
public class Matrix {
    private char[][] feld;
    public Matrix(char[][] f)
    {   
        feld = f;
    }
    public void Tausche(int i, int j)
    {
        if(i >= feld.length || i >= feld[0].length ||
           j >= feld.length || j >= feld[0].length ||
           i <= 0 || j <= 0)
        {
            System.out.println("Out of Index");
            return;
        }
        //Tauschen der Spalte
        //Tempörär speichern
        char[] tmp = feld[i];
        //Feld I überschreiben
        feld[i] = feld[j];
        //Feld J überschreiben
        feld[j] = tmp;
        
        tmp = new char[feld.length];
        //Tauschen der Zeile
        for (int x = 0; x < feld.length; x++) {
            //Tempörär speichern
            
            tmp[x] = feld[x][i];
            feld[x][i] = feld[x][j];
            feld[x][j] = tmp[x];
        }
    }
    public void Ausgabe()
    {
        for (int y = 0; y < feld[0].length; y++) {
            for (int x = 0; x < feld.length; x++) {
                System.out.print(feld[x][y] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
   
    }
}
