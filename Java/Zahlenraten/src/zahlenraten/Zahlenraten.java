package zahlenraten;
import java.util.*;
public class Zahlenraten {
    public static void main(String[] args) {
     int geraten, zahl = 0, i = 0, maxdurchläufe = 3;
        Scanner scan = new Scanner(System.in);
     Random r = new Random();
     zahl = r.nextInt(10) + 1;     
     while(i < maxdurchläufe)
     {
         i++;
         System.out.println("Rate die Zahl (1-10):");
         geraten = scan.nextInt();
        if(zahl != geraten)
        {
            if(zahl < geraten)
            {
                System.out.println("Zu groß");
            }else
            {
                System.out.println("Zu klein");
            }
        }else
     {
         System.out.println("Richtig");
         i = 3000;
     }
     }
    }
}
