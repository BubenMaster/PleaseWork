package com.yoj.core.level4.henFabric;

public class Solution {
    public static void main(String[] args) {
        Hen hen = HenFactory.getHen(Country.RUSSIA);
        System.out.println(hen.getDescription());
    }

    static class HenFactory {

        static Hen getHen(String country) {
            Hen hen;
            hen = switch (country) {
                case Country.BELARUS : yield new BelarusHen();
                case Country.RUSSIA: yield new RussianHen();
                case Country.UKRAINE: yield new UkrainianHen();
                case Country.MOLDOVA: yield new MoldovanHen();
                default: yield null;
            };
            //напишите тут ваш код
            return hen;
        }
    }


}
