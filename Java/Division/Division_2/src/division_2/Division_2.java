package division_2;
import java.util.Scanner;
public class Division_2 
{
 public static void main(String[] args) 
 {
  Scanner scan = new Scanner( System.in );
  int divisor=0, divident=0, quotient=0;
  System.out.println("Geben Sie nun den Divisor ein. (Bestätigen sie mit Enter");
  divisor=scan.nextInt();
  System.out.println("Geben Sie nun den Divident ein. (Bestätigen sie mit Enter");
  divident=scan.nextInt();
  quotient=divisor/divident;       
  System.out.print("Diese programm führt eine Division zweier Zahlen aus: ");   
  System.out.println(divisor+"/"+divident+"="+quotient);   
 }
}
