/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizen;

import java.util.Scanner;

/**
 *
 * @author Hannes
 */
public class Matrizen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        // TODO code application logic here
        Matrix ma = new Matrix(
                        new char[][]{
                            {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'},
                            {'a', 'x', '1', '1', '1', '0', '1', '0', '1', '1', '0'},
                            {'b', '1', 'x', '1', '0', '0', '0', '0', '0', '0', '0'},
                            {'c', '1', '1', 'x', '1', '1', '0', '1', '0', '0', '0'},
                            {'d', '1', '0', '1', 'x', '0', '0', '0', '0', '0', '0'},
                            {'e', '0', '0', '1', '0', 'x', '0', '0', '0', '0', '1'},
                            {'f', '1', '0', '0', '0', '0', 'x', '0', '0', '0', '0'},
                            {'g', '0', '0', '1', '0', '0', '0', 'x', '0', '0', '0'},
                            {'h', '1', '0', '0', '0', '0', '0', '0', 'x', '0', '0'},
                            {'i', '1', '0', '0', '0', '0', '0', '0', '0', 'x', '0'},
                            {'j', '0', '0', '0', '0', '1', '0', '0', '0', '0', 'x'}
                        });
        
        
        while(true)
        {
            ma.Ausgabe();
            //Eingabe 
            System.out.print("Tausche: ");
            int i = scan.nextInt();
            System.out.print("mit: ");
            int j = scan.nextInt();
            ma.Tausche(i, j);
        }
    }
}
