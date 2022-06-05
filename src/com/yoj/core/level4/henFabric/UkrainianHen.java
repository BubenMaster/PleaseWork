package com.yoj.core.level4.henFabric;

public class UkrainianHen extends Hen{
    private final String country = Country.UKRAINE;


    @Override
    public String getDescription() {
        return super.getDescription()  + " Моя страна " +  country + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }

    @Override
    public int getCountOfEggsPerMonth() {
        return 7;
    }
}
