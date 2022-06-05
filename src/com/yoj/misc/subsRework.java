package com.yoj.misc;

import java.io.*;
import java.nio.charset.Charset;

public class subsRework {

    public static void main(String[] args) {
        BufferedReader reader;
        BufferedWriter writer;
        String line;
        int counter = 0;

        {
            try {
                File subFile = new File("D:\\test\\subs\\subs.srt");
                File outPutSubs = new File("D:\\test\\subs\\subsOut.srt");
                writer = new BufferedWriter(new FileWriter(outPutSubs));
                reader = new BufferedReader(new FileReader(subFile));
                while (reader.ready()) {
                    line = reader.readLine();
                    writer.write(line + "\n");
                    if (line.equals("")) {
                        counter++;
                        writer.write(counter + "\n");
                    }
                    System.out.println(line);
                }
                writer.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
