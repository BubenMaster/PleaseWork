
package com.yoj.core.leve10.part2.javarushRnW;

import com.yoj.core.leve10.part2.readNWrite.Asset;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
Читаем и пишем в файл: JavaRush
*/


public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File dir = new File("G:\\JavaWorkspace\\Inellij Workspace\\Please Work\\src\\com\\yoj\\core\\leve10\\part2\\javarushRnW");
            File yourFile = File.createTempFile("temp_file", null, dir);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            User vasya = new User("Vasya", "Pupkin", new Date(1990,4,5), true, User.Country.OTHER);
            User john = new User("John", "Snow", new Date(1976,1, 20), false, User.Country.RUSSIA );
            javaRush.users.add(vasya);
            javaRush.users.add(john);
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println("Equals check: " + javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            System.out.println("Oops, something is wrong with my file");
            e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            Writer writer = new BufferedWriter( new OutputStreamWriter(outputStream));
            if (users == null || users.size() == 0) {
                writer.write("null\n");
                writer.flush();
            }
            else {
                writer.write(users.size() + "\n");
                writer.flush();
                users.forEach(user -> user.save(outputStream));
            }
            //implement this method - реализуйте этот метод
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
            String listSize = reader.readLine();
            if (!listSize.equals("null")) {
                int size = Integer.parseInt(listSize);
                while (size != 0) {
                    User user = new User();
                    user.load(reader);
                    users.add(user);
                    size--;
                }
            }
            //implement this method - реализуйте этот метод
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }


    }
}

