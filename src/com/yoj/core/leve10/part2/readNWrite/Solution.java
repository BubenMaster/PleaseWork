package com.yoj.core.leve10.part2.readNWrite;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Читаем и пишем в файл: Human
*/

public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File dir = new File("G:/JavaWorkspace/Inellij Workspace/Please Work/src/com/yoj/core/leve10/part2/readNWrite");
            File your_file_name = File.createTempFile("your_file_name", null, dir);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            //Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            Human ivanov = new Human();
            ivanov.name = "Ivanov";
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            System.out.println("Equals check: " + somePerson.equals(ivanov));

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {

            System.out.println("Oops, something wrong with save/load method");
            e.printStackTrace();
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        public void save(OutputStream outputStream) throws Exception {
            Writer writer = new BufferedWriter( new OutputStreamWriter(outputStream));
            writer.write(name + "\n");
            writer.flush();
            if (assets == null || assets.size() == 0) {
                writer.write("null\n");
                writer.flush();
            }
            else {
                writer.write(assets.size() + "\n");
                writer.flush();
                assets.forEach(ass -> ass.save(outputStream));
            }
            //implement this method - реализуйте этот метод
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
            name = reader.readLine();
            String listSize = reader.readLine();
            if (!listSize.equals("null")) {
                int size = Integer.parseInt(listSize);
                while (size != 0) {
                    Asset asset = new Asset();
                    asset.load(reader);
                    assets.add(asset);
                    size--;
                }
            }
            //implement this method - реализуйте этот метод
        }
    }
}