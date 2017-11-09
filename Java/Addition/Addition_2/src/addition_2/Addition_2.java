package addition_2;
import java.util.Scanner;
public class Addition_2 
{
 public static void main(String[] args) 
 {
  Scanner scan = new Scanner( System.in );
  int summand1=0, summand2=0, summe=0;
  System.out.println("Geben Sie nun den ersten Summanden ein. (Bestätigen sie mit Enter");
  summand1=scan.nextInt();
  System.out.println("Geben Sie nun den zweiten Summanden ein. (Bestätigen sie mit Enter");
  summand2=scan.nextInt();
  summe=summand1+summand2;
  System.out.print("Dieses Programm führt eine Addition zweier Zahlen aus: ");
  System.out.println(summand1+"+"+summand2+"="+summe);      
 }
}
