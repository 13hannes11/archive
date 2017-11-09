package bubblesort;
public class Bubblesort {
    static double[] arr = new double[400000];
    static int start = 0, ende = arr.length - 1;
    
    public static void main(String[] args) {
        // TODO code application logic here
        fillArray();
        
        while(start != ende)
        {
            int aktuelles = start;
            int bestes = start;
            
            do {
                aktuelles++;
                if(bestes < aktuelles)
                {
                    bestes = aktuelles;
                }
            } while (aktuelles != ende);
            tausche(bestes, start);

         }
        //ausgabe();
        
        
    }
    public static void ausgabe()
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void tausche(int zeiger1, int zeiger2)
    {
        double tmp = arr[zeiger1];
        arr[zeiger1] = arr[zeiger2];
        arr[zeiger2] = tmp;
        start++;
    }
    public static void fillArray()
    {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random();
        }
    }
}
