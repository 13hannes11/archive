/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20x20;

/**
 *
 * @author kuchelmeister.hannes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for (int a = 1; a <= 20; a++)  
        {
            for (int b = 1; b <= 20; b++)
            {
                System.out.printf("%4d", a * b);
                
                if (b == 20) {
                    System.out.print("\n");
                }
            }
        }
    }
}
