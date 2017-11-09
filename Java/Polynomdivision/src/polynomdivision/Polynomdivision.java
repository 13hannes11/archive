package polynomdivision;

public class Polynomdivision {

    public static void main(String[] args) {
        //arr[X][0 = Koeffizient || 1 = Exponent]
        int[][] arr = new int[10][2];
        int[][] dividend = new int[9][2];
        int[][] ergebnis = new int[10][2];
        int[] tmpTerm = new int[2];
        int rest = 0;
        
        arr[0][0] = 2;
        arr[0][1] = 2;
        arr[1][0] = 7;
        arr[1][1] = 3;
        arr[2][0] = 3;
        arr[2][1] = 0;

        dividend[0][0] = 1;
        dividend[0][1] = 1;
        dividend[1][0] = 3;
        dividend[1][1] = 0;
        
        tmpTerm = arr[0];
        int i = 0;
        while (true) {
            //ergebnis[0] = termDivision(arr[0], dividend[0]);
            //tmpTerm = termMultiplikation(ergebnis[0], dividend[1]);
            //tmpTerm = termSutraction(arr[1], tmpTerm);

            //ergebnis[1] = termDivision(tmpTerm, dividend[0]);
            //tmpTerm = termMultiplikation(ergebnis[1], dividend[1]);
            //tmpTerm = termSutraction(arr[2], tmpTerm);

            ergebnis[ergebnis.length - (i+1)] = termDivision(tmpTerm, dividend[i]);
            tmpTerm = termMultiplikation(ergebnis[ergebnis.length - (i+1)], dividend[i]);
            tmpTerm = termSutraction(arr[i+1], tmpTerm);
            if(tmpTerm[1] == 0)
            {
                rest = tmpTerm[0];
                break;
            }
            i++;
        }
        
        for (int k = 0; k < ergebnis.length; k++) {
            System.out.print(termToString(ergebnis[k]));
        }
        System.out.println("\nRest: " + rest);

    }
    static String termToString(int[] term)
    {
        String out = "";
        if(term[0] != 0)
            out += "(" + term[0] + "x^" + term[1] + ")";
        return out;
    }
    static int[] termDivision(int[] term1, int[] term2) {
        int[] ergebnis = new int[2];

        ergebnis[0] = term1[0] / term2[0];
        ergebnis[1] = term1[1] - term2[1];

        return ergebnis;
    }

    static int[] termMultiplikation(int[] term1, int[] term2) {
        int[] ergebnis = new int[2];

        ergebnis[0] = term1[0] * term2[0];
        ergebnis[1] = term1[1] + term2[1];

        return ergebnis;
    }

    static int[] termSutraction(int[] term1, int[] term2) {
        int[] ergebnis = new int[2];

        ergebnis[0] = term1[0] - term2[0];
        ergebnis[1] = term1[1];

        return ergebnis;
    }
}
