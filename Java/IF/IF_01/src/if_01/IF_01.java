package if_01;
import java.util.*;
public class IF_01 {
 public static void main(String[] args) {
  Random generator = new Random();
  
  int zahl1 = generator.nextInt();
  double ergebnis = 0;
  if(zahl1 > 0)
  {
   ergebnis = Math.sqrt(zahl1);
   System.out.println("Wurzel aus " + zahl1 + " ergibt " + ergebnis);
  }
  else
  {
   System.out.println("Aus diser Zahl (" +zahl1+ ") kann keine Wurzel aus einer Zahl kleiner 0 ziehen.");
  }
 }
}
