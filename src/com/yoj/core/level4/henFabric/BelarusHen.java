package com.yoj.core.level4.henFabric;

public class BelarusHen  extends Hen{
    private final String country = Country.BELARUS;


    @Override
    public String getDescription() {
        return super.getDescription()  + " Моя страна " +  country + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }

    @Override
    public int getCountOfEggsPerMonth() {
        return 6;
    }
}
