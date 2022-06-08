package com.yoj.collections.level2.part6.PasswordGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;


public class SimulatorForPasswordGenerator {

    static int lengthOfPassword = 16;

    public static void main(String[] args) {
        ByteArrayOutputStream password = PasswordGenerator.generatePassword(lengthOfPassword);
        System.out.println();
        System.out.println(password.toString());

        Random randy = new Random();
        for (int i = 20; i < 40; i++) {
            password = PasswordGenerator.generatePassword(lengthOfPassword);
          //  System.out.println();
          // System.out.println(password.toString());
            int simulatedRange = i,  //randy.nextInt(i, i+20),
                  //  simulatedPieces = randy.nextInt(1,simulatedRange / 3);
                simulatedPieces = 5;
            int[] simulatedResult = RangeRandomSplitter.getInstance().splitWithMinimalPiece(simulatedRange, simulatedPieces, 1);
            System.out.printf(" %B ", (Arrays.stream(simulatedResult).sum() == simulatedRange));
            System.out.printf("Range: %d, pieces: %d, Split result: %s%n",simulatedRange, simulatedPieces, Arrays.toString(simulatedResult));
        }


    }



}
