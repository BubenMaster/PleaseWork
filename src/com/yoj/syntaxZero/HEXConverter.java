package com.yoj.syntaxZero;
import java.util.Arrays;
import java.util.regex.Pattern;
public class HEXConverter {

/*
Шестнадцатеричный конвертер
*/


    private static final String HEX = "0123456789abcdef";
    public static String[] hexArray = HEX.split("");


    public static void main(String[] args) {
        int decimalNumber = -8;
        System.out.println("Десятичное число " + decimalNumber + " равно шестнадцатеричному числу " + toHex(decimalNumber));
        String hexNumber = "4ef";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно десятичному числу " + toDecimal(hexNumber));
    }

    public static String toHex(int decimalNumber) {
        String hexNumber = "";
        if (decimalNumber > 0) {
            int i = 0;
            while (decimalNumber > 0) {
                hexNumber = hexArray[decimalNumber % 16] + hexNumber;
                decimalNumber = decimalNumber / 16;
                i++;
            }
            return hexNumber;
        }
        //напишите тут ваш код
        else return null;
    }

    public static int toDecimal(String hexNumber) {
        int decimalNumber = 0;
        if (hexNumber == null) return 0;
        if (hexNumber.equals("") ) return 0;
        String[] hexNumberArray = hexNumber.split("");
        hexNumberArray = reverseArray(hexNumberArray);

            for (int i = 0; i < hexNumberArray.length; i++) {
                int SixteenPowi = (int) Math.pow(16, i);
                decimalNumber = decimalNumber + oneHexFromArray(hexNumberArray[i],hexArray) * SixteenPowi;
            }
        return decimalNumber;

        //напишите тут ваш код

    }

    public static String[] reverseArray(String[] inArray) {
        String[] outArray = new String[inArray.length];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[inArray.length - i - 1];
        }
        return outArray;
    }

    public static int oneHexFromArray(String hexLetter, String[] hexArray) {
        for (int i = 0; i < hexArray.length; i++) {
            if (hexLetter.equals(hexArray[i]))
                return i;
        }
        return 0;
    }
}