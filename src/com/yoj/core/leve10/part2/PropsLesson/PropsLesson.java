package com.yoj.core.leve10.part2.PropsLesson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
Знакомство с properties
*/

// Путь: G:\JavaWorkspace\Inellij Workspace\Please Work\src\com\yoj\core\leve10\part2\PropsLesson\props.properties

public class PropsLesson {

    private enum KeyValue {
        KEY,
        VALUE;
    }

    public static Map<String, String> runtimeStorage = new HashMap<>();


    public static void save(OutputStream outputStream) throws Exception {
        Properties props = new Properties();
        props.putAll(runtimeStorage);
        props.store(outputStream, "ha-ha");

        //напишите тут ваш код
    }

    public static void load(InputStream inputStream) throws IOException {
        Properties props = new Properties();
        props.load(inputStream);
        runtimeStorage = ConvertPropToStringMap(props);
        //напишите тут ваш код
    }

    private static HashMap<String, String> ConvertPropToStringMap(Properties prop) {
        HashMap<String, String> resulMap = new HashMap<>();
        for (String key: prop.stringPropertyNames()) {
            resulMap.put(key, prop.getProperty(key));
        }
        return resulMap;
    }

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader((System.in)));
        String initial = "";
        try {
            while ( !initial.equalsIgnoreCase("read") && !initial.equalsIgnoreCase("write")) {
                System.out.print("What to do (read/write?): ");
                initial = input.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Enter path: ");

        if ("read".equalsIgnoreCase(initial)) {
            try (
                 FileInputStream fos = new FileInputStream(input.readLine());
            ) {
                load(fos);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else  if ("write".equalsIgnoreCase(initial)) {
            try (
                 FileOutputStream fos = new FileOutputStream(input.readLine());
            ) {
                System.out.println("Enter Map fields (stpths - end word):");
                KeyValue keyValue = KeyValue.KEY;
                while (true) {
                    String word = "",
                            key = "",
                            value = "";

                    if (keyValue == KeyValue.KEY) {
                        System.out.print("Key: ");
                        word = input.readLine();
                        if (word.equals("stpths")) {
                            System.out.println("Map filling ended");
                            break;
                        }
                        key = word;
                        value = "";
                        keyValue = KeyValue.VALUE;
                    }
                    if (keyValue == KeyValue.VALUE) {
                        System.out.print("Value: ");
                        word = input.readLine();
                        if (word.equals("stpths")) {
                            runtimeStorage.put(key, value);
                            System.out.println("Map filling ended");
                            break;
                        }
                        value = word;
                        runtimeStorage.put(key, value);
                        keyValue = KeyValue.KEY;
                    }
                }
                save(fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(runtimeStorage);
    }
}
