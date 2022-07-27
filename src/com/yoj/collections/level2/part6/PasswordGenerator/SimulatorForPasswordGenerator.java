package com.yoj.collections.level2.part6.PasswordGenerator;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Random;


public class SimulatorForPasswordGenerator {

    static int lengthOfPassword = 16;

    public static void main(String[] args) {
        ByteArrayOutputStream password = PasswordGenerator.generatePassword(lengthOfPassword);
        System.out.println();
        System.out.println(password.toString());
        Random randy = new Random();
        password = PasswordGenerator.generatePassword(lengthOfPassword);
        testForRelativeMassOfElements();
    }

    private static void show15SplitsWithMinimum1For(int range, int pieces){
        System.out.println("\n");
        for (int i = 0; i < 15; i++) {
            int[] simulatedResult = RangeRandomSplitter.getInstance().splitWithMinimalPiece(range, pieces, 1);
            System.out.printf("Range: %d, pieces: %d, Split result: %s%n", range, pieces, Arrays.toString(simulatedResult));
        }
    }

    private static void testForRelativeMassOfElements(){
        for (int i = 1; i < 6; i++) {
            showRelativeElementsDistributionAverageFrom100SimulationsFor(i*20,19);
        }
        for (int i = 1; i < 6; i++) {
            showRelativeElementsDistributionAverageFrom100SimulationsFor(30,i+1);
        }
    }

    private static void showRelativeElementsDistributionAverageFrom100SimulationsFor(int range, int pieces){
        int[] summaryOfSimulations = new int[pieces];
        for (int i = 0; i < 100; i++) {
            int[] singleSimulation = RangeRandomSplitter.getInstance().splitWithMinimalPiece(range, pieces, 1);
            appendArraySourceTo(summaryOfSimulations, singleSimulation);

        }
        normalizeAndMultiplyBy100ElementsOf(summaryOfSimulations);
        System.out.printf("Range: %d, pieces: %d, Split mass in %% of Range: %s%n", range, pieces, Arrays.toString(summaryOfSimulations));
    }

    private  static void appendArraySourceTo(int[] arrayMaster, int[] arraySource){
        int i = 0;
        for (int element: arraySource) {
            arrayMaster[i++] += element;
        }
    }

    private static void normalizeAndMultiplyBy100ElementsOf(int[] array){
        int summaryOfElements = Arrays.stream(array).sum();
        for (int i = 0; i < array.length; i++) {
            array[i] = (array[i] * 100) / summaryOfElements ;
        }
    }
}
