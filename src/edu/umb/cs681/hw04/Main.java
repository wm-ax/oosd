package edu.umb.cs681.hw04;

import java.io.*;
import java.util.stream.Stream;
import java.lang.InterruptedException;

public class Main {

    private static Integer[] threadNums = {1, 2, 4, 8, 16};
    // private static long from = 1, to = 2000000;
    // //take off a 0 to speed things up
    private static long from = 1, to = 200000;
    public static void main(String[] args) {
        System.out.println("HW04...");

        timeJavaPrimes();

        try {
            timeRustPrimes();
        } catch (Exception e) {
            String apology = "I couldn't find Cargo to compile an included Rust program.  So instead, here is some canned output from the author's machine:";
            System.out.println(apology);
            dumpCanOfStats();
        }
    }

    private static void timeRustPrimes() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        String cmdString = "cargo run --manifest-path src/edu/umb/cs681/hw04/Cargo.toml";
            
        System.out.printf("\tRunning %s...\n", cmdString);
        Process pr = rt.exec(cmdString);

        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        String line = null;
        
        while ((line = input.readLine()) != null)
            {
                System.out.println(line);
            }
    }

    private static void timeJavaPrimes() {
        System.out.println("\tRunning prime generator in Java...");
        for (int numThreads : threadNums) {
            timeJavaPrimesForFixedThreadNum(numThreads);
        }
    }


    private static void timeJavaPrimesForFixedThreadNum(int numThreads) {
        long startTime = System.nanoTime();
        VariThreadedPrimeGenerator vtpg =
            new VariThreadedPrimeGenerator(from, to, numThreads);
        long endTime = System.nanoTime();
        long msDuration = (endTime - startTime) / 1000000;
        System.out.printf("\t\tJava implementation using %d threads took %d ms\n",
                          numThreads, msDuration);
    }


    private static void dumpCanOfStats() {
        String canOfStats = "src/edu/umb/cs681/hw04/stats.txt";
        try {
            FileReader fileReader = new FileReader(canOfStats);
            BufferedReader br = new BufferedReader(fileReader);
            br.lines().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("couldn't read the stats file:(");
        }
    }

}

