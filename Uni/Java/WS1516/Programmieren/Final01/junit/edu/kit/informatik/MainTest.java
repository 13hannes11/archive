package edu.kit.informatik;

import org.junit.Test;

public class MainTest {

    @Test
    public void testMainWrongFile() {
        System.setSecurityManager(new NoExitSecurityManager());

        // File not existent
        try {
            final String[] args = new String[1];
            args[0] = "Z:\\";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }

        // duplicated Edge
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\duplicateEdge.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // edgeWithoutVertex
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\edgeWithoutVertex.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // emptyFile
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\emptyFile.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // negativeNumber
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\negativeNumber.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // noDivider
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\noDivider.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // noFirstPart
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\noFirstPart.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // noFirstPart
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\noSecondPart.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // notContinous
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\notContinous.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // numberOverflow
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\numberOverflow.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        // numberZero
        try {
            final String[] args = new String[1];
            args[0] = "C:\\Eclipse\\workspace\\Final01\\junit\\graphs\\numberZero.txt";
            // this should throw an exception
            Main.main(args);
            assert false;
        } catch (final ExitException e) {
            // if no exception is thrown something went wrong
        }
        System.setSecurityManager(null);
    }

}
