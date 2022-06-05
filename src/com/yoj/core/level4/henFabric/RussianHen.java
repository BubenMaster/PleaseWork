package com.yoj.core.level4.henFabric;

public class RussianHen extends Hen{
    private final String country = Country.RUSSIA;


    @Override
    public String getDescription() {
        return super.getDescription()  + " Моя страна " +  country + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }

    @Override
    public int getCountOfEggsPerMonth() {
        return 5;
    }
}
