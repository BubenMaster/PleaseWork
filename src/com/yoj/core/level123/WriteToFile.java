package com.yoj.core.level123;

import com.sun.source.tree.WhileLoopTree;

import java.io.*;

// D:\test\testTarget\testicle.txt

public class WriteToFile {

   public static void main(String[] args)  {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            String path = reader.readLine();

                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path)))) {
                    String marker = "";
                    while (!marker.equals("exit")) {
                        marker = reader.readLine();
                        out.println(marker);
                    }

                }
                catch (IOException ee) {
                    ee.printStackTrace();
                }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
   }
}
