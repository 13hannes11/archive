package subtraktion_2;
import java.util.Scanner;
public class Subtraktion_2 
{
 public static void main(String[] args) 
 {
  Scanner scan = new Scanner( System.in );
  int minuend=0, subtrahend=0, differenz=0;
  System.out.println("Geben Sie nun den Minuend ein. (Bestätigen sie mit Enter");
  minuend=scan.nextInt();
  System.out.println("Geben Sie nun den Subtrahend ein. (Bestätigen sie mit Enter");
  subtrahend=scan.nextInt();
  differenz=minuend-subtrahend;
  System.out.print("Diese programm führt eine Subtraktion zweier Zahlen aus: ");   
  System.out.println(minuend+"-"+subtrahend+"="+differenz );    
 }
}
