package multiplikation_2;
import java.util.Scanner;
public class Multiplikation_2
{
 public static void main(String[] args)
 {
  Scanner scan = new Scanner( System.in );
  int faktor1=0, faktor2=0, produkt=0;
  System.out.println("Geben Sie nun den ersten Faktor ein. (Bestätigen sie mit Enter");
  faktor1=scan.nextInt();
  System.out.println("Geben Sie nun den zweiten Faktor ein. (Bestätigen sie mit Enter");
  faktor2=scan.nextInt();
  produkt=faktor1*faktor2;
  System.out.print("Diese programm führt eine Multiplikation zweier Zahlen aus: ");   
  System.out.println(faktor1+"*"+faktor2+"="+produkt);      
 }
}
