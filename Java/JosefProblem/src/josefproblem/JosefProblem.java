package josefproblem;
public class JosefProblem {
    public static void main(String[] args) {
        // TODO code application logic here
        boolean arr[] = new boolean[41];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = true;
        }

        int b = 0;
        int count = arr.length;
        while (count > 1) {
            String output = "";
            for (int i = 0; i < arr.length; i++) {
                if (arr[i]) {
                    b++;
                    if (b % 3 == 0) {
                        arr[i] = false;
                        output += i + " stirbt | ";
                        count--;
                        //b = 0;
                    }
                }
            }
            System.out.println(output);
        }
        //Ausgabe wenn Schleife zu ende
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                System.out.println("Stelle: " + i + " überlebt");
                // break;
            }
        }
    }
}
