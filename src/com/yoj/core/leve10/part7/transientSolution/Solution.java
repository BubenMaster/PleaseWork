package com.yoj.core.leve10.part7.transientSolution;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/*
Serializable Solution
*/

public class Solution  implements Serializable{
    public static void main(String[] args) {
        System.out.println(new Solution(4));

        Solution solution = new Solution(37);
        Solution clone = null;
        try {
            File tmp = File.createTempFile("tmp", null, new File("G:\\JavaWorkspace\\Inellij Workspace\\Please Work\\src\\com\\yoj\\core\\leve10\\part7\\transientSolution"));
            ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(tmp));
            ObjectInputStream objIS = new ObjectInputStream(new FileInputStream(tmp));

            objOS.writeObject(solution);

            Object subject = objIS.readObject();
            clone = (Solution) subject;
            System.out.println(solution.equals(clone));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    public String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return string.equals(solution.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }


}
