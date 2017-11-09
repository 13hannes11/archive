package permutation;

public class Permutation {

    static String text = "abcd";

    private static void perm(String vor, int pos, String nach) {
        if (nach.length() == 1) {
            System.out.println(vor + nach);
            return;
        }
        String vor1 =  vor + nach.charAt(pos);
        String nach1 = nach.substring(0, pos) + nach.substring(pos +1);
        perm(vor1, 0, nach1);
        if(pos + 1 < nach.length())
        {
            perm(vor, pos + 1, nach);
        }
    }

    public static void main(String[] args) {
        perm("", 0, text);
    }
}
