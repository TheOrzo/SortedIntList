package test;

import java.io.*;
import java.util.Scanner;

public class ConsoleTest implements Runnable {

    File inFile;
    File cmpFile;
    File outFile;

    FileInputStream in;
    FileInputStream compare;
    FileOutputStream out;

    PrintStream defaultOut;
    InputStream defaultIn;

    public ConsoleTest(String in, String out) {
        try {
            inFile = new File(in);
            cmpFile = new File(out);
            outFile = new File(out + ".result");

            this.in = new FileInputStream(inFile);
            compare = new FileInputStream(cmpFile);
            this.out = new FileOutputStream(outFile, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        new Thread(this).start();
    }

    @Override
    public void run() {
        defaultOut = System.out;
        defaultIn = System.in;

        System.setOut(new PrintStream(out));
        System.setIn(in);
    }

    public boolean finish() {
        System.setOut(defaultOut);
        System.setIn(defaultIn);

        if (outFile.length() != cmpFile.length()) {
            return false;
        }

        try {
            Scanner out = new Scanner(this.outFile);
            Scanner cmp = new Scanner(this.cmpFile);

            while (out.hasNext() && cmp.hasNext()) {
                if (!(out.next().equals(cmp.next()))) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {

            }
        }

        return true;
    }
}
