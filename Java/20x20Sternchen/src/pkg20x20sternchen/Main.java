/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20x20sternchen;

/**
 *
 * @author kuchelmeister.hannes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int x = 1; x <= 20; x++)
        {
            String s = "";
            for(int y = 0; y < x; y++)
            {
                s += "*";
            }
            System.out.printf("%20s",s);
            System.out.print("\n");
        }
        
        System.out.print("\n\n");
        
        for(int x = 20; x > 0; x--)
        {
            String s = "";
            for(int y = 0; y < x; y++)
            {
                s += "*";
            }
            System.out.printf("%20s",s);
            System.out.print("\n");
        }
        
        System.out.print("\n\n");
        
        for(int x = 20; x > 0; x--)
        {
            for(int y = 0; y < x; y++)
            {
                System.out.print("*");
            }
            System.out.print("\n");
        }
        
        System.out.print("\n\n");
        
        for(int x = 1; x <=20; x++)
        {
            for(int y = 0; y < x; y++)
            {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}
