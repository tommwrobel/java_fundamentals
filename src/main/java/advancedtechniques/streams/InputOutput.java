package advancedtechniques.streams;

import java.io.*;
import java.util.Scanner;

public class InputOutput {
    public static void main(String[] args) throws IOException {
        FileInputStream input = new FileInputStream(".gitignore");


        printTimeTaken(()->{
            long size = 0;
            long lines = 0;


            Scanner sc = null;
            try {
                sc = new Scanner(new File("large.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                size += line.length();
            }

        });






        BufferedReader bf = new BufferedReader(new FileReader(new File("large.txt")));


        printTimeTaken(() -> {
            String line;
            long size2 = 0;
            try {
                while ((line = bf.readLine()) != null) {
                    size2 += line.length();
                }
            } catch (Exception ex) {
            }

        });

    }

    public static void printTimeTaken(Runnable runnable) {
        long start = System.currentTimeMillis();

        runnable.run();

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
    }
}
