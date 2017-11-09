package fakultätrekursiv;
public class FakultätRekursiv {
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Fakultät (Rekursiv): " + fak(9));
        System.out.println("Fakultät (Iterativ): " + fakIt(9));
        System.out.println("Potenz (Rekursiv): " + pot(0.5 , 2));
        System.out.println("Potenz (Iterativ): " + potIt(0.5 , 2));
        System.out.println("Fibonacci (Rekursiv [ineffektiv]): " + fibonacci(25));
        System.out.println("ggT (Rekursiv): " + ggT(14,12));
        int[] e = bruchAddition(258, 36891, 30123, 40990);
        System.out.println("Brüche addieren: " + e[0] + "/" + e[1]);
    }
    //Fakultät
    public static long fak(int n) {
        if (n <= 1)
            return 1;
        else
            return n * fak(n-1);
    }
    public static long fakIt(int n)
    {
        long e = 1;
        for (int i = n; i > 0; i--) {
           e *= i;             
        }
        return e;
    }
    //Additon von Brüchen
    public static int[] bruchAddition(int z1, int n1, int z2, int n2)
    {
        int ggt = ggT(n1, n2);
        
        int e1 = n1 / ggt;
        int e2 = n2 / ggt;
        
        //Brücjhe erweitern
        n1 *= e2;
        z1 *= e2;
        
        n2 *= e1;
        z2 *= e1; 
        
        int[] r = new int[2];
        r[0] = z1 + z2;
        r[1] = n1;
        
        //Kürzen
        int s = ggT(r[0], r[1]);
        r[0] /= s;
        r[1] /= s;
        
        return r;
        
    }
    //Größter gemeinsamer Teiler
    public static int ggT(int p, int o)
    {
       if(p == o)
            return p;
       return ggT(Math.abs(p - o), //Dake abs() of p - o
                  (p < o)? p : o); //Take smaller one either o or p
    }
    //Potenzrechnung
    public static double pot(double a , int exponent)
    {
        if(exponent == 0)
            return 1;
        else
            return a * pot(a , exponent - 1);
    }
    public static double potIt(double a, int exponent)
    {
        double e = 1;
        for (int i = 1; i <= exponent; i++) {
            e *= a;
        }
        return e;
    }
    //Fibonacci number
    public static long fibonacci(int runs)
    {
        if(runs <= 2)
            return 1;
        else
            return fibonacci(runs - 1) + fibonacci(runs -2);
    }
}
