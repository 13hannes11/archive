package ha.test_1;
import java.util.Scanner;
public class HATest_1 
{
 public static void main(String[] args) 
 {
  Scanner scan = new Scanner( System.in );
  String name1="", name2="";
  int summand1=0, summand2=0, summe=0;
  System.out.println("Bitte geben Sie den ersten Namen ein. (Bestätigen Sie mit Enter.)");
  name1=scan.nextLine();
  System.out.println("Bitte geben Sie den zweiten Namen ein. (Bestätigen Sie mit Enter.)");
  name2=scan.nextLine();
  System.out.println("Bitte geben Sie nun den ersten Summanden ein. (Bestätigen Sie mit Enter.)");
  summand1=scan.nextInt();
  System.out.println("Bitte geben Sie nun den zweiten Summanden ein. (Bestätigen Sie mit Enter.)");
  summand2=scan.nextInt();
  summe=summand1+summand2;
  System.out.println("Hallo "+name1+",");
  System.out.println("dieses Programm addiert zwei beliebeige Zahlen,");
  System.out.println("z.B. "+summand1+"+"+summand2+"="+summe+".");
  System.out.println("Zeig das bitte mal "+name2+" zum überprüfen, ");
  System.out.println("ob die Summe von "+summand1+" und "+summand2);
  System.out.println("wirklich "+summe+" ergibt.");
 }
}
