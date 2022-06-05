package com.yoj.core.level6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class L6Solution {
    public static String firstFileName; //= "D:\\test\\testTarget\\FirstFile.txt";
    public static String secondFileName; //= "D:\\test\\testTarget\\SecondFile.txt";

    static {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));

        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {
            System.out.println("IOException !");;
        }
    }
    //напишите тут ваш код

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        //private Path filePath;
        private File file;
        private volatile String fileContent = "";

        @Override
        public void setFileName(String fullFileName) {
            this.file = new File(fullFileName);
            //this.filePath = Path.of(fullFileName);
            //if (Files.exists(this.filePath)) System.out.println("exist checked");
            //else System.out.println("exist failed");
        }

        @Override
        public String getFileContent() {
            return this.fileContent;
        }

        @Override
        public void run() {
            try {
                FileReader fReader = new FileReader(this.file);
                BufferedReader buffer = new BufferedReader(fReader);
                while (buffer.ready()) {
                    //System.out.printf(".");
                    if (this.fileContent.equals("")) {
                        this.fileContent += buffer.readLine();
                    }
                     else {
                        this.fileContent += " " + buffer.readLine();
                    }
                }
                buffer.close();
                fReader.close();
                //System.out.println("");
            } catch (FileNotFoundException e) {
                System.out.println("File not exist");;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //напишите тут ваш код
}
