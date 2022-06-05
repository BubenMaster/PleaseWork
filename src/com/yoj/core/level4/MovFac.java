package com.yoj.core.level4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
MovieFactory
*/

public class MovFac {
    public static void main(String[] args) throws Exception {
        //ввести с консоли несколько ключей (строк), пункт 7
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        List<String> personList = new ArrayList<>();
        personList.add("cartoon");
        personList.add("soapopera");
        personList.add("thriller");


        try {
            String line;
            do {
            /*
8 Создать переменную movie класса Movie и для каждой введенной строки(ключа):
8.1 получить объект используя MovieFactory.getMovie и присвоить его переменной movie
8.2 вывести на экран movie.getClass().getSimpleName()
        */  line = reader.readLine().toLowerCase();
             Movie movie = MovieFactory.getMovie(line);

             movie.getClassText();
            } while (personList.contains(line));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
    }

    static class MovieFactory {

        static Movie getMovie(String key) {
            Movie movie = null;
            String keyLow = key.toLowerCase();
            //создание объекта SoapOpera (мыльная опера) для ключа "soapOpera"
            if ("soapopera".equals(keyLow)) {
                movie = new SoapOpera();
            }
            else if ("cartoon".equals(keyLow)) {
                movie = new Cartoon();
            }
            else if ("thriller".equals(keyLow)) {
                movie = new Thriller();
            }
            else movie = new Movie() {
                    @Override
                    public void getClassText() {
                        super.getClassText();
                    }
                };

            //напишите тут ваш код, пункты 5,6

            return movie;
        }
    }

    static abstract class Movie {
        public void getClassText() {
            System.out.println(this.getClass().getSimpleName());
        }
    }

    static class SoapOpera extends Movie {
    }

    static class Cartoon extends Movie {
    }

    static class Thriller extends Movie {
    }

    //Напишите тут ваши классы, пункт 3
}
