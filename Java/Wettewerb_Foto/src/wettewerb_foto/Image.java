package wettewerb_foto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Image {

    private int width = 0, height = 0;
    private double abstand, streuung;

    //Streuung
    private void sigmaDist(double durchschnitt, ArrayList<Pixel> pMap) {
        double s = 0;
        for (int i = 0; i < pMap.size() - width; i++) {
            Pixel p = pMap.get(i);
            Pixel q = pMap.get(i + width);
            s = s + ((pixelDist(p, q) - durchschnitt) * 
                     (pixelDist(p, q) - durchschnitt));
        }
        streuung = Math.sqrt(s / (pMap.size() - width));
    }

    private double pixelDist(Pixel p, Pixel q) {
        double d = Math.sqrt((p.r - q.r) * (p.r - q.r)
                + (p.g - q.g) * (p.g - q.g)
                + (p.b - q.b) * (p.b - q.b));
        return d;
    }

    private void avgDist(ArrayList<Pixel> pMap) {
        double d = 0;
        for (int i = 0; i < pMap.size() - width; i++) {
            d = d + pixelDist(pMap.get(i), pMap.get(i + width));
        }
        abstand = d / (pMap.size() - width);
    }

    private void compute(ArrayList<Pixel> pMap) {
        int size = pMap.size();
        avgDist(pMap);
        sigmaDist(abstand, pMap);
        //System.out.println(getAbstand());
    }
    //Lädt Image und gibt Farbspektrum zurück (um speicherplatz zu sparen)

    public void Load(String path) {
        ArrayList<Pixel> pixelMap = new ArrayList();
        int i = 0;
        BufferedReader br = null;
        try {

            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new FileReader(path));

            if (readHeader(br)) {
                while (br.ready()) {
                    short r = (short) readInt(br);
                    short g = (short) readInt(br);
                    short b = (short) readInt(br);
                    if (r >= 0 && g >= 0 && b >= 0) {
                        pixelMap.add(new Pixel(i, r, g, b));
                        i++;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        compute(pixelMap);
        //return pixelMap;
    }

    private boolean readHeader(BufferedReader br) {
        try {
            char c1 = (char) br.read();
            char c2 = (char) br.read();
            if (c1 == 'P' && c2 == '3') {
                width = readInt(br);
                height = readInt(br);
                int maxVal = readInt(br);
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }
    private static char readNonwhiteChar(BufferedReader bf) throws IOException {
        char c;

        do {
            c = (char) bf.read();
        } while (c == ' ' || c == '\t' || c == '\n' || c == '\r');

        return c;
    }

    private int readInt(BufferedReader br) throws IOException {
        char c;
        int i;

        c = readNonwhiteChar(br);
        i = 0;
        do {
            i = i * 10 + c - '0';
            c = (char) br.read();
        } while (c >= '0' && c <= '9');
        return i;
    }
    /**
     * @return the abstand
     */
    public double getAbstand() {
        return abstand;
    }
    /**
     * @return the streuung
     */
    public double getStreuung() {
        return streuung;
    }
}
