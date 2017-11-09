package tournamentsort;
public class TournamentSort {
    private static Element champ = new Element();
    private static int anzahl = 10;
    
    public static void main(String[] args) {       
        baueBaum();      
        System.out.println(champ);
    }
    private static void baueBaum()
    {
        int anz = 1;
        while(2 * anz < anzahl)
        {
            //fügt eine komplette ebene an
            champ.addEbene();
            anz *= 2;
            System.out.println(anz);
        }
        champ.fillEbene(anzahl);
    }
}

