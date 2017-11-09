package nst;
public class NST {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double p = 6, q  = -16;
        double d = Math.pow(p/2, 2) - q;
        if(d < 0)
        {
            System.out.println("Keine Nullstelle!");
        }
        else if(d == 0)
        {
            System.out.println("x1: " + (-p/2 ));
        }else
        {
            double e = (-p/2);
            System.out.println("x1: " + (e + Math.sqrt(d)));
            System.out.println("x2: " + (e - Math.sqrt(d)));
        }
        
    }
}
