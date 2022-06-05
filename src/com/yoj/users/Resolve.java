package com.yoj.users;

import java.awt.*;

public class Resolve {

    public static void main(String[] args) {


        System.out.println("Years gone: " + nbYear(1500, 5, 100, 5000) + "...");
        System.out.println("Years gone: " + nbYear(1500000, 2.5, 0, 2000000) + "...");
        System.out.println("Years gone: " + nbYear(1500000, 0, 1000, 2000000) + "...");
        System.out.println("Years gone: " + nbYear(1,0.001D, 2,400000000) + "...");
    }

    public static int nbYear(int p0, double percent, int aug, int p) {
        // Checkers for parameters: all return -1 and message
        // Prevention of infinite cycle by border population lesser than initial population
        if (p <= p0) { System.out.println("Border lesser then initial population");
            return -1;}
        // Prevention of infinite cycle by negative parameter
        else if ( (p0 < 0) || (percent < 0) || (aug < 0) || (p < 0)) { System.out.println("Some value is negative");
            return -1;}
        // Prevention of infinite cycle by grow absence
        else if ( (percent == 0) && (aug == 0) ) { System.out.println("Grow is absolute zero (unreachable border)");
            return -1;}

        // initial values for population, year. Percent transition to absolute value
        int pop = p0,
            year = 0;
        double percentAbs = percent * 0.01;

        // While cycle with internal if operator
        while (pop < p) {
            pop = (int) Math.floor(pop + pop * percentAbs + aug);
            year++; // year never gets zero value
            //Year checker:
            //System.out.println("Year: " + year + ", Population: " + pop);
            if (pop >= p) return year;

            // checker for infinite cycle
            if (year == Integer.MAX_VALUE) {
                System.out.println("It takes myriad of years... "); return Integer.MAX_VALUE;}
        }

        // for case of something goes wrong
        System.out.println("went wrong");
        return 0;
        // your code
    }


}
