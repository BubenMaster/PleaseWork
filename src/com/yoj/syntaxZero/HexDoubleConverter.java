package com.yoj.syntaxZero;

public class HexDoubleConverter {

    private static final String HEX = "0123456789abcdef";
    public static String[] hexArray = HEX.split("");

    public static void main(String[] args) {
        String binaryNumber = "100111010000";
        System.out.println("Двоичное число " + binaryNumber + " равно шестнадцатеричному числу " + toHex(binaryNumber));
        String hexNumber = "9d0";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        String hexNumber = "";
        if (binaryNumber == null || binaryNumber.equals("")) return "err1"; //проверка на пустую строку или null
        if (checkBinary(binaryNumber) == false) return "err2"; // проверка на наличие других символов
                //проверка на кратность 4-м, добавление нулей
        if ( !(binaryNumber.length() % 4 == 0) ) {
            int binaryNumberLength = binaryNumber.length();
            for (int i = 0; i < (4 - binaryNumberLength % 4); i++) {
                binaryNumber = "0" + binaryNumber;
            }
        }
        String[] binHexArray = createBinHexArray();//Создаем вспомогательный массив на 16 элементов в двоичной системе
                    // Основная операция преобразования, без разбития строки в массив по четыре символа. Стоит упростить.
            for (int i = 0; i < (binaryNumber.length() / 4); i++) {
                hexNumber ="" + hexArray[SearchElementInArray(binaryNumber.substring(binaryNumber.length() - ((i + 1 ) * 4),binaryNumber.length() - (i * 4)), binHexArray)] + hexNumber;
            }
        return hexNumber;

        //напишите тут ваш код

    }

    public static String toBinary(String hexNumber) {
        String binaryNumber = "";
        if (hexNumber == null || hexNumber.equals("")) return "err1"; //проверка на пустую строку или null
        if (checkHex(hexNumber) == false) return "err2"; // проверка на наличие других символов
        String[] binHexArray = createBinHexArray();//Создаем вспомогательный массив на 16 элементов в двоичной системе
        String[] hexNumArray = hexNumber.split(""); //Разбиваем число-строку на массив, с которым будем работать
        hexNumArray = reverseArray(hexNumArray); //меняем порядок элементов массива для укорочения последующей записи
                        // Основная операция преобразования
        for (int i = 0; i < hexNumArray.length; i++) {
            binaryNumber = "" + binHexArray[SearchElementInArray(hexNumArray[i],hexArray)] + binaryNumber;
        }
        return binaryNumber;
    }

    //обратный порядок элементов
    public static String[] reverseArray(String[] inArray) {
        String[] outArray = new String[inArray.length];
        for (int i = 0; i < inArray.length; i++) {
            outArray[i] = inArray[inArray.length - i - 1];
        }
        return outArray;
    }

    //проверка массива на соответствие двойичной системе
    public static boolean checkBinary(String numberForCheck){
        String[] splitNumber = numberForCheck.split("");
        for (int i = 0; i < splitNumber.length; i++) {
            if (!splitNumber[i].equals("1") && !splitNumber[i].equals("0")) return false;
        }
        return true;
    }

    //проверка массива на соответствие шестнадцатеричной системе
    public static boolean checkHex(String numberForCheck){
        String[] splitNumber = numberForCheck.split("");
        for (int i = 0; i < splitNumber.length; i++) {
            for (int j = 0; j < hexArray.length; j++) {
                if (splitNumber[i].equals(hexArray[j])) break;
                else if (j == hexArray.length - 1) return false;
            }
            }

        return true;
    }

    //Search String element in array of strings
    public static int SearchElementInArray(String Element, String[] Array) {
        for (int i = 0; i < Array.length; i++) {
            if (Element.equals(Array[i]))
                return i;
        }
        return 0;
    }

    // Creating Binary Array with toBinary transition
    public static String[] createBinHexArray(){
        String[] arrayBinHex = new String[16];
        arrayBinHex[0] = "0000";
        for (int i = 1; i < arrayBinHex.length; i++) {
            arrayBinHex[i] = decToBinary(i);

            if ( !(arrayBinHex[i].length() % 4 == 0) ) {
                int binHexLength = arrayBinHex[i].length();
                for (int j = 0; j < (4 - binHexLength % 4); j++) {
                    arrayBinHex[i] = "0" + arrayBinHex[i];
                }
            }
        }
        return arrayBinHex;
    }

    //Decimal to binary
    public static String decToBinary(int decimalNumber) {
        String binaryNumber = "";
        if (decimalNumber > 0) {
            int i = 0;
            while (decimalNumber > 0) {
                binaryNumber = (decimalNumber % 2) + binaryNumber + "";
                decimalNumber = decimalNumber / 2;
                i++;
            }
            return  binaryNumber;
        }

        else
            return "";
    }
}
