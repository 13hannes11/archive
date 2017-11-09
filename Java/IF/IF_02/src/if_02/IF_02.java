package if_02;
import java.util.*;
public class IF_02 {
 public static void main(String[] args) 
 {
  Scanner scan = new Scanner( System.in);
  int betrag=0, tag=0, monat=0, tage=0, monate=0;
  double zinsen=0;
  System.out.println("Bitte geben Sie nun den Betrag ein, den Sie einzahlen möchten.");
  betrag=scan.nextInt();
  System.out.println("Bitte geben Sie nun zuerst den heutigen Tag und dann den momentanen Monat ein.");
  tag=scan.nextInt();
  monat=scan.nextInt();
  zinsen=betrag*((30-tag)+30*(12-monat))*1.03/(360*100);
  if(betrag>0 && betrag!=0)
  {
   System.out.println("Sie bekommen bis zum Jahresende "+zinsen+" Euro Zinsen.");
  }
  else if(betrag==0)
  {
   System.out.println("Sie machen weder neue Schulden, noch bekommen sie Zinsen");           
  }
  else
  {
   System.out.println("Sie machen "+zinsen+" Euro neue Schulden.");   
  }
 }
}
