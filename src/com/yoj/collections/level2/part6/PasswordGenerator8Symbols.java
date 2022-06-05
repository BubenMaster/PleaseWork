package com.yoj.collections.level2.part6;

import com.sun.source.tree.WhileLoopTree;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class PasswordGenerator8Symbols {
    static char[] lowReg = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'},
            highReg = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'},
            numbers = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    static int passLength = 16;

    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println();
        System.out.println(password.toString());


    }

    public static ByteArrayOutputStream getPassword() {
        int[] passPos = new int[passLength];
        for (int i = 0; i < passLength; i++) {
            passPos[i] = i;
        }
        byte[] passMask = new byte[passLength];
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Random rng = new Random();

        System.out.println();
        int lRegCount = rng.nextInt(1,passLength - 2),
            hRegCount = rng.nextInt(1,passLength - 1 - lRegCount),
            numCount = passLength - lRegCount - hRegCount;

        //fill the mast massive containing correspond number of each type:
        // 0 - low registry, 1 - high registry, 2 - number
        Arrays.fill(passMask,0,lRegCount-1, (byte) 0);
        Arrays.fill(passMask,lRegCount,lRegCount + hRegCount, (byte) 1);
        Arrays.fill(passMask,lRegCount + hRegCount,lRegCount + hRegCount + numCount, (byte) 2);

        System.out.printf("low reg: %d, high reg: %d, numbers: %d ", lRegCount,hRegCount,numCount);
        System.out.println(Arrays.toString(passMask));
        System.out.println(Arrays.toString(passPos));

        for (int i = 0; i < passLength; i++) {
            int iRand;
            do iRand = passPos[rng.nextInt(passLength)];
            while (iRand==-1);
            passPos[iRand] = -1;
            System.out.print(iRand);

            char symbol = switch (passMask[iRand]){
                case (byte) 1 : yield highReg[rng.nextInt(highReg.length)];
                case (byte) 2 : yield numbers[rng.nextInt(numbers.length)];
                default : yield lowReg[rng.nextInt(lowReg.length)]; // case of (byte) 0
        };
            try {
                result.write(String.valueOf(symbol).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
