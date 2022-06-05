package com.yoj.core.level4.henFabric;

public abstract class Hen {
    Country country;


    public int getCountOfEggsPerMonth() {
        return 4;
    }

    public String getDescription() {
        return "Я - курица.";
    }
}
