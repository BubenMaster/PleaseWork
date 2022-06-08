package com.yoj.collections.level2.part6.PasswordGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

public class PasswordGenerator{

    private enum SymbolicTypes {
        numeric,
        highCase,
        lowCase; }

    private final static char[] lowCaseSymbol = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private final static char[] highCaseSymbol = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final static char[] numericSymbol = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    private static final Random randomizer = new Random();
    private static SymbolicTypes[] symbolTypeMask;
    private static int[] symbolPossiblePositions;
    private static String password;

    public static ByteArrayOutputStream generatePassword(int lengthOfPassword) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        symbolPossiblePositions = createPositionsMask(lengthOfPassword);
        symbolTypeMask = createSymbolTypeMask(lengthOfPassword);
        //System.out.println(Arrays.toString(symbolTypeMask));
        //System.out.println(Arrays.toString(allPositions));
        password = assemblePassword(lengthOfPassword, symbolPossiblePositions, symbolTypeMask);
        writePasswordToResult(result, password);
        return result;
    }

    private static void writePasswordToResult(ByteArrayOutputStream result, String password) {
        try {
            result.write(password.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String assemblePassword(int lengthOfPassword, int[] symbolPossiblePositions, SymbolicTypes[] symbolTypeMask) {
        StringBuffer resultBuffer = new StringBuffer();
        for (int i = 0; i < lengthOfPassword; i++) {
            int iRand;
            do iRand = symbolPossiblePositions[randomizer.nextInt(lengthOfPassword)];
            while (iRand == -1);
            symbolPossiblePositions[iRand] = -1;
            //System.out.print(iRand+" ,");
            resultBuffer.append(generateNextRandomSymbol(symbolTypeMask[iRand]));
        }
        return resultBuffer.toString();
    }

    //Create array with random ranges of symbol types
    private static SymbolicTypes[] createSymbolTypeMask(int lengthOfMask) {
        SymbolicTypes[] result = new SymbolicTypes[lengthOfMask];
        int Amounts[] = RangeRandomSplitter.getInstance().split(lengthOfMask,3);
        int lowCaseRandomisedAmount = Amounts[0],
            highCaseRandomisedAmount = Amounts[1],
            numericRandomAmount = Amounts[2];
        // System.out.printf("low reg: %d, high reg: %d, numbers: %d ", lowCaseRandomisedAmount,highCaseRandomisedAmount,numericRandomAmount);
        Arrays.fill(result,0,lowCaseRandomisedAmount, SymbolicTypes.lowCase);
        Arrays.fill(result,lowCaseRandomisedAmount,lowCaseRandomisedAmount + highCaseRandomisedAmount, SymbolicTypes.highCase);
        Arrays.fill(result,lowCaseRandomisedAmount + highCaseRandomisedAmount,lowCaseRandomisedAmount + highCaseRandomisedAmount + numericRandomAmount, SymbolicTypes.numeric);
        return result;
    }

    //Array filled with its indexes. Serve as a source of residual index for a current symbol position
    private static int[] createPositionsMask(int lengthOfMask) {
        int[] result = new int[lengthOfMask];
        for (int i = 0; i < lengthOfMask; i++) {
            result[i] = i;
        }
        return result;
    }

    //Produce random symbol from
    private static String generateNextRandomSymbol(SymbolicTypes typeOfSymbol) {
        char symbol = ' ';
        if (null != typeOfSymbol){
            symbol = switch (typeOfSymbol) {
                case numeric:
                    yield randomChar(numericSymbol);
                case highCase:
                    yield randomChar(highCaseSymbol);
                case lowCase:
                    yield randomChar(lowCaseSymbol);
            };
        }
        return String.valueOf(symbol);
    }

    //Pulls random value from chosen symbol array
    private static char randomChar(char[] symbols) {
        return symbols[randomizer.nextInt(symbols.length)];
    }




}
