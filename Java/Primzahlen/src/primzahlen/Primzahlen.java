package primzahlen;
public class Primzahlen {
    static Primzahl erste = new Primzahl(2);
    public static void main(String[] args) {
        int checkRange = 100000;
        for (int i = 3; i < checkRange; i++) {
            addIfPrime(i); 
        }
        Ausgabe();
    }
    static public void Ausgabe()
    {
        Primzahl current = erste;
        int c = 1;
        while(current.next != null)
        {
            System.out.println(current.Zahl());
            current = current.next;
            c++;
        }
        System.out.println("Anzahl: " + c);
    }
    static public Boolean addIfPrime(int z)
    {
        Primzahl current = erste;
        while(Math.sqrt(z) >= current.Zahl() && current != null)
        {
            if(z % current.Zahl() == 0)
                return false;
            current = current.next;
        }
        erste.append(new Primzahl(z));
        return true;
    }
}
class Primzahl
{
    private int id;
    public Primzahl next = null;
    

    public Primzahl(int nummer)
    {
        this.id = nummer;
    }
    public void append(Primzahl p)
    {
        Primzahl current = this;
        while(current.next != null)
        {
            current = current.next;
        }
        current.next = p;
    }
    public int Zahl() {return id;}
}
