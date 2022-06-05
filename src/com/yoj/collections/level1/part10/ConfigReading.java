package com.yoj.collections.level1.part10;

import org.xml.sax.XMLReader;

import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

/*
Читаем конфиги
*/

public class ConfigReading {
    public static void main(String[] args) {
        ConfigReading solution = new ConfigReading();
        Properties properties = solution.getProperties("G:/JavaWorkspace/Inellij Workspace/Please Work/src/com/yoj/collections/level1/part10/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("G:/JavaWorkspace/Inellij Workspace/Please Work/src/com/yoj/collections/level1/part10/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("G:/JavaWorkspace/Inellij Workspace/Please Work/src/com/yoj/collections/level1/part10/NotExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        Properties props = new Properties();
        File file = new File(fileName);
        if (!file.exists()) return props;

        if (fileName.endsWith(".xml")) {
            try {
                props.loadFromXML(new FileInputStream(file));
            } catch (IOException e) {
                System.out.println("Не прочел XML");
                e.printStackTrace();
                return props;
            }
            return props;
        }

        try {
            props.load(new FileReader(file));
        } catch (IOException e) {
            System.out.println("Не прочел txt");
            e.printStackTrace();
            return props;
        }
        return props;


    }
}
