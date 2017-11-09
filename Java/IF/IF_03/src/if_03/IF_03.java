package if_03;
public class IF_03 {
    public static void main(String[] args) {
        double zahl1 = 2;
        double zahl2 = 3;
        double zahl3 = 4;
        if(Math.pow(zahl1, 2) >= zahl3 && Math.pow(zahl2, 2) >= zahl3)
        {
            System.out.println("blue");
        }
        else
        {
            System.out.println("rot");
        }
    }
}
