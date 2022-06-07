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



    }



}
