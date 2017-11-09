package edu.kit.informatik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Helper class for reading text files.
 * 
 * @author IPD Reussner, KIT
 * @author ITI Sinz, KIT
 * @version 1.1
 */
public final class FileInputHelper {

    /**
     * Private constructor to avoid instantiation.
     */
    private FileInputHelper() {
        // intentionally left blank
    }

    /**
     * Reads the specified file and returns its content as a String array, where
     * the first array field contains the file's first line, the second field
     * contains the second line, and so on.
     * 
     * @param file
     *            the file to be read
     * @return the content of the file
     */
    public static String read(final String file) {
        final StringBuilder result = new StringBuilder();

        FileReader in = null;
        try {
            in = new FileReader(file);
        } catch (final FileNotFoundException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        }

        final BufferedReader reader = new BufferedReader(in);
        try {
            String line = reader.readLine();
            while (line != null) {
                result.append(line);
                line = reader.readLine();
                if (line != null) {
                    result.append("\n");
                }
            }
        } catch (final IOException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        } finally {
            try {
                reader.close();
            } catch (final IOException e) {
                // no need for handling this exception
            }
        }

        return result.toString();
    }

}