package verzeichnisgröße;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Hannes
 */
public class Verzeichnisgröße {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(fileSize(new File("."), 1));
    }

    private static long fileSize(File f, int ebene) {
        long size = 0;
        String leer = "";
        for(int i = 0; i < ebene; i++)
        {
            if(i + 1 != ebene)
                leer = leer + "\u2502  ";
            else
                leer = leer + "\u2514\u2500";
        }
        
        File[] fList = f.listFiles();
        for (File file : fList) {
            if (file.isDirectory()) {
                size += fileSize(file, ebene + 1);
               System.out.println(leer + file.getName());
            } else if(file.isFile()) {
                size += file.length();
                System.out.println(leer + file.getName());
            }

        }
        return size;
    }
}
