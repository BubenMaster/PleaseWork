package com.yoj.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SandyClass {
    public static void main(String[] args) {

        String firstStr = "first ";
        String secondStr = firstStr + "second ";
        firstStr.concat("third ");
        secondStr.concat(firstStr);
        firstStr += "fouth ";
        System.out.println(firstStr + " " + secondStr);
        System.out.println("GitGUD");

    }
}
